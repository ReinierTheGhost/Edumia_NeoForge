package com.legends.edumia.entity;

import com.legends.edumia.entity.goals.CustomBowAttackGoal;
import com.legends.edumia.entity.goals.NpcTargetPlayerGoal;
import com.legends.edumia.entity.races.human.BanditHumanEntity;
import com.legends.edumia.exceptions.FactionResourceLocationException;
import com.legends.edumia.items.weapons.ranged.CustomLongbowWeaponItem;
import com.legends.edumia.resources.StateSaverAndLoader;
import com.legends.edumia.resources.datas.Disposition;
import com.legends.edumia.resources.datas.DispositionUtil;
import com.legends.edumia.resources.datas.factions.Faction;
import com.legends.edumia.resources.datas.factions.FactionLookup;
import com.legends.edumia.resources.datas.npcs.NpcData;
import com.legends.edumia.resources.datas.npcs.NpcUtil;
import com.legends.edumia.resources.datas.npcs.data.NpcGearData;
import com.legends.edumia.resources.datas.npcs.data.NpcRank;
import com.legends.edumia.resources.datas.races.Race;
import com.legends.edumia.resources.datas.races.RaceLookup;
import com.legends.edumia.resources.persistent_datas.PlayerData;
import com.legends.edumia.utils.LoggerUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Predicate;

public class NpcEntity extends PathfinderMob implements RangedAttackMob {
    protected Disposition disposition;
    private ResourceLocation raceID;
    private Item bow;
    private final CustomBowAttackGoal<NpcEntity> bowAttackGoal = new CustomBowAttackGoal<NpcEntity>(this, 1.0,
            16, 30.0f);
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.5, false);
    public NpcRank rank;
    protected NpcEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.updateAttackType();
        for (int i = 0; i < 4; i++) {
            Arrays.fill(this.armorDropChances, 0.0f);
        }
    }

    protected ResourceLocation getFactionId(){
        return null;
    }
    protected ResourceLocation getRaceId(){
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType,
                                        @Nullable SpawnGroupData spawnGroupData) {
        spawnGroupData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        this.updateAttackType();
        return spawnGroupData;
    }

    @Override
    protected void registerGoals() {
        ResourceLocation factionId = getFactionId();
        if(factionId == null)
            disposition = Disposition.NEUTRAL;
        else {
            try {
                disposition = FactionLookup.getFactionById(level(), factionId).getDisposition();
            } catch (FactionResourceLocationException e) {
                disposition = Disposition.NEUTRAL; // Attacks everyone, no judgement made
            }
        }

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this, this.getClass()));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(2, new NpcTargetPlayerGoal(this));
    }

    public void updateAttackType() {
        if (this.level() != null && !this.level().isClientSide) {
            this.goalSelector.removeGoal(this.meleeAttackGoal);
            this.goalSelector.removeGoal(this.bowAttackGoal);

            ItemStack itemStack = this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemStack != null && itemStack.getItem() instanceof BowItem) {
                this.bow = itemStack.getItem();
            } else {
                this.bow = null;
            }

            if (this.bow != null) {
                this.bowAttackGoal.setAttackInterval(16);
                this.goalSelector.addGoal(2, this.bowAttackGoal);
            } else {
                this.goalSelector.addGoal(2, this.meleeAttackGoal);
            }
        }
    }

    public Disposition getDisposition(){
        return disposition;
    }
    public Item getBow(){
        return this.bow;
    }

    public void setBow(Item bow){
        this.bow = bow;
    }

    public NpcRank getRank() {
        return rank;
    }

    public void setRank(NpcRank rank) {
        this.rank = rank;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        if(target == null || level().getDifficulty() == Difficulty.PEACEFUL){
            return false;
        }

        if(target instanceof Player player) {
            if(player.isCreative()){
                return false;
            }
            if(disposition != null){
                PlayerData data = StateSaverAndLoader.getPlayerState(player);
                if(data != null){
                    Disposition playerDisposition = data.getCurrentDisposition();
                    if(playerDisposition == disposition){
                        return false;
                    }
                    if(playerDisposition == null)
                        return true;
                    return true;
                }
            }
        }
        return super.canAttack(target);
    }

    public static enum State {
        NEUTRAL,
        ATTACKING,
    }

    public NpcEntity.State getState() {
        if (this.isAggressive()) {
            return NpcEntity.State.ATTACKING;
        }
        return NpcEntity.State.NEUTRAL;
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        tryToEquipGears(this.getRank(), this.getRaceId(), getFactionId());
    }

    @Override
    public boolean isPersistenceRequired() {
        return super.isPersistenceRequired();
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        super.setItemSlot(slot, stack);
        if (!this.level().isClientSide) {
            this.updateAttackType();
        }
    }

    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
        return weapon == getBow();
    }

    @Override
    public ItemStack getProjectile(ItemStack stack) {
        if (stack.getItem() instanceof BowItem) {
            Predicate<ItemStack> predicate = ((ProjectileWeaponItem)stack.getItem()).getSupportedHeldProjectiles();
            ItemStack itemStack = ProjectileWeaponItem.getHeldProjectile(this, predicate);
            return itemStack.isEmpty() ? new ItemStack(Items.ARROW) : itemStack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float pullProgress) {
        ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, getBow()));
        ItemStack itemStack2 = this.getProjectile(itemStack);
        AbstractArrow persistentProjectileEntity = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
        double d = target.getX() - this.getX();
        double e = target.getY(0.3333333333333333) - persistentProjectileEntity.getY();
        double f = target.getZ() - this.getZ();
        double g = Math.sqrt(d * d + f * f);

        boolean isLongbow =(this.bow instanceof CustomLongbowWeaponItem);
        float power = (isLongbow) ? 2.5F : 1.6f;
        float uncertaintyBase = (isLongbow) ? 10 : 14;
        float yVelocityModifier = (isLongbow) ?  0.10000000298023224f : 0.20000000298023224f;

        persistentProjectileEntity.shoot(d, e + g * yVelocityModifier, f, power,
                (uncertaintyBase - this.level().getDifficulty().getId() * 4));

        this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(persistentProjectileEntity);
    }

    protected AbstractArrow createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
        return ProjectileUtil.getMobArrow(this, arrow, damageModifier, shotFrom);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.updateAttackType();
    }

    @Override
    protected int getBaseExperienceReward() {
        int exp = 0;
        switch (this.getRank()){
            case NpcRank.CIVILIAN -> exp = 5;
            case NpcRank.MILITIA -> exp = 10;
            case NpcRank.SOLDIER -> exp = 15;
            case NpcRank.KNIGHT -> exp = 20;
            case NpcRank.VETERAN -> exp = 25;
            case NpcRank.LEADER -> exp = 30;
        }
        return exp;
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        super.actuallyHurt(damageSource, damageAmount);
    }

    @Override
    protected void dropExperience(@Nullable Entity attacker) {
        if(attacker instanceof Player player && canDrop(player, null)){
            super.dropExperience(attacker);
        }
    }

    @Override
    protected void dropFromLootTable(DamageSource damageSource, boolean causedByPlayer) {
        if(damageSource.getEntity() instanceof Player player && canDrop(player, damageSource)){
            super.dropFromLootTable(damageSource, causedByPlayer);
        }
    }

    private boolean canDrop(Player player, DamageSource damageSource) {
        /*
        // If we want more control over what drop and what doesn't allow drops
        if(!causedByPlayer){
            String damageSourceValue = damageSource.getTypeRegistryEntry().getIdAsString();
            if(Objects.equals(damageSourceValue, DamageTypes.IN_WALL.getValue().toString()))
                return false;
        }
        */

        if(player != null){
            Disposition playerDisposition = DispositionUtil.getDisposition(player);
            return playerDisposition == null || playerDisposition != getDisposition();
        }

        return true;
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit) {
        return;
    }

    protected void tryToEquipGears(NpcRank npcRank, ResourceLocation raceId, ResourceLocation factionId) {
        if(factionId == null)
            return;
        try{
            Faction faction = FactionLookup.getFactionById(level(), factionId);
            Race race = RaceLookup.getRace(level(), raceId);
            NpcData data = faction.getRandomGear(level(), npcRank, race);
            if(data == null)
                return;
            NpcGearData gearData = data.getGear();
            NpcUtil.equipAll(this, gearData);
        } catch (FactionResourceLocationException e) {
            LoggerUtil.logError("NpcEntity::Couldn't find faction registry with [%s] for rank [%s]".formatted(factionId, npcRank.toString()));
            throw new RuntimeException(e);
        }
    }

    public int initGoodTargetSelector(int i){
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, BanditHumanEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Player.class, true));

        return i;
    }

    public int initEvilTargetSelector(int i){
        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, BanditHumanEntity.class, true));

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Player.class, true));

        return i;
    }

    public int initNeutralTargetSelector(int i){

        this.targetSelector.addGoal(++i, new NearestAttackableTargetGoal<>(this, Player.class, true));

        return i;
    }
}

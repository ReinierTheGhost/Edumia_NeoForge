package com.legends.edumia.entity.animals.dragonfly;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class DragonflyEntity  extends Animal implements FlyingAnimal {
    private static final EntityDataAccessor<Integer> LANDED =
            SynchedEntityData.defineId(DragonflyEntity.class, EntityDataSerializers.INT);
    private static final TargetingConditions playerPredicate =
            TargetingConditions.forNonCombat().range(4.0D).ignoreLineOfSight();
    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(DragonflyEntity.class, EntityDataSerializers.INT);
    private BlockPos targetPosition;
    private int rainTicks = 0;

    public DragonflyEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public DragonflyVariant getVariant() {
        return DragonflyVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(DragonflyVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        DragonflyVariant variant = Util.getRandom(DragonflyVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
        builder.define(LANDED, 1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1d)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.FLYING_SPEED, 0.2D)
                .add(Attributes.SCALE, 0.5D);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return super.isInvulnerableTo(source) || source == level().damageSources().sweetBerryBush();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entityIn) {
    }

    @Override
    protected void pushEntities() {
    }

    public boolean isLanded() {
        return this.entityData.get(LANDED) != 1;
    }

    public int getLandedInteger() {
        return this.entityData.get(LANDED);
    }

    public void setLanded(Direction direction) {
        if(direction == Direction.UP) {
            throw new RuntimeException("Invalid landing direction!");
        }
        this.entityData.set(LANDED, direction.ordinal());
    }

    public void setNotLanded() {
        this.entityData.set(LANDED, 1);
        this.teleportTo(this.blockPosition().getX() + 0.5D, this.blockPosition().getY() + 0.5D,
                this.blockPosition().getZ() + 0.5D);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.isLanded()) {
            this.setDeltaMovement(Vec3.ZERO);
            if(Direction.from3DDataValue(this.getLandedInteger()) != Direction.DOWN) {
                double x = Math.floor(this.getX()) + 0.5D;
                double z = Math.floor(this.getZ()) + 0.5D;
                BlockPos pos = new BlockPos((int) x, (int) (Math.floor(this.getY()) + 0.5D), (int) z);
                BlockPos offset = pos.relative(Direction.from3DDataValue(this.getLandedInteger()));
                BlockPos diff = pos.subtract(offset);
                this.teleportTo(x - ((double) diff.getX()) / 2.778D, Math.floor(this.getY()) + 0.5D,
                        z - ((double) diff.getZ()) / 2.778D);
                this.setYRot(0);
                this.yHeadRot = 0;
            } else {
                this.teleportTo(this.getX(), Math.floor(this.getY()), this.getZ());
            }
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }
    }

    public boolean isRainingAt(BlockPos position) {
        if(!level().isRaining()) {
            return false;
        } else if(position == null) {
            return true;
        } else if(!level().isLoaded(position) || !level().canSeeSkyFromBelowWater(position)) {
            return false;
        } else {
            return level().getBiome(position).value().getPrecipitationAt(blockPosition()) == Biome.Precipitation.RAIN;
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        BlockPos blockpos = this.blockPosition();
        if(this.isLanded()) {
            Direction direction = Direction.from3DDataValue(this.getLandedInteger());
            BlockPos offset = blockpos.relative(direction);
            if(this.level().getBlockState(offset).isFaceSturdy(level(), offset, direction.getOpposite(), SupportType.CENTER)) {
                if(this.level().getNearestPlayer(playerPredicate, this) != null || this.getRandom().nextInt(500) == 0) {
                    this.setNotLanded();
                }
            } else {
                this.setNotLanded();
            }
        }
        if(isRainingAt(this.blockPosition())) {
            rainTicks++;
            if(this.isLanded()) {
                this.setNotLanded();
            }
            if(this.targetPosition == null || isRainingAt(this.targetPosition)) {
                this.targetPosition = tryToFindPositionBlock(pos -> {
                    if(pos != null && !isRainingAt(pos) && level().isEmptyBlock(pos)) {
                        for(Direction direction : Direction.values()) {
                            if(direction != Direction.UP) {
                                BlockPos offset = pos.relative(direction);
                                if(this.level().getBlockState(offset).isFaceSturdy(level(), offset, direction.getOpposite(),
                                        SupportType.CENTER)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                    return false;
                });
            }
            if(this.getRandom().nextFloat() < 0.0025F && rainTicks > 100) {
                this.hurt(level().damageSources().drown(), 1F);
            }
        } else {
            rainTicks = 0;
            if(this.targetPosition == null || this.random.nextInt(30) == 0 ||
                    (this.targetPosition.closerThan(this.blockPosition(), 1.0D))) {
                if(level().isRaining() && level().getBiome(this.blockPosition()).value()
                        .getPrecipitationAt(blockpos) == Biome.Precipitation.RAIN) {
                    // attempt to land
                    boolean found = false;
                    for(Direction direction : Direction.values()) {
                        if(direction != Direction.UP) {
                            BlockPos offset = blockpos.relative(direction);
                            if(level().getBlockState(offset).isFaceSturdy(level(), offset, direction.getOpposite(),
                                    SupportType.CENTER) && level().isEmptyBlock(blockpos)) {
                                this.setLanded(direction);
                                this.targetPosition = null;
                                found = true;
                                break;
                            }
                        }
                    }
                    if(!found) {
                        BlockPos temp = new BlockPos(
                                (int) (this.getX() + (double) this.random.nextInt(5) - (double) this.random.nextInt(5)),
                                (int) (this.getY() + (double) this.random.nextInt(4) - 1.0D),
                                (int) (this.getZ() + (double) this.random.nextInt(5) - (double) this.random.nextInt(5)));
                        if(!isRainingAt(temp)) {
                            this.targetPosition = temp;
                        }
                    }
                } else {
                    boolean found = false;
                    if(this.level().getNearestPlayer(playerPredicate, this) == null) {
                        for(Direction direction : Direction.values()) {
                            if(direction != Direction.UP) {
                                BlockPos offset = blockpos.relative(direction);
                                if(level().getBlockState(offset).isFaceSturdy(level(), offset, direction.getOpposite(),
                                        SupportType.CENTER) && level().isEmptyBlock(blockpos)) {
                                    this.setLanded(direction);
                                    this.targetPosition = null;
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                    if(!found) {
                        int attempts = 0;
                        while(attempts < 5 && (targetPosition == null || !level().isEmptyBlock(targetPosition))) {
                            this.targetPosition = new BlockPos(
                                    (int) (this.getX() + (double) this.random.nextInt(5) - (double) this.random.nextInt(5)),
                                    (int) (this.getY() + (double) this.random.nextInt(4) - 1.0D),
                                    (int) (this.getZ() + (double) this.random.nextInt(5) - (double) this.random.nextInt(5)));
                            attempts++;
                        }
                    }
                }
            }
            if(this.targetPosition != null && this.targetPosition.closerThan(this.blockPosition(), 1.0D)) {
                this.targetPosition = null;
            }
        }
        if(!this.isLanded() && targetPosition != null) {
            if(this.isInWall()) {
                this.targetPosition = this.tryToFindPositionSlow(pos -> {
                    AABB abb = this.getBoundingBox();
                    Vec3 diff = new Vec3(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D).subtract(this.position());
                    double length = diff.length();
                    diff = diff.normalize();
                    for(int i = 1; i < length; ++i) {
                        abb = abb.move(diff);
                        if (!this.level().noCollision(this, abb)) {
                            return false;
                        }
                    }
                    return true;
                });

            }
            double d0 = (double) this.targetPosition.getX() + 0.5D - this.getX();
            double d1 = (double) this.targetPosition.getY() + 0.1D - this.getY();
            double d2 = (double) this.targetPosition.getZ() + 0.5D - this.getZ();
            Vec3 vec3d = this.getDeltaMovement();
            Vec3 vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * (double) 0.1F,
                    (Math.signum(d1) * (double) 0.7F - vec3d.y) * (double) 0.1F,
                    (Math.signum(d2) * 0.5D - vec3d.z) * (double) 0.1F);
            this.setDeltaMovement(vec3d1);
            float f = (float) (Mth.atan2(vec3d1.z, vec3d1.x) * (double) (180F / (float) Math.PI)) - 90.0F;
            float f1 = Mth.wrapDegrees(f - this.getYRot());
            this.zza = 0.5F;
            this.setYRot(this.getYRot() + f1);
        }
    }

    private BlockPos tryToFindPositionBlock(Predicate<BlockPos> condition) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int i = 12;
        int j = 2;
        for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
            for(int l = 0; l < i; ++l) {
                for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        pos.set(this.blockPosition()).move(i1, k - 1, j1);
                        if(condition.test(pos.immutable())) {
                            return pos.immutable();
                        }
                    }
                }
            }
        }
        return null;
    }

    private BlockPos tryToFindPositionSlow(Predicate<BlockPos> condition) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int i = 12;
        int j = 2;
        boolean down = false;
        while (!down) {
            for (int k = down ? -1 : 0; down ? k >= -j : k <= j; k += down ? -1 : 1) {
                for (int l = 0; l < i; ++l) {
                    for (int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                        for (int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                            pos.set(this.blockPosition()).move(i1, k, j1);
                            if (condition.test(pos.immutable())) {
                                return pos.immutable();
                            }
                        }
                    }
                }
            }
            down = true;
        }
        return null;
    }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    @Override
    public boolean causeFallDamage(float f, float g, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if(this.isInvulnerableTo(source)) {
            return false;
        } else {
            if(!this.level().isClientSide && this.isLanded()) {
                this.setNotLanded();
            }
            return super.hurt(source, amount);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getInt("Variant"));
        this.entityData.set(LANDED, compound.getInt("Landed"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.entityData.get(VARIANT));
        compound.putInt("Landed", this.entityData.get(LANDED));
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return null;
    }

    @Override
    public boolean isFlying() {
        return false;
    }
}

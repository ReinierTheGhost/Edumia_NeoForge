package com.legends.edumia.resources.datas.races;

import com.legends.edumia.entity.EdumiaEntities;
import com.legends.edumia.entity.NpcEntity;
import com.legends.edumia.entity.races.darkelves.DarkElfEntity;
import com.legends.edumia.entity.races.demon.DemonEntity;
import com.legends.edumia.entity.races.fairy.FairyEntity;
import com.legends.edumia.entity.races.gensai.GensaiEntity;
import com.legends.edumia.entity.races.highelves.HighElfEntity;
import com.legends.edumia.entity.races.human.BanditHumanEntity;
import com.legends.edumia.entity.races.human.HumanEntity;
import com.legends.edumia.entity.races.ogre.OgreEntity;
import com.legends.edumia.entity.races.orc.OrcEntity;
import com.legends.edumia.resources.datas.RaceType;
import com.legends.edumia.resources.datas.races.data.AttributeData;
import com.legends.edumia.resources.datas.races.data.RaceFeature;
import com.legends.edumia.utils.ResourceLocationUtil;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Race {
    public static final Codec<Race> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(Race::getIdValue),
            Codec.STRING.fieldOf("type").forGetter(Race::getRaceTypeValue),
            CompoundTag.CODEC.fieldOf("attributes").forGetter(Race::getAttributeDatas),
            Codec.list(Codec.STRING, 0, 5).optionalFieldOf("command_join").forGetter(Race::getJoinCommands),
            Codec.list(Codec.STRING, 0, 5).optionalFieldOf("command_leave").forGetter(Race::getLeaveCommands),
            RaceFeature.CODEC.listOf().optionalFieldOf("features").forGetter(Race::getFeaturesValue)
    ).apply(instance, Race::new));

    private final ResourceLocation id;
    private final RaceType raceType;
    private final String translatableKey;
    private final AttributeData attributeData;
    private List<String> joinCommands;
    private List<String> leaveCommands;
    private final List<RaceFeature> features;


    public Race(String id, String raceTypeValue, CompoundTag attributes, Optional<List<String>> joinCommands,
                Optional<List<String>> leaveCommands, Optional<List<RaceFeature>> features){
        // Create id
        this.id = ResourceLocationUtil.getResourceLocationFromString(id);
        this.translatableKey = "race.".concat(this.id.toLanguageKey());
        // Create model
        this.raceType = RaceType.valueOf(raceTypeValue.toUpperCase());
        // Attribute Datas
        this.attributeData = new AttributeData(attributes);
        // Join commands
        this.joinCommands = new ArrayList<>();
        joinCommands.ifPresent(nbtCompound -> this.joinCommands.addAll(nbtCompound));
        // Leave commands
        this.leaveCommands = new ArrayList<>();
        leaveCommands.ifPresent(nbtCompound -> this.leaveCommands.addAll(nbtCompound));
        // Features
        this.features = features.orElse(List.of());
    }

    public Race(ResourceLocation id, RaceType raceType, AttributeData attributeData, List<String> joinCommands,
                List<String> leaveCommands, List<RaceFeature> features) {
        this.id = id;
        this.raceType = raceType;
        this.translatableKey = "race.".concat(this.id.toLanguageKey());
        this.attributeData = attributeData;
        this.joinCommands = joinCommands;
        this.leaveCommands = leaveCommands;
        this.features = features;
    }

    public ResourceLocation getId() {
        return id;
    }
    private String getIdValue() {
        return this.id.toString();
    }
    private String getRaceTypeValue() {
        return raceType.toString().toUpperCase();
    }
    private CompoundTag getAttributeDatas() {
        if(attributeData == null)
            return null;
        return attributeData.getNbt();
    }
    public Optional<List<String>> getJoinCommands() {
        if(this.joinCommands == null)
            return Optional.empty();
        return Optional.of(this.joinCommands);
    }
    public Optional<List<String>> getLeaveCommands() {
        if(this.leaveCommands == null)
            return Optional.empty();
        return Optional.of(this.leaveCommands);
    }

    public Component getFullName() {
        return Component.translatable(translatableKey);
    }

    public LivingEntity getModel(Level world) {
        NpcEntity entity;
        switch (raceType){
            case RaceType.HUMAN:
                entity = new HumanEntity(EdumiaEntities.HUMAN_CIVILIAN.get(), world);
                break;
            case RaceType.HIGH_ELF:
                entity = new HighElfEntity(EdumiaEntities.HIGH_ELVEN_CIVILIAN.get(), world);
                break;
            case RaceType.DARK_ELF:
                entity = new DarkElfEntity(EdumiaEntities.DARK_ELVEN_CIVILIAN.get(), world);
                break;
            case RaceType.DEMON:
                entity = new DemonEntity(EdumiaEntities.DEMON_CIVILIAN.get(), world);
                break;
            case RaceType.ORC:
                entity = new OrcEntity(EdumiaEntities.ORC_CIVILIAN.get(), world);
                break;
            case RaceType.OGRE:
                entity = new OgreEntity(EdumiaEntities.OGRE_CIVILIAN.get(), world);
                break;
            case RaceType.FAIRY:
                entity = new FairyEntity(EdumiaEntities.FAIRY_CIVILIAN.get(), world);
                break;
//            case RaceType.GENSAI:
//                entity = new GensaiEntity(EntityType.VILLAGER, world);
//                break;
            default:
                entity = new BanditHumanEntity(EntityType.VILLAGER, world);
                break;
        }
        entity.setNoAi(true);
        return entity;
    }

    public void applyAttributes(Player playerEntity){
        attributeData.ApplyAll(playerEntity);
    }

    public void reverseAttributes(Player playerEntity){
        AttributeData.reset(playerEntity);
    }

    public String getTranslatableKey() {
        return translatableKey;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public List<RaceFeature> getFeatures() {
        return features;
    }
    private Optional<List<RaceFeature>> getFeaturesValue() {
        if(this.features == null)
            return Optional.empty();
        return Optional.of(this.features);
    }


    public void drawTooltip(LivingEntity entity, GuiGraphics context, Font renderer, int x, int y){
        List<Component> texts = new ArrayList<>();
        texts.add(getFullName());
        texts.add(Component.translatable("race_tooltip.edumia.attribute_header").withStyle(ChatFormatting.UNDERLINE));
        Map<ResourceLocation, Double> datas = attributeData.getDatas();
        for(ResourceLocation id : datas.keySet()){
            double value = datas.get(id);
            double difference = datas.get(id) - attributeData.getCurrentValue(entity, id);
            // Round them
            value = Math.round(value * 1000) / 1000.0;
            difference = Math.round(difference * 1000) / 1000.0;

            String differenceChar = (difference > 0) ? "+" : "";
            ChatFormatting white = ChatFormatting.WHITE;
            ChatFormatting differenceColor = (difference < 0) ? ChatFormatting.RED : (difference > 0) ? ChatFormatting.GREEN : white;
            if(attributeData.isBuffReversed(id)){
                differenceColor = (difference < 0) ? ChatFormatting.GREEN : (difference > 0) ? ChatFormatting.RED : white;
            }
            MutableComponent rawValue = Component.literal(String.valueOf(value)).withStyle(white);
            MutableComponent valueText = rawValue.append(Component.literal(" (").withStyle(white)
                    .append(Component.literal(differenceChar + difference).withStyle(differenceColor)
                            .append(Component.literal(") ").withStyle(white))));
            texts.add(valueText.append(Component.translatable("attribute.name."+id.getPath())
                    .withStyle(ChatFormatting.WHITE)));
        }
        context.renderComponentTooltip(renderer, texts, x, y);
    }


}

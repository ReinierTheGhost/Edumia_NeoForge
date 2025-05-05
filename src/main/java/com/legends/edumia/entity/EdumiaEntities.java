package com.legends.edumia.entity;

import com.legends.edumia.Edumia;
import com.legends.edumia.entity.races.darkelves.DarkElfEntity;
import com.legends.edumia.entity.races.fairy.FairyEntity;
import com.legends.edumia.entity.races.highelves.HighElfEntity;
import com.legends.edumia.entity.races.human.HumanEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EdumiaEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Edumia.MOD_ID);

    public static final Supplier<EntityType<FairyEntity>> FAIRY_CIVILIAN =
            ENTITY_TYPES.register("fairy", () -> EntityType.Builder.of(FairyEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 2F).build("fairy"));

    public static final Supplier<EntityType<HumanEntity>> HUMAN_CIVILIAN =
            ENTITY_TYPES.register("human", () -> EntityType.Builder.of(HumanEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 2F).build("human"));
    public static final Supplier<EntityType<HighElfEntity>> HIGH_ELVEN_CIVILIAN =
            ENTITY_TYPES.register("high_elven", () -> EntityType.Builder.of(HighElfEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 2F).build("high_elven"));
    public static final Supplier<EntityType<DarkElfEntity>> DARK_ELVEN_CIVILIAN =
            ENTITY_TYPES.register("dark_elven", () -> EntityType.Builder.of(DarkElfEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 2F).build("dark_elven"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}

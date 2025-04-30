package com.legends.edumia.entity;

import com.legends.edumia.Edumia;
import com.legends.edumia.entity.races.fairy.FairyEntity;
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

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}

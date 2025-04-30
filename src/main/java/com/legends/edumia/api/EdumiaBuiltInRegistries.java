package com.legends.edumia.api;

import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.jetbrains.annotations.ApiStatus;

public class EdumiaBuiltInRegistries {

    private static <T> Registry<T> simple(ResourceKey<Registry<T>> key) {
        return register(key, false, () -> {
        });
    }

    private static <T> Registry<T> simpleWithFreezeCallback(ResourceKey<Registry<T>> key, Runnable onBakeCallback) {
        return register(key, false, onBakeCallback);
    }

    private static <T> Registry<T> withIntrusiveHolders(ResourceKey<Registry<T>> key) {
        return register(key, true, () -> {
        });
    }

    private static <T> Registry<T> register(ResourceKey<Registry<T>> key, boolean hasIntrusiveHolders, Runnable onBakeCallback) {
        RegistryBuilder<T> builder = (new RegistryBuilder(key)).sync(true);
        if (hasIntrusiveHolders) {
            builder.withIntrusiveHolders();
        }

        builder.onBake((r) -> onBakeCallback.run());
        Registry<T> registry = builder.create();
        ((WritableRegistry) BuiltInRegistries.REGISTRY).register(key, registry, RegistrationInfo.BUILT_IN);
        return registry;
    }

    @ApiStatus.Internal
    public static void init() {
    }

}

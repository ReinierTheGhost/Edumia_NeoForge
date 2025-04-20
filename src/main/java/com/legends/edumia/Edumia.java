package com.legends.edumia;

import com.legends.edumia.blocks.blocksets.*;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.core.CreativeTabLoader;
import com.legends.edumia.core.ItemLoader;
import com.legends.edumia.world.biomes.EdumiaBiomeKeys;
import com.legends.edumia.world.biomes.surface.MapBasedBiomePool;
import com.legends.edumia.world.biomes.surface.MapBiomeData;
import com.legends.edumia.world.chunkgen.ModChunkGenerators;
import com.legends.edumia.world.dimension.ModDimensions;
import com.legends.edumia.world.features.EdumiaFeatures;
import com.legends.edumia.world.gen.ModWorldGeneration;
import com.legends.edumia.world.map.EdumiaMapGeneration;
import com.legends.edumia.world.trees.EdumiaFoliagePlacerTypes;
import com.legends.edumia.world.trees.EdumiaTreeDecoratorTypes;
import com.legends.edumia.world.trees.EdumiaTrunkPlacerTypes;
import earth.terrarium.chipped.common.registry.ModBlocks;
import glitchcore.util.BlockHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@SuppressWarnings("deprecation")
@Mod(Edumia.MOD_ID)
public class Edumia
{

    public static final String MOD_ID = "edumia";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final boolean IS_DEBUG = true;

    public static final String MOD_VERSION = "1.1.0-1.21.1-beta";

    public static final boolean ENABLE_INSTANT_BOOTING = false;

    public Edumia(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        CreativeTabLoader.register(modEventBus);

        BlockLoader.register(modEventBus);
        ItemLoader.register(modEventBus);
        BuildingSets.register(modEventBus);
        ClayTilingSets.register(modEventBus);
        GlassSets.register(modEventBus);
        StoneSets.register(modEventBus);
        WoodBlockSets.register(modEventBus);
        PaperwallSets.register(modEventBus);
        NotBrickBuildingSets.register(modEventBus);
        ModNatureBlocks.register(modEventBus);
        SandBlockSets.register(modEventBus);
        FlowerBlockSets.register(modEventBus);
        GrassBlockSets.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        EdumiaTrunkPlacerTypes.register(modEventBus);
        EdumiaFoliagePlacerTypes.register(modEventBus);
        EdumiaTreeDecoratorTypes.register(modEventBus);
        EdumiaFeatures.register(modEventBus);

        ModChunkGenerators.register(modEventBus);

        ModDimensions.register();
        EdumiaBiomeKeys.registerModBiomes();


        ModWorldGeneration.generateModWorldGen();

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        MapBasedBiomePool.loadBiomes();
        MapBiomeData.loadBiomes();
        try {
            new EdumiaMapGeneration();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        BlockHelper.registerFlattenable(BlockLoader.VOLCANIC_DIRT.get(), BlockLoader.VOLCANIC_DIRT_PATH.get().defaultBlockState());

        for (WoodBlockSets.SimpleBlockSet set : WoodBlockSets.sets){
            event.enqueueWork(() -> {
                BlockHelper.registerStrippable(set.log().get(), set.strippedLog().get());
                BlockHelper.registerStrippable(set.wood().get(), set.strippedWood().get());
            });
        }

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SuppressWarnings("deprecated")
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            for (GlassSets.GlassSet set : GlassSets.glassSets) {
                ItemBlockRenderTypes.setRenderLayer(set.block().get(), RenderType.translucent());
            }
        }
    }

    public static ResourceLocation location(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }

}

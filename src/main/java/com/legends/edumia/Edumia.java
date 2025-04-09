package com.legends.edumia;

import com.legends.edumia.blocks.blocksets.*;
import com.legends.edumia.core.BlockLoader;
import com.legends.edumia.core.CreativeTabLoader;
import com.legends.edumia.core.ItemLoader;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

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
}

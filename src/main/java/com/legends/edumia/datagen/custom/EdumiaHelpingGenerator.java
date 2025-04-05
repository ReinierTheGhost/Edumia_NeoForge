package com.legends.edumia.datagen.custom;

import com.legends.edumia.blocks.blocksets.BuildingSets;
import com.legends.edumia.datagen.custom.loot_tables.BlockDrops;
import com.legends.edumia.datagen.custom.models.*;
import com.legends.edumia.datagen.custom.tags.MineablePickaxe;
import com.legends.edumia.datagen.custom.tags.RequiresStoneTool;
import com.legends.edumia.datagen.custom.tags.Walls;

public class EdumiaHelpingGenerator {
    public static void generateFiles() {
        for (BuildingSets.BuildSet set : BuildingSets.buildSets){
            SimpleBlockModel.blocks.add(set.block().get());
            SimpleAxisSlabModel.blocks.add(new SimpleAxisSlabModel.Slab(set.block().get(), set.slab().get()));
            SimpleStairModel.blocks.add(new SimpleStairModel.Stair(set.block().get(), set.stair().get()));
            SimpleWallModel.blocks.add(new SimpleWallModel.Wall(set.block().get(), set.wall().get()));

            BlockDrops.blocks.add(set.block().get());
            BlockDrops.blocks.add(set.slab().get());
            BlockDrops.blocks.add(set.stair().get());
            BlockDrops.blocks.add(set.wall().get());

            MineablePickaxe.blocks.add(set.block().get());
            MineablePickaxe.blocks.add(set.slab().get());
            MineablePickaxe.blocks.add(set.stair().get());
            MineablePickaxe.blocks.add(set.wall().get());

            RequiresStoneTool.blocks.add(set.block().get());
            RequiresStoneTool.blocks.add(set.slab().get());
            RequiresStoneTool.blocks.add(set.stair().get());
            RequiresStoneTool.blocks.add(set.wall().get());

            Walls.walls.add(set.wall().get());

//            if (set.pillar() != null){
//                SimplePillarModels.blocks.add(new SimplePillarModels.Pillar(set.pillar().get()));
//                BlockDrops.blocks.add(set.pillar().get());
//                Pillar.pillars.add(set.pillar().get());
//                MineablePickaxe.blocks.add(set.pillar().get());
//                RequiresStoneTool.blocks.add(set.pillar().get());
//
//                SimpleAxisPillarSlabModel.blocks.add(new SimpleAxisPillarSlabModel.Slab(set.pillar().get(), set.pillarSlab().get()));
//                BlockDrops.blocks.add(set.pillarSlab().get());
//                MineablePickaxe.blocks.add(set.pillarSlab().get());
//                RequiresStoneTool.blocks.add(set.pillarSlab().get());
//            }
//
//
//            SimpleSmallArchModel.blocks.add(new SimpleSmallArchModel.Arch(set.block().get(), set.smallArch().get()));
//            BlockDrops.blocks.add(set.smallArch().get());
//            MineablePickaxe.blocks.add(set.smallArch().get());
//            RequiresStoneTool.blocks.add(set.smallArch().get());
//
//            SimpleTwoMeterArchModel.blocks.add(new SimpleTwoMeterArchModel.Arch(set.block().get(), set.twoMeterArch().get()));
//            BlockDrops.blocks.add(set.twoMeterArch().get());
//            MineablePickaxe.blocks.add(set.twoMeterArch().get());
//            RequiresStoneTool.blocks.add(set.twoMeterArch().get());
//
//            SimpleRoundArchModel.blocks.add(new SimpleRoundArchModel.Arch(set.block().get(), set.roundArch().get()));
//            BlockDrops.blocks.add(set.roundArch().get());
//            MineablePickaxe.blocks.add(set.roundArch().get());
//            RequiresStoneTool.blocks.add(set.roundArch().get());
//
//            SimpleSegmentalArchModel.blocks.add(new SimpleSegmentalArchModel.Arch(set.block().get(), set.segmentalArch().get()));
//            BlockDrops.blocks.add(set.segmentalArch().get());
//            MineablePickaxe.blocks.add(set.segmentalArch().get());
//            RequiresStoneTool.blocks.add(set.segmentalArch().get());
//
//            SimpleGothicArchModel.blocks.add(new SimpleGothicArchModel.Arch(set.block().get(), set.gothicArch().get()));
//            BlockDrops.blocks.add(set.gothicArch().get());
//            MineablePickaxe.blocks.add(set.gothicArch().get());
//            RequiresStoneTool.blocks.add(set.gothicArch().get());
//
            SimpleBalustradeModels.blocks.add(new SimpleBalustradeModels.Balustrades(set.block().get(), set.balustrade().get()));
            BlockDrops.blocks.add(set.balustrade().get());
            MineablePickaxe.blocks.add(set.balustrade().get());
            RequiresStoneTool.blocks.add(set.balustrade().get());
//
            SimpleArrowSlitModel.blocks.add(new SimpleArrowSlitModel.ArrowSlit(set.block().get(), set.arrowSlit().get()));
            BlockDrops.blocks.add(set.arrowSlit().get());
            MineablePickaxe.blocks.add(set.arrowSlit().get());
            RequiresStoneTool.blocks.add(set.arrowSlit().get());
        }
    }
}

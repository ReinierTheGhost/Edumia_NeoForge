package com.legends.edumia.datagen.custom;

import com.legends.edumia.blocks.blocksets.*;
import com.legends.edumia.datagen.custom.loot_tables.*;
import com.legends.edumia.datagen.custom.models.*;
import com.legends.edumia.datagen.custom.tags.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

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

            if (set.pillar() != null){
                SimplePillarModels.blocks.add(new SimplePillarModels.Pillar(set.pillar().get()));
                BlockDrops.blocks.add(set.pillar().get());
                Pillar.pillars.add(set.pillar().get());
                MineablePickaxe.blocks.add(set.pillar().get());
                RequiresStoneTool.blocks.add(set.pillar().get());

                SimpleAxisPillarSlabModel.blocks.add(new SimpleAxisPillarSlabModel.Slab(set.pillar().get(), set.pillarSlab().get()));
                BlockDrops.blocks.add(set.pillarSlab().get());
                MineablePickaxe.blocks.add(set.pillarSlab().get());
                RequiresStoneTool.blocks.add(set.pillarSlab().get());
            }


            SimpleSmallArchModel.blocks.add(new SimpleSmallArchModel.Arch(set.block().get(), set.smallArch().get()));
            BlockDrops.blocks.add(set.smallArch().get());
            MineablePickaxe.blocks.add(set.smallArch().get());
            RequiresStoneTool.blocks.add(set.smallArch().get());

            SimpleTwoMeterArchModel.blocks.add(new SimpleTwoMeterArchModel.Arch(set.block().get(), set.twoMeterArch().get()));
            BlockDrops.blocks.add(set.twoMeterArch().get());
            MineablePickaxe.blocks.add(set.twoMeterArch().get());
            RequiresStoneTool.blocks.add(set.twoMeterArch().get());
//
            SimpleRoundArchModel.blocks.add(new SimpleRoundArchModel.Arch(set.block().get(), set.roundArch().get()));
            BlockDrops.blocks.add(set.roundArch().get());
            MineablePickaxe.blocks.add(set.roundArch().get());
            RequiresStoneTool.blocks.add(set.roundArch().get());

            SimpleSegmentalArchModel.blocks.add(new SimpleSegmentalArchModel.Arch(set.block().get(), set.segmentalArch().get()));
            BlockDrops.blocks.add(set.segmentalArch().get());
            MineablePickaxe.blocks.add(set.segmentalArch().get());
            RequiresStoneTool.blocks.add(set.segmentalArch().get());
//
            SimpleGothicArchModel.blocks.add(new SimpleGothicArchModel.Arch(set.block().get(), set.gothicArch().get()));
            BlockDrops.blocks.add(set.gothicArch().get());
            MineablePickaxe.blocks.add(set.gothicArch().get());
            RequiresStoneTool.blocks.add(set.gothicArch().get());
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

            if (set.chiseled() != null){
                SimpleBlockModel.blocks.add(set.chiseled().get());
                BlockDrops.blocks.add(set.chiseled().get());
                MineablePickaxe.blocks.add(set.chiseled().get());
                RequiresStoneTool.blocks.add(set.chiseled().get());
            }
        }

        for (ClayTilingSets.ClayTilingSet set : ClayTilingSets.sets){
            SimpleBlockModel.blocks.add(set.block().get());
            SimpleAxisSlabModel.blocks.add(new SimpleAxisSlabModel.Slab(set.block().get(), set.slab().get()));
            SimpleStairModel.blocks.add(new SimpleStairModel.Stair(set.block().get(), set.stairs().get()));
            SimpleCornerBlockModel.blocks.add(new SimpleCornerBlockModel.Corner(set.block().get(), set.corner().get()));

            BlockDrops.blocks.add(set.block().get());
            BlockDrops.blocks.add(set.slab().get());
            BlockDrops.blocks.add(set.stairs().get());
            BlockDrops.blocks.add(set.corner().get());

            MineablePickaxe.blocks.add(set.block().get());
            MineablePickaxe.blocks.add(set.slab().get());
            MineablePickaxe.blocks.add(set.stairs().get());
            MineablePickaxe.blocks.add(set.corner().get());

            RequiresIronTool.blocks.add(set.block().get());
            RequiresIronTool.blocks.add(set.slab().get());
            RequiresIronTool.blocks.add(set.stairs().get());
            RequiresIronTool.blocks.add(set.corner().get());

        }

        for (GlassSets.GlassSet set : GlassSets.glassSets){
            SimpleGlassModel.blocks.add(new SimpleGlassModel.Glass(set.block().get(), set.pane().get()));
            SimpleBlockModel.blocks.add(set.block().get());

            SilkTouchDrops.blocks.add(set.pane().get());
            SilkTouchDrops.blocks.add(set.block().get());
        }

        for (StoneSets.StoneSet set : StoneSets.naturalSets){
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

            RequiresIronTool.blocks.add(set.block().get());
            RequiresIronTool.blocks.add(set.slab().get());
            RequiresIronTool.blocks.add(set.stair().get());
            RequiresIronTool.blocks.add(set.wall().get());


            Walls.walls.add(set.wall().get());
        }

        for (WoodBlockSets.SimpleBlockSet set : WoodBlockSets.sets){
            if (set.sapling() != null){
                SimpleSaplingModel.blocks.add(set.sapling().get());
                BlockDrops.blocks.add(set.sapling().get());
            }
            if(set.leaves() != null) {
                SimpleLeavesModel.blocks.add(set.leaves().get());
                if (set.sapling() != null){
                    if (set.leaves() != WoodBlockSets.MANGO.leaves()){
                        LeavesDrops.blocks.add(new LeavesDrops.LeavesDrop(set.leaves().get(), set.sapling().get()));
                    }

                }else {
                    LeavesDrops.blocks.add(new LeavesDrops.LeavesDrop(set.leaves().get(), Blocks.OAK_SAPLING));
                }

                Leaves.leaves.add(set.leaves().get());
            }


            SimplePillarModel.blocks.add(new SimplePillarModel.Pillar(set.log().get()));
            SimpleWoodBlockModel.blocks.add(new SimpleWoodBlockModel.WoodBlocks(set.log().get(), set.wood().get()));
            SimpleStairModel.blocks.add(new SimpleStairModel.Stair(set.log().get(), set.woodStairs().get()));
            SimpleAxisSlabModel.blocks.add(new SimpleAxisSlabModel.Slab(set.log().get(), set.woodSlab().get()));
            SimpleWallModel.blocks.add(new SimpleWallModel.Wall(set.log().get(), set.woodWall().get()));
            SimpleFenceModel.blocks.add(new SimpleFenceModel.Fence(set.log().get(), set.woodFence().get()));
            SimplePillarModel.blocks.add(new SimplePillarModel.Pillar(set.strippedLog().get()));
            SimpleWoodBlockModel.blocks.add(new SimpleWoodBlockModel.WoodBlocks(set.strippedLog().get(), set.strippedWood().get()));
            SimpleStairModel.blocks.add(new SimpleStairModel.Stair(set.strippedLog().get(), set.strippedWoodStairs().get()));
            SimpleAxisSlabModel.blocks.add(new SimpleAxisSlabModel.Slab(set.strippedLog().get(), set.strippedWoodSlab().get()));
            SimpleWallModel.blocks.add(new SimpleWallModel.Wall(set.strippedLog().get(), set.strippedWoodWall().get()));
            SimpleFenceModel.blocks.add(new SimpleFenceModel.Fence(set.strippedLog().get(), set.strippedWoodFence().get()));

            SimpleBlockModel.blocks.add(set.planks().get());
            SimpleAxisSlabModel.blocks.add(new SimpleAxisSlabModel.Slab(set.planks().get(), set.planksSlab().get()));
            SimpleStairModel.blocks.add(new SimpleStairModel.Stair(set.planks().get(), set.planksStairs().get()));
            SimpleFenceModel.blocks.add(new SimpleFenceModel.Fence(set.planks().get(), set.planksFence().get()));
            SimpleFenceGateModel.blocks.add(new SimpleFenceGateModel.FenceGate(set.planks().get(), set.planksGate().get()));
            SimpleButtonModel.blocks.add(new SimpleButtonModel.Button(set.planks().get(), set.button().get()));
            SimplePressurePlateModel.blocks.add(new SimplePressurePlateModel.PressurePlate(set.planks().get(), set.pressurePlate().get()));
            SimplePillarModel.blocks.add(new SimplePillarModel.Pillar(set.beam().get()));
            SimpleDoorModel.blocks.add(set.door().get());
            SimpleTrapDoorModel.blocks.add(set.trapdoor().get());


            BlockDrops.blocks.add(set.log().get());
            BlockDrops.blocks.add(set.wood().get());
            BlockDrops.blocks.add(set.strippedLog().get());
            BlockDrops.blocks.add(set.strippedWood().get());
            BlockDrops.blocks.add(set.woodStairs().get());
            BlockDrops.blocks.add(set.woodSlab().get());
            BlockDrops.blocks.add(set.strippedWoodStairs().get());
            BlockDrops.blocks.add(set.strippedWoodSlab().get());
            BlockDrops.blocks.add(set.woodWall().get());
            BlockDrops.blocks.add(set.strippedWoodWall().get());
            BlockDrops.blocks.add(set.strippedWoodFence().get());
            BlockDrops.blocks.add(set.woodFence().get());
            BlockDrops.blocks.add(set.planks().get());
            BlockDrops.blocks.add(set.planksSlab().get());
            BlockDrops.blocks.add(set.planksStairs().get());
            BlockDrops.blocks.add(set.planksFence().get());
            BlockDrops.blocks.add(set.planksGate().get());
            BlockDrops.blocks.add(set.button().get());
            BlockDrops.blocks.add(set.pressurePlate().get());
            DoorDrops.blocks.add(set.door().get());
            BlockDrops.blocks.add(set.trapdoor().get());
            BlockDrops.blocks.add(set.beam().get());

            MineableAxe.blocks.add(set.log().get());
            MineableAxe.blocks.add(set.wood().get());
            MineableAxe.blocks.add(set.strippedLog().get());
            MineableAxe.blocks.add(set.strippedWood().get());
            MineableAxe.blocks.add(set.woodStairs().get());
            MineableAxe.blocks.add(set.woodSlab().get());
            MineableAxe.blocks.add(set.strippedWoodStairs().get());
            MineableAxe.blocks.add(set.strippedWoodSlab().get());
            MineableAxe.blocks.add(set.strippedWoodWall().get());
            MineableAxe.blocks.add(set.strippedWoodFence().get());
            MineableAxe.blocks.add(set.woodWall().get());
            MineableAxe.blocks.add(set.woodFence().get());
            MineableAxe.blocks.add(set.planks().get());
            MineableAxe.blocks.add(set.planksSlab().get());
            MineableAxe.blocks.add(set.planksStairs().get());
            MineableAxe.blocks.add(set.planksFence().get());
            MineableAxe.blocks.add(set.planksGate().get());
            MineableAxe.blocks.add(set.button().get());
            MineableAxe.blocks.add(set.pressurePlate().get());

            Buttons.buttons.add(set.button().get());
            Fences.fences.add(set.woodFence().get());
            Fences.fences.add(set.planksFence().get());
            FenceGates.fenceGates.add(set.planksGate().get());
            Logs.logs.add(set.log().get());
            Logs.logs.add(set.wood().get());
            Logs.logs.add(set.woodWall().get());
            Logs.logs.add(set.woodFence().get());
            Logs.logs.add(set.woodSlab().get());
            Logs.logs.add(set.woodStairs().get());
            PressurePlates.pressurePlates.add(set.pressurePlate().get());
            Walls.walls.add(set.woodWall().get());
            Planks.planks.add(set.planks().get());

        }

        for (WoodBlockSets.SimpleVanillaBlocks blocks : WoodBlockSets.beams){
            SimplePillarModel.blocks.add(new SimplePillarModel.Pillar(blocks.beam().get()));
            SimpleAxisSlabModel.blocks.add(new SimpleAxisSlabModel.Slab(blocks.texture(), blocks.woodSlab().get()));
            SimpleStairModel.blocks.add(new SimpleStairModel.Stair(blocks.texture(), blocks.woodStairs().get()));
            SimpleWallModel.blocks.add(new SimpleWallModel.Wall(blocks.texture(), blocks.woodWall().get()));
            SimpleFenceModel.blocks.add(new SimpleFenceModel.Fence(blocks.texture(), blocks.woodFence().get()));

            BlockDrops.blocks.add(blocks.beam().get());
            BlockDrops.blocks.add(blocks.woodSlab().get());
            BlockDrops.blocks.add(blocks.woodStairs().get());
            BlockDrops.blocks.add(blocks.woodFence().get());
            BlockDrops.blocks.add(blocks.woodWall().get());

            MineableAxe.blocks.add(blocks.beam().get());
            MineableAxe.blocks.add(blocks.woodSlab().get());
            MineableAxe.blocks.add(blocks.woodStairs().get());
            MineableAxe.blocks.add(blocks.woodFence().get());
            MineableAxe.blocks.add(blocks.woodWall().get());
        }

        for (PaperwallSets.PaperwallSet set : PaperwallSets.paperwallSets){
            SimplePaperWallModel.blocks.add(set.pane().get());
            SilkTouchDrops.blocks.add(set.pane().get());
        }

        for (NotBrickBuildingSets.BuildSet set : NotBrickBuildingSets.buildSets){
            SimpleBlockModel.blocks.add(set.block().get());
            SimpleAxisSlabModel.blocks.add(new SimpleAxisSlabModel.Slab(set.block().get(), set.slab().get()));
            SimpleStairModel.blocks.add(new SimpleStairModel.Stair(set.block().get(), set.stair().get()));

            BlockDrops.blocks.add(set.block().get());
            BlockDrops.blocks.add(set.slab().get());
            BlockDrops.blocks.add(set.stair().get());

            MineablePickaxe.blocks.add(set.block().get());
            MineablePickaxe.blocks.add(set.slab().get());
            MineablePickaxe.blocks.add(set.stair().get());

            RequiresStoneTool.blocks.add(set.block().get());
            RequiresStoneTool.blocks.add(set.slab().get());
            RequiresStoneTool.blocks.add(set.stair().get());

            if (set.wall() != null){
                SimpleWallModel.blocks.add(new SimpleWallModel.Wall(set.block().get(), set.wall().get()));
                RequiresStoneTool.blocks.add(set.wall().get());
                MineablePickaxe.blocks.add(set.wall().get());
                BlockDrops.blocks.add(set.wall().get());
                Walls.walls.add(set.wall().get());
            }
        }

        for (SandBlockSets.SandSet block : SandBlockSets.sandSets){

            if(block.block() != null) {
                SimpleSlabModel.blocks.add(new SimpleSlabModel.Slab(block.block().get(), block.slab().get()));
                SimpleLayerModel.blocks.add(new SimpleLayerModel.Layer(block.block().get(), block.layer().get()));
                SimpleBlockModel.blocks.add(block.block().get());

                if (block.sandStone() != null && block.sandstoneSlab() != null && block.sandstoneStairs() != null){
                    SimpleMultiFaceBlockModel.blocks.add(block.sandStone().get());
                    SimpleMultiFaceSlabModel.blocks.add(new SimpleMultiFaceSlabModel.Slab(block.sandStone().get(), block.sandstoneSlab().get()));
                    SimpleMultiFaceStairModel.blocks.add(new SimpleMultiFaceStairModel.Stair(block.sandStone().get(), block.sandstoneStairs().get()));

                    BlockDrops.blocks.add(block.sandStone().get());
                    BlockDrops.blocks.add(block.sandstoneSlab().get());
                    BlockDrops.blocks.add(block.sandstoneStairs().get());

                    MineablePickaxe.blocks.add(block.sandStone().get());
                    MineablePickaxe.blocks.add(block.sandstoneStairs().get());
                    MineablePickaxe.blocks.add(block.sandstoneSlab().get());

                    RequiresStoneTool.blocks.add(block.sandStone().get());
                    RequiresStoneTool.blocks.add(block.sandstoneSlab().get());
                    RequiresStoneTool.blocks.add(block.sandstoneStairs().get());
                }
                BlockDrops.blocks.add(block.slab().get());
                BlockDrops.blocks.add(block.layer().get());
                BlockDrops.blocks.add(block.block().get());

                MineableShovel.blocks.add(block.block().get());
                MineableShovel.blocks.add(block.layer().get());
                MineableShovel.blocks.add(block.slab().get());

                ReedsPlaceable.blocks.add(block.block().get());

            }else {
                SimpleSlabModel.blocks.add(new SimpleSlabModel.Slab(block.texture(), block.slab().get()));
                SimpleLayerModel.blocks.add(new SimpleLayerModel.Layer(block.texture(), block.layer().get()));

                BlockDrops.blocks.add(block.layer().get());
                BlockDrops.blocks.add(block.slab().get());
            }
        }

        for (FlowerBlockSets.FlowerSet block : FlowerBlockSets.flowerSets){
            if (block.tallFlower() == null){
                SimpleFlowerModel.blocks.add(block.flower().get());
                SimplePottedFlowerModel.blocks.add(new SimplePottedFlowerModel.Potted(block.pottedFlower().get(), block.flower().get()));

                BlockDrops.blocks.add(block.flower().get());
                PottedFlowerDrops.blocks.add(block.pottedFlower().get());
            }else {
                SimpleTallFlowerModel.blocks.add(block.tallFlower().get());
                BlockDrops.blocks.add(block.tallFlower().get());
            }

        }

        for (GrassBlockSets.GrassSet block : GrassBlockSets.grassSets){
            if (block.tallGrass() != null){
                SimpleTallFlowerModel.blocks.add(block.tallGrass().get());
                BlockDrops.blocks.add(block.tallGrass().get());
            }else {
                SimpleFlowerModel.blocks.add(block.grass().get());
                BlockDrops.blocks.add(block.grass().get());
                if (block.pottedGrass() != null){
                    SimplePottedFlowerModel.blocks.add(new SimplePottedFlowerModel.Potted(block.pottedGrass().get(), block.grass().get()));
                    PottedFlowerDrops.blocks.add(block.pottedGrass().get());
                }

            }
        }

    }
}

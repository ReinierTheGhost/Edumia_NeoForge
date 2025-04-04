package com.legends.edumia.blocks;



import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class EdumiaLeavesBlock extends LeavesBlock {

    private Supplier<? extends ParticleOptions> fallingParticle;
    private int fallingChange;
    public EdumiaLeavesBlock() {
        super(Properties.ofFullCopy(Blocks.OAK_LEAVES).strength(0.2f).randomTicks().sound(SoundType.GRASS).noOcclusion());
    }

    public EdumiaLeavesBlock setFallingParticle(Supplier<? extends ParticleOptions> particle, int change){
        this.fallingParticle = particle;
        this.fallingChange = change;
        return this;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        super.animateTick(state, world, pos, random);
        if (this.fallingParticle != null && random.nextInt(this.fallingChange) == 0){
            ParticleOptions particle = this.fallingParticle.get();
            double x = (pos.getX() + random.nextFloat());
            double y = (pos.getY() - 0.05);
            double z = (pos.getZ() + random.nextFloat());
            double xSpeed = Mth.nextFloat(random, -0.1F, 0.1F);
            double ySpeed = Mth.nextFloat(random, -0.03F, -0.01F);
            double zSpeed = Mth.nextFloat(random, -0.1F, 0.1F);
            world.addParticle(particle, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}

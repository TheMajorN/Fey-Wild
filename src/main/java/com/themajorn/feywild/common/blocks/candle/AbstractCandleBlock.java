package com.themajorn.feywild.common.blocks.candle;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class AbstractCandleBlock extends Block {
    public static final int LIGHT_PER_CANDLE = 3;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    protected AbstractCandleBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    protected abstract Iterable<Vector3d> getParticleOffsets(BlockState state);

    @Override
    public void onProjectileHit(World p_220066_1_, BlockState p_220066_2_, BlockRayTraceResult p_220066_3_, ProjectileEntity p_220066_4_) {
        if (!p_220066_1_.isClientSide && p_220066_4_.isOnFire() && this.canBeLit(p_220066_2_)) {
            setLit(p_220066_1_, p_220066_2_, p_220066_3_.getBlockPos(), true);
        }
    }

    public static boolean isLit(BlockState state) {
        return state.hasProperty(LIT) && (state.is(BlockTags.FIRE) || state.is(BlockTags.FIRE)) && state.getValue(LIT);
    }

    protected boolean canBeLit(BlockState state) {
        return !state.getValue(LIT);
    }

    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.getValue(LIT)) {
            this.getParticleOffsets(state).forEach((p_151917_) -> {
                addParticlesAndSound(world, p_151917_.add((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), random);
            });
        }
    }

    private static void addParticlesAndSound(World world, Vector3d vector, Random random) {
        float f = random.nextFloat();
        if (f < 0.3F) {
            world.addParticle(ParticleTypes.SMOKE, vector.x, vector.y, vector.z, 0.0D, 0.0D, 0.0D);
            if (f < 0.17F) {
                //world.playLocalSound(vector.x + 0.5D, vector.y + 0.5D, vector.z + 0.5D, SoundEvents.FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
            }
        }

        world.addParticle(ParticleTypes.FLAME, vector.x, vector.y, vector.z, 0.0D, 0.0D, 0.0D);
    }

    public static void extinguish(@Nullable PlayerEntity player, BlockState state, IWorld world, BlockPos pos) {
        setLit(world, state, pos, false);
        if (state.getBlock() instanceof AbstractCandleBlock) {
            ((AbstractCandleBlock)state.getBlock()).getParticleOffsets(state).forEach((p_151926_) -> {
                world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + p_151926_.x(), (double)pos.getY() + p_151926_.y(), (double)pos.getZ() + p_151926_.z(), 0.0D, (double)0.1F, 0.0D);
            });
        }

        world.playSound((PlayerEntity)null, pos, SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    public static void setLit(IWorld world, BlockState state, BlockPos pos, boolean bool) {
        world.playSound((PlayerEntity)null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 0.2F, 1.0F);
        world.setBlock(pos, state.setValue(LIT, Boolean.valueOf(bool)), 11);
    }
}

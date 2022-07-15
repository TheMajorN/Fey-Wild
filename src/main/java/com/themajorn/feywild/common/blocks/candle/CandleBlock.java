package com.themajorn.feywild.common.blocks.candle;

import com.google.common.collect.ImmutableList;
import com.themajorn.feywild.common.blocks.candle.AbstractCandleBlock;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;


public class CandleBlock extends AbstractCandleBlock implements IWaterLoggable {
    public static final int MIN_CANDLES = 1;
    public static final int MAX_CANDLES = 4;

    public static final IntegerProperty CANDLES_CLASS = IntegerProperty.create("candles", 1, 4);
    public static final IntegerProperty CANDLES = CANDLES_CLASS;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final ToIntFunction<BlockState> LIGHT_EMISSION = (p_152848_) -> {
        return p_152848_.getValue(LIT) ? 3 * p_152848_.getValue(CANDLES) : 0;
    };
    private static final Int2ObjectMap<List<Vector3d>> PARTICLE_OFFSETS = Util.make(() -> {
        Int2ObjectMap<List<Vector3d>> int2objectmap = new Int2ObjectOpenHashMap<>();
        int2objectmap.defaultReturnValue(ImmutableList.of());
        int2objectmap.put(1, ImmutableList.of(new Vector3d(0.5D, 0.5D, 0.5D)));
        int2objectmap.put(2, ImmutableList.of(new Vector3d(0.375D, 0.44D, 0.5D), new Vector3d(0.625D, 0.5D, 0.44D)));
        int2objectmap.put(3, ImmutableList.of(new Vector3d(0.5D, 0.313D, 0.625D), new Vector3d(0.375D, 0.44D, 0.5D), new Vector3d(0.56D, 0.5D, 0.44D)));
        int2objectmap.put(4, ImmutableList.of(new Vector3d(0.44D, 0.313D, 0.56D), new Vector3d(0.625D, 0.44D, 0.56D), new Vector3d(0.375D, 0.44D, 0.375D), new Vector3d(0.56D, 0.5D, 0.375D)));
        return Int2ObjectMaps.unmodifiable(int2objectmap);
    });
    private static final VoxelShape ONE_AABB = Block.box(7.0D, 0.0D, 7.0D, 9.0D, 6.0D, 9.0D);
    private static final VoxelShape TWO_AABB = Block.box(5.0D, 0.0D, 6.0D, 11.0D, 6.0D, 9.0D);
    private static final VoxelShape THREE_AABB = Block.box(5.0D, 0.0D, 6.0D, 10.0D, 6.0D, 11.0D);
    private static final VoxelShape FOUR_AABB = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 10.0D);

    public CandleBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CANDLES, Integer.valueOf(1)).setValue(LIT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
        if (p_225533_4_.abilities.mayBuild && p_225533_4_.getItemInHand(p_225533_5_).isEmpty() && p_225533_1_.getValue(LIT)) {
            extinguish(p_225533_4_, p_225533_1_, p_225533_2_, p_225533_3_);
            return ActionResultType.sidedSuccess(p_225533_2_.isClientSide);
        }
        else if (p_225533_4_.abilities.mayBuild && p_225533_4_.getItemInHand(p_225533_5_).getItem() == Items.FLINT_AND_STEEL && !p_225533_1_.getValue(LIT)) {
            setLit(p_225533_2_, p_225533_1_, p_225533_3_, true);
            return ActionResultType.sidedSuccess(p_225533_2_.isClientSide);
        } else {
            return ActionResultType.PASS;
        }
    }


    @Override
    public boolean canBeReplaced(BlockState state, BlockItemUseContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().getItem() == this.asItem() && state.getValue(CANDLES) < 4 || super.canBeReplaced(state, context);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(this)) {
            return blockstate.cycle(CANDLES);
        } else {
            FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state1, IWorld world, BlockPos pos, BlockPos pos1) {
        if (state.getValue(WATERLOGGED)) {
            world.setBlock(pos, Fluids.WATER.defaultFluidState().createLegacyBlock(), Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, state1, world, pos, pos1);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        switch(state.getValue(CANDLES)) {
            case 1:
            default:
                return ONE_AABB;
            case 2:
                return TWO_AABB;
            case 3:
                return THREE_AABB;
            case 4:
                return FOUR_AABB;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(CANDLES, LIT, WATERLOGGED);
    }

    public boolean placeLiquid(IWorld world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!state.getValue(WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
            BlockState blockstate = state.setValue(WATERLOGGED, Boolean.valueOf(true));
            if (state.getValue(LIT)) {
                extinguish((PlayerEntity) null, blockstate, world, pos);
            } else {
                world.setBlock(pos, blockstate, 3);
            }

            world.setBlock(pos, fluidState.getType().defaultFluidState().createLegacyBlock(), fluidState.getType().getTickDelay(world));
            return true;
        } else {
            return false;
        }
    }
    public static final ITag.INamedTag<Block> CANDLES_TAG = BlockTags.createOptional(Objects.requireNonNull(ResourceLocation.tryParse("candles")));

    public static boolean canLight(BlockState state) {
        return state.is(CANDLES_TAG, (abstractBlockState) -> {
            return abstractBlockState.hasProperty(LIT) && abstractBlockState.hasProperty(WATERLOGGED);
        }) && !state.getValue(LIT) && !state.getValue(WATERLOGGED);
    }

    protected Iterable<Vector3d> getParticleOffsets(BlockState state) {
        return PARTICLE_OFFSETS.get(state.getValue(CANDLES).intValue());
    }

    @Override
    protected boolean canBeLit(BlockState state) {
        return !state.getValue(WATERLOGGED) && super.canBeLit(state);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldReader, BlockPos pos) {
        return Block.canSupportCenter(worldReader, pos.below(), Direction.UP);
    }
}

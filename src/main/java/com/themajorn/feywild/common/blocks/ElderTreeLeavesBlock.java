package com.themajorn.feywild.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ElderTreeLeavesBlock extends LeavesBlock {


    public ElderTreeLeavesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getLightBlock(BlockState p_200011_1_, IBlockReader p_200011_2_, BlockPos p_200011_3_) {
        return 4;
    }
}

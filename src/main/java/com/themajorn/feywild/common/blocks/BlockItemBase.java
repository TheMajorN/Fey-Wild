package com.themajorn.feywild.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block) {
        super(block, new Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS));
    }
}

package com.themajorn.feywild.common.tileentities;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TapTileEntity extends TileEntity implements ITickableTileEntity {
    public TapTileEntity(TileEntityType<?> type) {
        super(type);
    }

    @Override
    public void tick() {

    }
}

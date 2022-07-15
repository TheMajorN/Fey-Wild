package com.themajorn.feywild.core.util;

import com.themajorn.feywild.FeyWild;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class DimensionInit {

    public static RegistryKey<World> FWDIM = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(FeyWild.MOD_ID, "fwdim"));

}

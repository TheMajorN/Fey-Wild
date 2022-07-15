package com.themajorn.feywild.core.util;

import com.themajorn.feywild.FeyWild;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VillagerInit {

    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES,
            FeyWild.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS,
            FeyWild.MOD_ID);

    // public static final RegistryObject<PointOfInterestType> MINER_POI = POI_TYPES.register("miner_poi",
    //        () -> new PointOfInterestType("miner_poi", PointOfInterestType.forState(BlockInit.TEST.get()), 1, 1));

}

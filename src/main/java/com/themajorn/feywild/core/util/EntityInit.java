package com.themajorn.feywild.core.util;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.AlmirajEntity;
import com.themajorn.feywild.common.entities.BlinkDogEntity;
import com.themajorn.feywild.common.entities.brigganock.BrigganockEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, FeyWild.MOD_ID);

    public static final RegistryObject<EntityType<AlmirajEntity>> ALMIRAJ = ENTITIES.register("almiraj",
            () -> EntityType.Builder.of(AlmirajEntity::new, EntityClassification.CREATURE)
                    .sized(0.8F, 0.6F)
                    .build(new ResourceLocation(FeyWild.MOD_ID, "almiraj").toString()));

    public static final RegistryObject<EntityType<BlinkDogEntity>> BLINK_DOG = ENTITIES.register("blink_dog",
            () -> EntityType.Builder.of(BlinkDogEntity::new, EntityClassification.CREATURE)
                    .sized(1.2F, 1.0F)
                    .build(new ResourceLocation(FeyWild.MOD_ID, "blink_dog").toString()));

    public static final RegistryObject<EntityType<BrigganockEntity>> BRIGGANOCK = ENTITIES.register("brigganock",
            () -> EntityType.Builder.of(BrigganockEntity::new, EntityClassification.MONSTER)
                    .sized(1.2F, 1.0F)
                    .build(new ResourceLocation(FeyWild.MOD_ID, "brigganock").toString()));


}

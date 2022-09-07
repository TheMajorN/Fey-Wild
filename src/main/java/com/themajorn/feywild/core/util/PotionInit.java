package com.themajorn.feywild.core.util;

import com.themajorn.feywild.FeyWild;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, FeyWild.MOD_ID);

    public static final RegistryObject<Potion> MINOR_HEALING = POTIONS.register("minor_healing",
            () -> new Potion(new EffectInstance(Effects.HEAL, 1)));

}

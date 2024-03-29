package com.themajorn.feywild.core.util;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.blocks.BlockItemBase;
import com.themajorn.feywild.common.items.Antitoxin;
import com.themajorn.feywild.common.items.MuroosaBalm;
import com.themajorn.feywild.common.items.WillowshadeOil;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FeyWild.MOD_ID);

    // === FOOD === //
    public static final RegistryObject<Item> WILLOWSHADE_FRUIT = ITEMS.register("willowshade_fruit",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD)
                    .food(new Food.Builder().nutrition(1).saturationMod(1.0F).build())));

    public static final RegistryObject<Item> FIRE_PEAS = ITEMS.register("fire_peas",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD)
                    .food(new Food.Builder().nutrition(0).saturationMod(0.5F).build())));

    // === SALVES / BALMS / TINCTURES === //
    public static final RegistryObject<Item> VIAL = ITEMS.register("vial",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_BREWING)));

    public static final RegistryObject<Item> MUROOSA_BALM = ITEMS.register("muroosa_balm",
            () -> new MuroosaBalm(new Item.Properties().tab(ItemGroup.TAB_BREWING)));

    public static final RegistryObject<Item> ANTITOXIN = ITEMS.register("antitoxin",
            () -> new Antitoxin(new Item.Properties().tab(ItemGroup.TAB_BREWING)));

    public static final RegistryObject<Item> MINOR_HEALING_POTION = ITEMS.register("minor_healing_potion",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_BREWING)));

    public static final RegistryObject<Item> WILLOWSHADE_OIL = ITEMS.register("willowshade_oil",
            () -> new WillowshadeOil(new Item.Properties().tab(ItemGroup.TAB_BREWING)));

    // === INGREDIENTS === //
    public static final RegistryObject<Item> QUIPPER_SCALE = ITEMS.register("quipper_scale",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_BREWING)));

    public static final RegistryObject<Item> MUROOSA_TWIG = ITEMS.register("muroosa_twig",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_BREWING)));

    // === GEMS === //
    public static final RegistryObject<Item> ONYX = ITEMS.register("onyx",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> OPAL = ITEMS.register("opal",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> GARNET = ITEMS.register("garnet",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));

    // === BLOCK ITEMS === //
    public static final RegistryObject<Item> BOOK_STACK_ITEM = ITEMS.register("book_stack",
            () -> new BlockItemBase(BlockInit.BOOK_STACK.get()));

    public static final RegistryObject<Item> BOOK_STACK_SHORT_ITEM = ITEMS.register("book_stack_short",
            () -> new BlockItemBase(BlockInit.BOOK_STACK_SHORT.get()));

    public static final RegistryObject<Item> CANDLE_ITEM = ITEMS.register("candle",
            () -> new BlockItemBase(BlockInit.CANDLE.get()));

    public static final RegistryObject<Item> SPRUCE_BOOKSHELF_1_ITEM = ITEMS.register("spruce_bookshelf_1",
            () -> new BlockItemBase(BlockInit.SPRUCE_BOOKSHELF_1.get()));

    public static final RegistryObject<Item> SPRUCE_BOOKSHELF_2_ITEM = ITEMS.register("spruce_bookshelf_2",
            () -> new BlockItemBase(BlockInit.SPRUCE_BOOKSHELF_2.get()));

    public static final RegistryObject<Item> SPRUCE_BOOKSHELF_3_ITEM = ITEMS.register("spruce_bookshelf_3",
            () -> new BlockItemBase(BlockInit.SPRUCE_BOOKSHELF_3.get()));

    public static final RegistryObject<Item> RED_AMANITA_ITEM = ITEMS.register("red_amanita_mushroom",
            () -> new BlockItemBase(BlockInit.RED_AMANITA_MUSHROOM.get()));

    public static final RegistryObject<Item> FLAME_LILLY_ITEM = ITEMS.register("flame_lilly",
            () -> new BlockItemBase(BlockInit.FLAME_LILLY.get()));

}

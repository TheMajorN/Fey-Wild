package com.themajorn.feywild.core.util;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.blocks.BookStackBlock;
import com.themajorn.feywild.common.blocks.BookStackShortBlock;
import com.themajorn.feywild.common.blocks.candle.CandleBlock;
import com.themajorn.feywild.common.blocks.ElderTreeLeavesBlock;
import com.themajorn.feywild.common.trees.ElderTree;
import com.themajorn.feywild.common.trees.RowanTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FeyWild.MOD_ID);




    // === CLUTTER === //
    public static final RegistryObject<Block> BOOK_STACK = BLOCKS.register("book_stack",
            () -> new BookStackBlock(AbstractBlock.Properties.of(Material.SPONGE)));

    public static final RegistryObject<Block> BOOK_STACK_SHORT = BLOCKS.register("book_stack_short",
            () -> new BookStackShortBlock(AbstractBlock.Properties.of(Material.SPONGE)));

    public static final RegistryObject<Block> CANDLE = BLOCKS.register("candle",
            () -> new CandleBlock(AbstractBlock.Properties.of(Material.TOP_SNOW).instabreak().lightLevel(CandleBlock.LIGHT_EMISSION)));

    public static final RegistryObject<Block> SPRUCE_BOOKSHELF_1 = BLOCKS.register("spruce_bookshelf_1",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.BOOKSHELF)));

    public static final RegistryObject<Block> SPRUCE_BOOKSHELF_2 = BLOCKS.register("spruce_bookshelf_2",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.BOOKSHELF)));

    public static final RegistryObject<Block> SPRUCE_BOOKSHELF_3 = BLOCKS.register("spruce_bookshelf_3",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.BOOKSHELF)));


    // === TREES === //
    public static final RegistryObject<Block> ELDER_LOG = BLOCKS.register("elder_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> ELDER_WOOD = BLOCKS.register("elder_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_ELDER_LOG = BLOCKS.register("stripped_elder_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_ELDER_WOOD = BLOCKS.register("stripped_elder_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> ELDER_PLANKS = BLOCKS.register("elder_planks",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> ELDER_LEAVES = BLOCKS.register("elder_leaves",
            () -> new ElderTreeLeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2f)
                    .randomTicks().sound(SoundType.GRASS).noOcclusion()));

    public static final RegistryObject<Block> ELDER_SAPLING = BLOCKS.register("elder_sapling",
            () -> new SaplingBlock(new ElderTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> ROWAN_LOG = BLOCKS.register("rowan_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> ROWAN_WOOD = BLOCKS.register("rowan_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_ROWAN_LOG = BLOCKS.register("stripped_rowan_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_ROWAN_WOOD = BLOCKS.register("stripped_rowan_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> ROWAN_PLANKS = BLOCKS.register("rowan_planks",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> ROWAN_LEAVES = BLOCKS.register("rowan_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2f)
                    .randomTicks().sound(SoundType.GRASS).noOcclusion()));

    public static final RegistryObject<Block> ROWAN_SAPLING = BLOCKS.register("rowan_sapling",
            () -> new SaplingBlock(new RowanTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));


}

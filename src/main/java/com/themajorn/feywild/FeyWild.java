package com.themajorn.feywild;

import com.themajorn.feywild.client.entity.renderers.AlmirajRenderer;
import com.themajorn.feywild.client.entity.renderers.BlinkDogRenderer;
import com.themajorn.feywild.client.entity.renderers.BrigganockRenderer;
import com.themajorn.feywild.core.biomes.BiomeGeneration;
import com.themajorn.feywild.core.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

@Mod(FeyWild.MOD_ID)
public class FeyWild {

    public static final String MOD_ID = "feywild";
    private static final Logger LOGGER = LogManager.getLogger();
    public FeyWild() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        BiomeInit.BIOMES.register(bus);
        EntityInit.ENTITIES.register(bus);
        VillagerInit.POI_TYPES.register(bus);
        VillagerInit.VILLAGER_PROFESSIONS.register(bus);
        StructureInit.STRUCTURES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();

    }

    private void setup(final FMLCommonSetupEvent event) {
        BiomeGeneration.generateBiomes();
        StructureInit.setupStructures();

        LOGGER.info("HEY, MINECRAFT IS WORKING! GOING UP^^^^^^");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.ALMIRAJ.get(), AlmirajRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BLINK_DOG.get(), BlinkDogRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BRIGGANOCK.get(), BrigganockRenderer::new);

        RenderTypeLookup.setRenderLayer(BlockInit.BOOK_STACK.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.BOOK_STACK_SHORT.get(), RenderType.cutout());
    }
}

/*
Wood, bark, or the fruit from the rowan tree
are good for dampening fey magics. They’re
also good for protecting buildings, tools,
and crops against fey enchantments.

The rowan tree is a small-ish hardwood tree. It
grows mainly in temperate regions of the world.
These trees can grow up to 40 feet tall. And,
they bear small, red or white berry-like fruits
(that aren’t technically berries) with five-petalled flowers.

Brigganock village
 */


/*
JAC WRITES HERE!!!
I love you!! >3
Maybe we can make the book pages a little less shockingly white?



 */
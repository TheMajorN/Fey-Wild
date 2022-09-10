package com.themajorn.feywild;

import com.themajorn.feywild.client.entity.renderers.*;
import com.themajorn.feywild.core.biomes.BiomeGeneration;
import com.themajorn.feywild.core.util.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(FeyWild.MOD_ID)
public class FeyWild {

    public static final String MOD_ID = "feywild";
    public static final Logger LOGGER = LogManager.getLogger();
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
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.QUIPPER.get(), QuipperRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.DRYAD.get(), DryadRenderer::new);

        RenderTypeLookup.setRenderLayer(BlockInit.BOOK_STACK.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.BOOK_STACK_SHORT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.FLAME_LILLY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.RED_AMANITA_MUSHROOM.get(), RenderType.cutout());
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

//===============STRUCTURES==================//
STRUCTURES:
Brigganock village
Eladrin Ruins
Temple Ruins
Fey Temple
Eladrin Village
Fey Garden
Shrine
Bridge
Fey Farm

//===============PLANTS==================//
Healing Plants/Salves
Mortar & Pestle

HERBAL INGREDIENTS:
Potion of Healing:
Red Amanita Mushroom

Muroosa Balm (Fire Resistance):
Flame Lilly
Muroosa twigs

Antitoxin:
Everfrost Berry
Cat’s Tongue

Willowshade Oil (Slow / Freeze Resistance):
Willowshade Fruit

Tea of Refreshment (Remove exhaustion):
Morning Dew
Cat's Tongue

Potion of Animal Friendship:
Cat's Tongue
Quipper Scale

Pepper Peppers:
Fire peas

JAC WRITES HERE!!!
I love you!! >3

 */
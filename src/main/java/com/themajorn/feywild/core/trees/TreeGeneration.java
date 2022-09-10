package com.themajorn.feywild.core.trees;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class TreeGeneration {
    public static void generateTrees(final BiomeLoadingEvent event) {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.FOREST)) {
            List<Supplier<ConfiguredFeature<?, ?>>> base =
                    event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> TreeConfiguredFeatures.ELDER
                    .decorated(Features.Placements.HEIGHTMAP)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1, 0.25f, 2))));

            base.add(() -> TreeConfiguredFeatures.ROWAN
                    .decorated(Features.Placements.HEIGHTMAP)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1, 0.25f, 2))));

            base.add(() -> TreeConfiguredFeatures.WILLOWSHADE
                    .decorated(Features.Placements.HEIGHTMAP)
                    .decorated(Placement.COUNT_EXTRA.configured(
                            new AtSurfaceWithExtraConfig(1, 0.25f, 2))));
        }
    }

}

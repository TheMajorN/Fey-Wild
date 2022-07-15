package com.themajorn.feywild.core.trees;

import com.themajorn.feywild.core.util.BlockInit;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.JungleFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;

public class TreeConfiguredFeatures {

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ELDER =
            register("elder", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockInit.ELDER_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BlockInit.ELDER_LEAVES.get().defaultBlockState()),
                            //p_242253_0_ - offset from trunk horizontally | p_242253_1_ - offset from trunk vertically
                            new JungleFoliagePlacer(FeatureSpread.of(2, 2), FeatureSpread.of(0, 1), 2),
                            new DarkOakTrunkPlacer(14, 5, 6),
                            new TwoLayerFeature(10, 0, 10))).ignoreVines().build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ROWAN =
            register("rowan", Feature.TREE.configured((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockInit.ROWAN_LOG.get().defaultBlockState()),
                            new SimpleBlockStateProvider(BlockInit.ROWAN_LEAVES.get().defaultBlockState()),
                            //p_242253_0_ - offset from trunk horizontally | p_242253_1_ - offset from trunk vertically
                            new FancyFoliagePlacer(FeatureSpread.of(2, 2), FeatureSpread.of(0, 1), 2),
                            new ForkyTrunkPlacer(8, 5, 6),
                            new TwoLayerFeature(10, 0, 10))).ignoreVines().build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key,
                                                                                 ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}

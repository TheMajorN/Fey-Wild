package com.themajorn.feywild.core.util;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.core.biomes.ConfiguredSurfaceBuilders;
import net.minecraft.client.audio.BackgroundMusicTracks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BiomeInit {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, FeyWild.MOD_ID);

    public static final RegistryObject<Biome> FEY_FOREST_BIOME = BIOMES.register("fey_forest",
            () -> makeFeyForest(() -> ConfiguredSurfaceBuilders.FEY_FOREST_SURFACE, 0.055f, 0.08f));

    private static Biome makeFeyForest(final Supplier<ConfiguredSurfaceBuilder<?>> surfaceBuilder, float depth, float scale) {
        MobSpawnInfo.Builder mobSpawn = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.farmAnimals(mobSpawn);
        DefaultBiomeFeatures.commonSpawns(mobSpawn);

        BiomeGenerationSettings.Builder biomeSurfaceGenerator =
                (new BiomeGenerationSettings.Builder()).surfaceBuilder(surfaceBuilder);

        biomeSurfaceGenerator.addStructureStart(StructureFeatures.MINESHAFT);
        biomeSurfaceGenerator.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);
        biomeSurfaceGenerator.addStructureStart(StructureFeatures.WOODLAND_MANSION);

        DefaultBiomeFeatures.addDefaultCarvers(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addJungleEdgeTrees(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addForestFlowers(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addForestGrass(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addDefaultLakes(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addDefaultMonsterRoom(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addMossyStoneBlock(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addDefaultOres(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addSwampClayDisk(biomeSurfaceGenerator);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeSurfaceGenerator);

        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(depth).scale(scale)
                .temperature(1.0F).downfall(1.4F).specialEffects((new BiomeAmbience.Builder()).waterColor(0x0065d1).waterFogColor(-16777216)
                        .fogColor(0x8edfd5).skyColor(getSkyColorWithTemperatureModifier(0.5F)).foliageColorOverride(0x00830c).grassColorOverride(0x00830c)
                        .ambientParticle(new ParticleEffectAmbience(ParticleTypes.END_ROD, 0.0000004f)).skyColor(0x2afafd)
                        .backgroundMusic(BackgroundMusicTracks.createGameMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST))
                        .build())
                .mobSpawnSettings(mobSpawn.build()).generationSettings(biomeSurfaceGenerator.build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.2460909F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}

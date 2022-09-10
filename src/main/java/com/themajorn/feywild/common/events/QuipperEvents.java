package com.themajorn.feywild.common.events;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.quipper.QuipperEntity;
import com.themajorn.feywild.core.util.EntityInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = FeyWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class QuipperEvents {

    @SubscribeEvent
    public void attackEntitiesTrigger(LivingHurtEvent event) {
        LivingEntity entity = event.getEntityLiving();
        BlockPos pos = event.getEntityLiving().blockPosition();
        World world = event.getEntityLiving().level;
        AxisAlignedBB blockAABB = new AxisAlignedBB(pos).inflate(20, 20, 20);

        assert world != null;
        List<QuipperEntity> entities = world.getEntities
                (EntityInit.QUIPPER.get(), blockAABB, quipperEntity -> true);

        if (entities.size() > 0 && entity.isInWater()) {
            FeyWild.LOGGER.info("You've frenzied the quippers!");
        }
    }
}

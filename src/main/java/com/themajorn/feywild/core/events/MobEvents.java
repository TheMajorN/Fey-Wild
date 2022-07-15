package com.themajorn.feywild.core.events;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.AlmirajEntity;
import com.themajorn.feywild.common.entities.BlinkDogEntity;
import com.themajorn.feywild.core.util.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FeyWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {


    @SubscribeEvent
    public static void entityAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityInit.ALMIRAJ.get(), AlmirajEntity.setAttributes().build());
        event.put(EntityInit.BLINK_DOG.get(), BlinkDogEntity.setAttributes().build());

    }

}

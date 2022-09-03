package com.themajorn.feywild.client.events;

import com.themajorn.feywild.FeyWild;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FeyWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiranhaEvents {

    public void attackEntitiesTrigger(LivingHurtEvent event) {

    }

}

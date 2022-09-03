package com.themajorn.feywild.client.events;

import com.themajorn.feywild.FeyWild;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FeyWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DryadEvents {

    @SubscribeEvent
    public void dryadAnger(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();
        PlayerEntity player = event.getPlayer();

        if (block == Blocks.OAK_WOOD) {

        }
    }

}

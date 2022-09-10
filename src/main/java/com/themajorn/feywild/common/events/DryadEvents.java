package com.themajorn.feywild.common.events;

import com.themajorn.feywild.FeyWild;
import com.themajorn.feywild.common.entities.DryadEntity;
import com.themajorn.feywild.core.util.EntityInit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.client.renderer.entity.model.PhantomModel;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = FeyWild.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DryadEvents {
    @SubscribeEvent
    public void dryadAnger(BlockEvent.BreakEvent event) {
        Block block = event.getState().getBlock();
        World world = (World) event.getWorld();
        BlockPos pos = event.getPos();

        AxisAlignedBB blockAABB = new AxisAlignedBB(pos).inflate(20, 20, 20);

        assert world != null;
        List<DryadEntity> entities = world.getEntities
                (EntityInit.DRYAD.get(), blockAABB, dryadEntity -> true);

        if (block.is(Blocks.OAK_LOG) || block.is(Blocks.SPRUCE_LOG) || block.is(Blocks.JUNGLE_LOG) && entities.size() > 0) {
            FeyWild.LOGGER.info("You've angered a dryad!");
        }
    }



}

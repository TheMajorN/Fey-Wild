package com.themajorn.feywild.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MuroosaBalm extends Item {
    public MuroosaBalm(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 120, 1));
            player.getCooldowns().addCooldown(this, 40);
            player.playSound(SoundEvents.BEEHIVE_SHEAR, 1.0F, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResult.sidedSuccess(itemstack, world.isClientSide());
    }
}

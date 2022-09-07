package com.themajorn.feywild.common.items;

import com.themajorn.feywild.core.util.ItemInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DrinkHelper;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Antitoxin extends Item {
    public Antitoxin(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World world, LivingEntity entity) {
        if (!world.isClientSide) entity.removeEffect(Effects.POISON); // FORGE - move up so stack.shrink does not turn stack into air

        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, itemStack);
            serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
        }

        if (entity instanceof PlayerEntity && !((PlayerEntity)entity).abilities.instabuild) {
            itemStack.shrink(1);
        }

        return itemStack.isEmpty() ? new ItemStack(ItemInit.VIAL.get()) : itemStack;
    }

    public int getUseDuration(ItemStack itemStack) {
        return 16;
    }

    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.DRINK;
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return DrinkHelper.useDrink(world, player, hand);
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @javax.annotation.Nullable net.minecraft.nbt.CompoundNBT nbt) {
        return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
    }
}

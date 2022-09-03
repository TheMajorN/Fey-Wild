package com.themajorn.feywild.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.UUID;

public class PiranhaEntity extends AbstractGroupFishEntity implements IAnimatable, IFrenzied {

    private AnimationFactory factory = new AnimationFactory(this);

    public PiranhaEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.JUMP_STRENGTH, 2.0)
                .add(Attributes.ATTACK_DAMAGE, 0.5);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.piranha.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.piranha.idle", true));
        return PlayState.CONTINUE;

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    protected ItemStack getBucketItemStack() {
        return null;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public int getRemainingPersistentFrenzyTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentFrenzyTime(int p_230260_1_) {

    }

    @Nullable
    @Override
    public UUID getPersistentFrenzyTarget() {
        return null;
    }

    @Override
    public void setPersistentFrenzyTarget(@Nullable UUID p_230259_1_) {

    }

    @Override
    public void startPersistentFrenzyTimer() {

    }
}

package com.themajorn.feywild.common.entities.quipper;

import com.themajorn.feywild.common.entities.quipper.IFrenzied;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Predicate;

public class QuipperEntity extends AbstractGroupFishEntity implements IAnimatable, IFrenzied {

    private static final RangedInteger PERSISTENT_FRENZY_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;
    private int remainingPersistentFrenzyTime;


    private AnimationFactory factory = new AnimationFactory(this);


    public QuipperEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(5, new FollowSchoolLeaderGoal(this));
        this.goalSelector.addGoal(6, new SwimGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 1.2)
                .add(Attributes.JUMP_STRENGTH, 2.0)
                .add(Attributes.ATTACK_DAMAGE, 0.5);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.quipper.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.quipper.idle", true));
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
    public void readAdditionalSaveData(CompoundNBT nbt) {
        super.readAdditionalSaveData(nbt);
        if(!level.isClientSide)
            this.readPersistentFrenzySaveData((ServerWorld)this.level, nbt);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT nbt) {
        super.addAdditionalSaveData(nbt);
        this.addPersistentFrenzySaveData(nbt);
    }

    @Override
    public int getRemainingPersistentFrenzyTime() {
        return this.remainingPersistentFrenzyTime;
    }

    @Override
    public void setRemainingPersistentFrenzyTime(int time) {
        this.remainingPersistentFrenzyTime = time;
    }

    @Nullable
    @Override
    public UUID getPersistentFrenzyTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentFrenzyTarget(@Nullable UUID target) {
        this.persistentAngerTarget = target;
    }

    @Override
    public void startPersistentFrenzyTimer() {
        this.setRemainingPersistentFrenzyTime(PERSISTENT_FRENZY_TIME.randomValue(this.random));

    }
}

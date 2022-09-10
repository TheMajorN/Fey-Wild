package com.themajorn.feywild.common.entities.quipper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public interface IFrenzied {
    
        int getRemainingPersistentFrenzyTime();

        void setRemainingPersistentFrenzyTime(int time);

        @Nullable
        UUID getPersistentFrenzyTarget();

        void setPersistentFrenzyTarget(@Nullable UUID uuid);

        void startPersistentFrenzyTimer();

        default void addPersistentFrenzySaveData(CompoundNBT compoundNBT) {
            compoundNBT.putInt("FrenzyTime", this.getRemainingPersistentFrenzyTime());
            if (this.getPersistentFrenzyTarget() != null) {
                compoundNBT.putUUID("FrenziedAt", this.getPersistentFrenzyTarget());
            }

        }

        default void readPersistentFrenzySaveData(ServerWorld serverWorld, CompoundNBT compoundNBT) {
            this.setRemainingPersistentFrenzyTime(compoundNBT.getInt("FrenzyTime"));
            if (!compoundNBT.hasUUID("FrenziedAt")) {
                this.setPersistentFrenzyTarget((UUID)null);
            } else {
                UUID uuid = compoundNBT.getUUID("FrenziedAt");
                this.setPersistentFrenzyTarget(uuid);
                Entity entity = serverWorld.getEntity(uuid);
                if (entity != null) {
                    if (entity instanceof MobEntity) {
                        this.setLastHurtByMob((MobEntity)entity);
                    }

                    if (entity.getType() == EntityType.PLAYER) {
                        this.setLastHurtByPlayer((PlayerEntity)entity);
                    }

                }
            }
        }

        default void updatePersistentFrenzy(ServerWorld serverWorld, boolean frenzyBool) {
            LivingEntity livingentity = this.getTarget();
            UUID uuid = this.getPersistentFrenzyTarget();
            if ((livingentity == null || livingentity.isDeadOrDying()) && uuid != null && serverWorld.getEntity(uuid) instanceof MobEntity) {
                this.stopBeingFrenzied();
            } else {
                if (livingentity != null && !Objects.equals(uuid, livingentity.getUUID())) {
                    this.setPersistentFrenzyTarget(livingentity.getUUID());
                    this.startPersistentFrenzyTimer();
                }

                if (this.getRemainingPersistentFrenzyTime() > 0 && (livingentity == null || livingentity.getType() != EntityType.PLAYER || !frenzyBool)) {
                    this.setRemainingPersistentFrenzyTime(this.getRemainingPersistentFrenzyTime() - 1);
                    if (this.getRemainingPersistentFrenzyTime() == 0) {
                        this.stopBeingFrenzied();
                    }
                }

            }
        }

        default boolean isFrenziedAt(LivingEntity entity) {
            if (!EntityPredicates.ATTACK_ALLOWED.test(entity)) {
                return false;
            } else {
                return entity.getType() == EntityType.PLAYER
                        && entity.isInWater()
                        && entity.hurt(DamageSource.GENERIC, 0.5F)
                        && this.isFrenziedAtAllPlayers(entity.level) || entity.getUUID().equals(this.getPersistentFrenzyTarget());
            }
        }

        default boolean isFrenziedAtAllPlayers(World world) {
            return world.getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER) && this.isFrenzied() && this.getPersistentFrenzyTarget() == null;
        }

        default boolean isFrenzied() {
            return this.getRemainingPersistentFrenzyTime() > 0;
        }

        default void playerDied(PlayerEntity player) {
            if (player.level.getGameRules().getBoolean(GameRules.RULE_FORGIVE_DEAD_PLAYERS)) {
                if (player.getUUID().equals(this.getPersistentFrenzyTarget())) {
                    this.stopBeingFrenzied();
                }
            }
        }

        default void forgetCurrentTargetAndRefreshUniversalFrenzy() {
            this.stopBeingFrenzied();
            this.startPersistentFrenzyTimer();
        }

        default void stopBeingFrenzied() {
            this.setLastHurtByMob((LivingEntity)null);
            this.setPersistentFrenzyTarget((UUID)null);
            this.setTarget((LivingEntity)null);
            this.setRemainingPersistentFrenzyTime(0);
        }

        void setLastHurtByMob(@Nullable LivingEntity livingEntity);

        void setLastHurtByPlayer(@Nullable PlayerEntity player);

        void setTarget(@Nullable LivingEntity livingEntity);

        @Nullable
        LivingEntity getTarget();
}
    

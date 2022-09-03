package com.themajorn.feywild.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

public interface IFrenzied {
    
        int getRemainingPersistentFrenzyTime();

        void setRemainingPersistentFrenzyTime(int p_230260_1_);

        @Nullable
        UUID getPersistentFrenzyTarget();

        void setPersistentFrenzyTarget(@Nullable UUID p_230259_1_);

        void startPersistentFrenzyTimer();

        default void addPersistentFrenzySaveData(CompoundNBT p_233682_1_) {
            p_233682_1_.putInt("FrenzyTime", this.getRemainingPersistentFrenzyTime());
            if (this.getPersistentFrenzyTarget() != null) {
                p_233682_1_.putUUID("FrenziedAt", this.getPersistentFrenzyTarget());
            }

        }

        default void readPersistentFrenzySaveData(ServerWorld p_241358_1_, CompoundNBT p_241358_2_) {
            this.setRemainingPersistentFrenzyTime(p_241358_2_.getInt("FrenzyTime"));
            if (!p_241358_2_.hasUUID("FrenziedAt")) {
                this.setPersistentFrenzyTarget((UUID)null);
            } else {
                UUID uuid = p_241358_2_.getUUID("FrenziedAt");
                this.setPersistentFrenzyTarget(uuid);
                Entity entity = p_241358_1_.getEntity(uuid);
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

        default void updatePersistentFrenzy(ServerWorld serverWorld, boolean p_241359_2_) {
            LivingEntity livingentity = this.getTarget();
            UUID uuid = this.getPersistentFrenzyTarget();
            if ((livingentity == null || livingentity.isDeadOrDying()) && uuid != null && serverWorld.getEntity(uuid) instanceof MobEntity) {
                this.stopBeingFrenzied();
            } else {
                if (livingentity != null && !Objects.equals(uuid, livingentity.getUUID())) {
                    this.setPersistentFrenzyTarget(livingentity.getUUID());
                    this.startPersistentFrenzyTimer();
                }

                if (this.getRemainingPersistentFrenzyTime() > 0 && (livingentity == null || livingentity.getType() != EntityType.PLAYER || !p_241359_2_)) {
                    this.setRemainingPersistentFrenzyTime(this.getRemainingPersistentFrenzyTime() - 1);
                    if (this.getRemainingPersistentFrenzyTime() == 0) {
                        this.stopBeingFrenzied();
                    }
                }

            }
        }

        default boolean isFrenziedAt(LivingEntity p_233680_1_) {
            if (!EntityPredicates.ATTACK_ALLOWED.test(p_233680_1_)) {
                return false;
            } else {
                return p_233680_1_.getType() == EntityType.PLAYER && this.isFrenziedAtAllPlayers(p_233680_1_.level) ? true : p_233680_1_.getUUID().equals(this.getPersistentFrenzyTarget());
            }
        }

        default boolean isFrenziedAtAllPlayers(World p_241357_1_) {
            return p_241357_1_.getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER) && this.isFrenzied() && this.getPersistentFrenzyTarget() == null;
        }

        default boolean isFrenzied() {
            return this.getRemainingPersistentFrenzyTime() > 0;
        }

        default void playerDied(PlayerEntity p_233681_1_) {
            if (p_233681_1_.level.getGameRules().getBoolean(GameRules.RULE_FORGIVE_DEAD_PLAYERS)) {
                if (p_233681_1_.getUUID().equals(this.getPersistentFrenzyTarget())) {
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

        void setLastHurtByMob(@Nullable LivingEntity p_70604_1_);

        void setLastHurtByPlayer(@Nullable PlayerEntity p_230246_1_);

        void setTarget(@Nullable LivingEntity p_70624_1_);

        @Nullable
        LivingEntity getTarget();
}
    

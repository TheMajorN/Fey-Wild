package com.themajorn.feywild.common.entities.brigganock;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
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
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BrigganockEntity extends AbstractBrigganockEntity implements IAnimatable, ICrossbowUser {

    private AnimationFactory factory = new AnimationFactory(this);

    public BrigganockEntity(EntityType<? extends AbstractBrigganockEntity> brigganock, World world) {
        super(brigganock, world);
    }

    private static final DataParameter<Boolean> DATA_BABY_ID = EntityDataManager.defineId(BrigganockEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> DATA_IS_CHARGING_CROSSBOW = EntityDataManager.defineId(BrigganockEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> DATA_IS_DANCING = EntityDataManager.defineId(BrigganockEntity.class, DataSerializers.BOOLEAN);
    private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("766bfa64-11f3-11ea-8d71-362b9e155667");
    private static final AttributeModifier SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speed boost", (double)0.2F, AttributeModifier.Operation.MULTIPLY_BASE);
    private final Inventory inventory = new Inventory(8);
    private boolean cannotHunt = false;
    protected static final ImmutableList<SensorType<? extends Sensor<? super BrigganockEntity>>> SENSOR_TYPES = ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ITEMS, SensorType.HURT_BY, SensorType.PIGLIN_SPECIFIC_SENSOR);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(MemoryModuleType.LOOK_TARGET, MemoryModuleType.DOORS_TO_CLOSE, MemoryModuleType.LIVING_ENTITIES, MemoryModuleType.VISIBLE_LIVING_ENTITIES, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, MemoryModuleType.NEARBY_ADULT_PIGLINS, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryModuleType.HURT_BY, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.INTERACTION_TARGET, MemoryModuleType.PATH, MemoryModuleType.ANGRY_AT, MemoryModuleType.UNIVERSAL_ANGER, MemoryModuleType.AVOID_TARGET, MemoryModuleType.ADMIRING_ITEM, MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM, MemoryModuleType.ADMIRING_DISABLED, MemoryModuleType.DISABLE_WALK_TO_ADMIRE_ITEM, MemoryModuleType.CELEBRATE_LOCATION, MemoryModuleType.DANCING, MemoryModuleType.HUNTED_RECENTLY, MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, MemoryModuleType.RIDE_TARGET, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN, MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD, MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM, MemoryModuleType.ATE_RECENTLY, MemoryModuleType.NEAREST_REPELLENT);

    public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        if (this.isBaby()) {
            p_213281_1_.putBoolean("IsBaby", true);
        }

        if (this.cannotHunt) {
            p_213281_1_.putBoolean("CannotHunt", true);
        }

        p_213281_1_.put("Inventory", this.inventory.createTag());
    }

    public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setBaby(p_70037_1_.getBoolean("IsBaby"));
        this.setCannotHunt(p_70037_1_.getBoolean("CannotHunt"));
        this.inventory.fromTag(p_70037_1_.getList("Inventory", 10));
    }

    protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
        this.inventory.removeAllItems().forEach(this::spawnAtLocation);
    }

    protected ItemStack addToInventory(ItemStack p_234436_1_) {
        return this.inventory.addItem(p_234436_1_);
    }

    protected boolean canAddToInventory(ItemStack p_234437_1_) {
        return this.inventory.canAddItem(p_234437_1_);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_BABY_ID, false);
        this.entityData.define(DATA_IS_CHARGING_CROSSBOW, false);
        this.entityData.define(DATA_IS_DANCING, false);
    }

    public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
        super.onSyncedDataUpdated(p_184206_1_);
        if (DATA_BABY_ID.equals(p_184206_1_)) {
            this.refreshDimensions();
        }

    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, 16.0D).add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    public static boolean checkPiglinSpawnRules(EntityType<BrigganockEntity> p_234418_0_, IWorld p_234418_1_, SpawnReason p_234418_2_, BlockPos p_234418_3_, Random p_234418_4_) {
        return !p_234418_1_.getBlockState(p_234418_3_.below()).is(Blocks.NETHER_WART_BLOCK);
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        if (p_213386_3_ != SpawnReason.STRUCTURE) {
            if (p_213386_1_.getRandom().nextFloat() < 0.2F) {
                this.setBaby(true);
            } else if (this.isAdult()) {
                this.setItemSlot(EquipmentSlotType.MAINHAND, this.createSpawnWeapon());
            }
        }

        BrigganockTasks.initMemories(this);
        this.populateDefaultEquipmentSlots(p_213386_2_);
        this.populateDefaultEquipmentEnchantments(p_213386_2_);
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.isPersistenceRequired();
    }

    protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
        if (this.isAdult()) {
            this.maybeWearArmor(EquipmentSlotType.HEAD, new ItemStack(Items.GOLDEN_HELMET));
            this.maybeWearArmor(EquipmentSlotType.CHEST, new ItemStack(Items.GOLDEN_CHESTPLATE));
            this.maybeWearArmor(EquipmentSlotType.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
            this.maybeWearArmor(EquipmentSlotType.FEET, new ItemStack(Items.GOLDEN_BOOTS));
        }

    }

    private void maybeWearArmor(EquipmentSlotType p_234419_1_, ItemStack p_234419_2_) {
        if (this.level.random.nextFloat() < 0.1F) {
            this.setItemSlot(p_234419_1_, p_234419_2_);
        }

    }

    protected Brain.BrainCodec<BrigganockEntity> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected Brain<?> makeBrain(Dynamic<?> p_213364_1_) {
        return BrigganockTasks.makeBrain(this, this.brainProvider().makeBrain(p_213364_1_));
    }

    public Brain<BrigganockEntity> getBrain() {
        return (Brain<BrigganockEntity>)super.getBrain();
    }

    public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
        ActionResultType actionresulttype = super.mobInteract(p_230254_1_, p_230254_2_);
        if (actionresulttype.consumesAction()) {
            return actionresulttype;
        } else if (!this.level.isClientSide) {
            return BrigganockTasks.mobInteract(this, p_230254_1_, p_230254_2_);
        } else {
            boolean flag = BrigganockTasks.canAdmire(this, p_230254_1_.getItemInHand(p_230254_2_)) && this.getArmPose() != BrigganockAction.ADMIRING_ITEM;
            return flag ? ActionResultType.SUCCESS : ActionResultType.PASS;
        }
    }

    protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
        return this.isBaby() ? 0.93F : 1.74F;
    }

    public double getPassengersRidingOffset() {
        return (double)this.getBbHeight() * 0.92D;
    }

    public void setBaby(boolean p_82227_1_) {
        this.getEntityData().set(DATA_BABY_ID, p_82227_1_);
        if (!this.level.isClientSide) {
            ModifiableAttributeInstance modifiableattributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            modifiableattributeinstance.removeModifier(SPEED_MODIFIER_BABY);
            if (p_82227_1_) {
                modifiableattributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
            }
        }

    }

    public boolean isBaby() {
        return this.getEntityData().get(DATA_BABY_ID);
    }

    private void setCannotHunt(boolean p_234443_1_) {
        this.cannotHunt = p_234443_1_;
    }

    protected boolean canHunt() {
        return !this.cannotHunt;
    }

    protected void customServerAiStep() {
        this.level.getProfiler().push("piglinBrain");
        this.getBrain().tick((ServerWorld)this.level, this);
        this.level.getProfiler().pop();
        BrigganockTasks.updateActivity(this);
        super.customServerAiStep();
    }

    protected int getExperienceReward(PlayerEntity p_70693_1_) {
        return this.xpReward;
    }

    protected void finishConversion(ServerWorld p_234416_1_) {
        BrigganockTasks.cancelAdmiring(this);
        this.inventory.removeAllItems().forEach(this::spawnAtLocation);
        super.finishConversion(p_234416_1_);
    }

    private ItemStack createSpawnWeapon() {
        return (double)this.random.nextFloat() < 0.5D ? new ItemStack(Items.CROSSBOW) : new ItemStack(Items.GOLDEN_SWORD);
    }

    private boolean isChargingCrossbow() {
        return this.entityData.get(DATA_IS_CHARGING_CROSSBOW);
    }

    public void setChargingCrossbow(boolean p_213671_1_) {
        this.entityData.set(DATA_IS_CHARGING_CROSSBOW, p_213671_1_);
    }

    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    public BrigganockAction getArmPose() {
        if (this.isDancing()) {
            return BrigganockAction.DANCING;
        } else if (BrigganockTasks.isLovedItem(this.getOffhandItem().getItem())) {
            return BrigganockAction.ADMIRING_ITEM;
        } else if (this.isAggressive() && this.isHoldingMeleeWeapon()) {
            return BrigganockAction.ATTACKING_WITH_MELEE_WEAPON;
        } else if (this.isChargingCrossbow()) {
            return BrigganockAction.CROSSBOW_CHARGE;
        } else {
            return this.isAggressive() && this.isHolding(item -> item instanceof net.minecraft.item.CrossbowItem) ? BrigganockAction.CROSSBOW_HOLD : BrigganockAction.DEFAULT;
        }
    }

    public boolean isDancing() {
        return this.entityData.get(DATA_IS_DANCING);
    }

    public void setDancing(boolean p_234442_1_) {
        this.entityData.set(DATA_IS_DANCING, p_234442_1_);
    }

    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        boolean flag = super.hurt(p_70097_1_, p_70097_2_);
        if (this.level.isClientSide) {
            return false;
        } else {
            if (flag && p_70097_1_.getEntity() instanceof LivingEntity) {
                BrigganockTasks.wasHurtBy(this, (LivingEntity)p_70097_1_.getEntity());
            }

            return flag;
        }
    }

    public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
        this.performCrossbowAttack(this, 1.6F);
    }

    public void shootCrossbowProjectile(LivingEntity p_230284_1_, ItemStack p_230284_2_, ProjectileEntity p_230284_3_, float p_230284_4_) {
        this.shootCrossbowProjectile(this, p_230284_1_, p_230284_3_, p_230284_4_, 1.6F);
    }

    public boolean canFireProjectileWeapon(ShootableItem p_230280_1_) {
        return p_230280_1_ == Items.CROSSBOW;
    }

    protected void holdInMainHand(ItemStack p_234438_1_) {
        this.setItemSlotAndDropWhenKilled(EquipmentSlotType.MAINHAND, p_234438_1_);
    }

    protected void holdInOffHand(ItemStack p_234439_1_) {
        if (p_234439_1_.isPiglinCurrency()) {
            this.setItemSlot(EquipmentSlotType.OFFHAND, p_234439_1_);
            this.setGuaranteedDrop(EquipmentSlotType.OFFHAND);
        } else {
            this.setItemSlotAndDropWhenKilled(EquipmentSlotType.OFFHAND, p_234439_1_);
        }

    }

    public boolean wantsToPickUp(ItemStack p_230293_1_) {
        return net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this) && this.canPickUpLoot() && BrigganockTasks.wantsToPickup(this, p_230293_1_);
    }

    protected boolean canReplaceCurrentItem(ItemStack p_234440_1_) {
        EquipmentSlotType equipmentslottype = MobEntity.getEquipmentSlotForItem(p_234440_1_);
        ItemStack itemstack = this.getItemBySlot(equipmentslottype);
        return this.canReplaceCurrentItem(p_234440_1_, itemstack);
    }

    protected boolean canReplaceCurrentItem(ItemStack p_208003_1_, ItemStack p_208003_2_) {
        if (EnchantmentHelper.hasBindingCurse(p_208003_2_)) {
            return false;
        } else {
            boolean flag = BrigganockTasks.isLovedItem(p_208003_1_.getItem()) || p_208003_1_.getItem() == Items.CROSSBOW;
            boolean flag1 = BrigganockTasks.isLovedItem(p_208003_2_.getItem()) || p_208003_2_.getItem() == Items.CROSSBOW;
            if (flag && !flag1) {
                return true;
            } else if (!flag && flag1) {
                return false;
            } else {
                return this.isAdult() && p_208003_1_.getItem() != Items.CROSSBOW && p_208003_2_.getItem() == Items.CROSSBOW ? false : super.canReplaceCurrentItem(p_208003_1_, p_208003_2_);
            }
        }
    }

    public void pickUpItem(ItemEntity item) {
        this.onItemPickup(item);
        BrigganockTasks.pickUpItem(this, item);
    }

    public boolean startRiding(Entity p_184205_1_, boolean p_184205_2_) {
        if (this.isBaby() && p_184205_1_.getType() == EntityType.HOGLIN) {
            p_184205_1_ = this.getTopPassenger(p_184205_1_, 3);
        }

        return super.startRiding(p_184205_1_, p_184205_2_);
    }

    private Entity getTopPassenger(Entity p_234417_1_, int p_234417_2_) {
        List<Entity> list = p_234417_1_.getPassengers();
        return p_234417_2_ != 1 && !list.isEmpty() ? this.getTopPassenger(list.get(0), p_234417_2_ - 1) : p_234417_1_;
    }

    protected SoundEvent getAmbientSound() {
        return this.level.isClientSide ? null : BrigganockTasks.getSoundForCurrentActivity(this).orElse((SoundEvent)null);
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.PIGLIN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PIGLIN_DEATH;
    }

    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
        this.playSound(SoundEvents.PIGLIN_STEP, 0.15F, 1.0F);
    }

    protected void playSound(SoundEvent p_241417_1_) {
        this.playSound(p_241417_1_, this.getSoundVolume(), this.getVoicePitch());
    }

    protected void playConvertedSound() {
        this.playSound(SoundEvents.PIGLIN_CONVERTED_TO_ZOMBIFIED);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.brigganock.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.brigganock.idle", true));
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

}

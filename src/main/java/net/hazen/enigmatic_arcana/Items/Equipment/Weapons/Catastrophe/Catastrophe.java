package net.hazen.enigmatic_arcana.Items.Equipment.Weapons.Catastrophe;

import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import software.bernie.geckolib.animatable.GeoItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.projectile.AbstractArrow;

public class Catastrophe extends BowItem implements GeoItem {
    public Catastrophe(Properties properties) {
        super(properties
                .stacksTo(1)
                .fireResistant()
                .rarity(EARarities.SOUL_STAINED_RARITY.getValue())
                .durability(1561)

        );
        GeoItem.registerSyncedAnimatable(this);
    }

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final int MAX_DRAW_DURATION = 60;
    public static final int DEFAULT_RANGE = 15;
    private static final String CONTROLLER = "controller";
    private static final RawAnimation CHARGING = RawAnimation.begin().thenLoop("charging_normal");
    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation RELEASE = RawAnimation.begin().thenPlay("release");


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    @Override public boolean isDamageable(ItemStack stack) { return false; }
    @Override public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) return;
        ItemStack ammo = player.getProjectile(stack);
        boolean creative = player.getAbilities().instabuild;
        boolean infinity = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, stack) > 0;
        int charge = ForgeEventFactory.onArrowLoose(stack, level, player, getUseDuration(stack) - timeLeft, !ammo.isEmpty() || creative || infinity);
        if (charge < 0 || (ammo.isEmpty() && !creative && !infinity)) return;
        if (ammo.isEmpty()) ammo = new ItemStack(Items.ARROW);
        float power = getPowerForTime(charge);
        if (power < 0.1F) return;
        ArrowItem arrowItem = ammo.getItem() instanceof ArrowItem item ? item : (ArrowItem) Items.ARROW;
        boolean infinite = creative || arrowItem.isInfinite(ammo, stack, player);
        if (level instanceof ServerLevel serverLevel) {
            int count = ammo.is(Items.ARROW) ? 3 : 1;
            float[] pitchOffsets = {0, -15, 15};
            for (int i = 0; i < count; i++) {
                AbstractArrow arrow = arrowItem.createArrow(level, ammo, player);
                arrow.shootFromRotation(player, player.getXRot() + pitchOffsets[i], player.getYRot(), 0, power * 3, 1);
                arrow.setCritArrow(power == 1);
                int damage = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.POWER_ARROWS, stack);
                if (damage > 0) arrow.setBaseDamage(arrow.getBaseDamage() + damage * 0.5 + 0.5);
                int knockback = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.PUNCH_ARROWS, stack);
                if (knockback > 0) arrow.setKnockback(knockback);
                if (net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.FLAMING_ARROWS, stack) > 0) arrow.setSecondsOnFire(100);
                arrow.pickup = infinite ? AbstractArrow.Pickup.CREATIVE_ONLY : i == 0 ? AbstractArrow.Pickup.ALLOWED : AbstractArrow.Pickup.DISALLOWED;
                level.addFreshEntity(arrow);
            }
            if (!infinite) {
                ammo.shrink(1);
                if (ammo.isEmpty()) player.getInventory().removeItem(ammo);
            }
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1, 1 / (level.random.nextFloat() * 0.4F + 1.2F) + power * 0.5F);
            player.awardStat(Stats.ITEM_USED.get(this));
            triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), CONTROLLER, "release");
        }
    }

    public static float getPowerForTime(int charge) {
        float f = (float) charge / 60.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean flag = !player.getProjectile(itemstack).isEmpty() || net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, itemstack) > 0;
        InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onArrowNock(itemstack, level, player, hand, flag);
        if (ret != null) {
            return ret;
        } else if (!player.getAbilities().instabuild && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private CatastropheRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new CatastropheRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, CONTROLLER, 0, state -> {

            var stack = state.getData(DataTickets.ITEMSTACK);

            boolean play = false;

            // Fallback: if the synced entity is using the item, play the charging animation too
            if (!play && state.getData(DataTickets.ENTITY) instanceof LivingEntity entity) {
                if (entity.isUsingItem() && entity.getUseItem() == stack) {
                    play = true;
                }
            }

            if (play) {
                state.setAnimation(CHARGING);
            } else {
                state.setAnimation(IDLE);
            }

            return PlayState.CONTINUE;
        }).triggerableAnim("release", RELEASE));
    }

}

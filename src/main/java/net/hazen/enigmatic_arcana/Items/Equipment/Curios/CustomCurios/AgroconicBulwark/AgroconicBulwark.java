package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicBulwark;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.sammy.malum.registry.common.AttributeRegistry;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Consumer;

public class AgroconicBulwark extends CurioBaseItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public AgroconicBulwark() {
        super(new Item.Properties()
                .stacksTo(1)
                .fireResistant()
                .rarity(EARarities.AGROCONIC_RARITY.getValue())
        );
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, java.util.UUID id, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attr = LinkedHashMultimap.create();
        attr.put(Attributes.ARMOR, new AttributeModifier(id, "Agroconic bulwark", 4f, AttributeModifier.Operation.ADDITION));
        attr.put(AttributeRegistry.SOUL_WARD_CAP.get(), new AttributeModifier(id, "Agroconic bulwark", 8f, AttributeModifier.Operation.ADDITION));
        attr.put(AttributeRegistry.SOUL_WARD_RECOVERY_RATE.get(), new AttributeModifier(id, "Agroconic bulwark", 0.5f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attr.put(AttributeRegistry.SOUL_WARD_INTEGRITY.get(), new AttributeModifier(id, "Agroconic bulwark", 0.3f, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attr;
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(animationController);
    }

    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("idle");

    private final AnimationController<AgroconicBulwark> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    // Make your animations in this predicate
    private PlayState predicate(AnimationState<AgroconicBulwark> event)
    {
        event.getController().setAnimation(IDLE_ANIMATION);

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private AgroconicBulwarkRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new AgroconicBulwarkRenderer();

                return this.renderer;
            }
        });
    }

}

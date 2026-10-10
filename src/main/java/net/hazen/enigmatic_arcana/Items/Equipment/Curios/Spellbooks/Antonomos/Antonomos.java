package net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos;


import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import net.hazen.enigmatic_arcana.Items.Utils.AttributeContainer;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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

public class Antonomos extends SpellBook implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public Antonomos() {
        super(10,
                new Item.Properties()
                        .stacksTo(1)
                        .fireResistant()
                        .rarity(EARarities.AGROCONIC_RARITY.getValue())
                );


    }

    @Override
    public com.google.common.collect.Multimap<net.minecraft.world.entity.ai.attributes.Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, java.util.UUID id, ItemStack stack) {
        var attributes = com.google.common.collect.ImmutableMultimap.<net.minecraft.world.entity.ai.attributes.Attribute, AttributeModifier>builder();
        attributes.put(com.sammy.malum.registry.common.AttributeRegistry.ARCANE_RESONANCE.get(), new AttributeModifier(id, "Antonomos resonance", 0.25, AttributeModifier.Operation.MULTIPLY_BASE));
        attributes.put(com.sammy.malum.registry.common.AttributeRegistry.SOUL_WARD_INTEGRITY.get(), new AttributeModifier(id, "Antonomos integrity", 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
        attributes.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(id, "Antonomos power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        attributes.put(AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier(id, "Antonomos resistance", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        attributes.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(id, "Antonomos mana", 300, AttributeModifier.Operation.ADDITION));
        return attributes.build();
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

    private final AnimationController<Antonomos> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    // Make your animations in this predicate
    private PlayState predicate(AnimationState<Antonomos> event)
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
            private AntonomosRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new AntonomosRenderer();

                return this.renderer;
            }
        });
    }

}

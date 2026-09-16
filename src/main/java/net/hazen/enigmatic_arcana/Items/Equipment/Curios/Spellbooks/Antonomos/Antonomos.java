package net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos;

import com.sammy.malum.registry.common.MalumAttributes;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
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

        this.withSpellbookAttributes(new AttributeContainer[]{
                new AttributeContainer(MalumAttributes.ARCANE_RESONANCE, 0.25, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(MalumAttributes.SOUL_WARD_INTEGRITY, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_POWER, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.SPELL_RESIST, 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 300, AttributeModifier.Operation.ADD_VALUE)
        });
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
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private AntonomosRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new AntonomosRenderer();

                return this.renderer;
            }
        });
    }

}

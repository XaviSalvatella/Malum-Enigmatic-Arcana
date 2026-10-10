package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicWings;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import top.theillusivec4.caelus.api.CaelusApi;
import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import io.redspace.ironsspellbooks.item.curios.CurioBaseItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Consumer;

public class AgroconicWings extends CurioBaseItem implements GeoItem {
    private static final Attribute FALL_FLY_ATTRIBUTE = CaelusApi.getInstance().getFallFlyingAttribute();
    private static final ResourceLocation ANGEL_WINGS = new ResourceLocation(EnigmaticArcana.MOD_ID, "angel_wings_flight");


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public AgroconicWings() {
        super(new Item.Properties()
                .stacksTo(1)
                .fireResistant()
                .rarity(EARarities.AGROCONIC_RARITY.getValue())
        );
        GeoItem.registerSyncedAnimatable(this);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private AgroconicWingsRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new AgroconicWingsRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, java.util.UUID id, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = LinkedHashMultimap.create();

        attributes.put(FALL_FLY_ATTRIBUTE, new AttributeModifier(id, "Agroconic wings flight", 1.0D, AttributeModifier.Operation.ADDITION));

        return attributes;
    }

    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "wing", 5, state -> {
            state.setAnimation(RawAnimation.begin().thenLoop("idle_1"));
            return PlayState.CONTINUE;
        }).triggerableAnim("flight", RawAnimation.begin().thenLoop("flight"))
          .triggerableAnim("idle", RawAnimation.begin().thenLoop("idle_1")));
    }
    @Override public boolean canEquipFromUse(SlotContext slot, ItemStack stack) { return true; }
    @Override public void curioTick(SlotContext slot, ItemStack stack) {
        if (slot.entity().level() instanceof net.minecraft.server.level.ServerLevel level) {
            boolean flying = slot.entity().isFallFlying();
            var tag = stack.getOrCreateTag();
            if (!tag.contains("eaFlight") || tag.getBoolean("eaFlight") != flying) {
                tag.putBoolean("eaFlight", flying);
                triggerAnim(slot.entity(), GeoItem.getOrAssignId(stack, level), "wing", flying ? "flight" : "idle");
            }
        }
    }
}

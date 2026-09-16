package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicWings;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.illusivesoulworks.caelus.api.CaelusApi;
import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.hazen.hazentouvelib.Items.Curios.Wings.WingCurioItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Consumer;

public class AgroconicWings extends WingCurioItem {
    private static final Holder<Attribute> FALL_FLY_ATTRIBUTE = CaelusApi.getInstance().getFallFlyingAttribute();
    private static final ResourceLocation ANGEL_WINGS = ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "angel_wings_flight");


    public AgroconicWings() {
        super(new Item.Properties()
                .stacksTo(1)
                .fireResistant()
                .rarity(EARarities.AGROCONIC_RARITY.getValue())
        );
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private AgroconicWingsRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new AgroconicWingsRenderer();

                return this.renderer;
            }
        });
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        Multimap<Holder<Attribute>, AttributeModifier> attributes = LinkedHashMultimap.create();

        attributes.put(FALL_FLY_ATTRIBUTE, new AttributeModifier(ANGEL_WINGS, 1.0D, AttributeModifier.Operation.ADD_VALUE));

        return attributes;
    }
}
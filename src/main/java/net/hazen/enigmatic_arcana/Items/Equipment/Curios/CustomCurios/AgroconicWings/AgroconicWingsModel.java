package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicWings;

import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class AgroconicWingsModel extends DefaultedItemGeoModel<AgroconicWings> {
    public AgroconicWingsModel() {
        super(ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(AgroconicWings animatable) {
        return ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "geo/curios/agroconic_wings.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AgroconicWings animatable) {
        return ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "textures/curios/agroconic_wings.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AgroconicWings animatable) {
        return ResourceLocation.fromNamespaceAndPath(EnigmaticArcana.MOD_ID, "animations/curios/agroconic_wings.animation.json");
    }
}
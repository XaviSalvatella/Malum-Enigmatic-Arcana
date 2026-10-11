package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicBulwark;

import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class AgroconicBulwarkModel extends DefaultedItemGeoModel<AgroconicBulwark> {
    public AgroconicBulwarkModel() {
        super(new ResourceLocation(EnigmaticArcana.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(AgroconicBulwark animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "geo/curios/agroconic_bulwark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AgroconicBulwark animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "textures/curios/agroconic_bulwark.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AgroconicBulwark animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "animations/curios/agroconic_bulwark.animation.json");
    }
}

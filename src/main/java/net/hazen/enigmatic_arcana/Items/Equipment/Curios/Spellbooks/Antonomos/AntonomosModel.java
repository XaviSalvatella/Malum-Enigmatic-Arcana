package net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos;

import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class AntonomosModel extends DefaultedItemGeoModel<Antonomos> {
    public AntonomosModel() {
        super(new ResourceLocation(EnigmaticArcana.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(Antonomos animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "geo/curios/antonomos.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Antonomos animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "textures/curios/antonomos_static.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Antonomos animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "animations/curios/antonomos.animation.json");
    }
}

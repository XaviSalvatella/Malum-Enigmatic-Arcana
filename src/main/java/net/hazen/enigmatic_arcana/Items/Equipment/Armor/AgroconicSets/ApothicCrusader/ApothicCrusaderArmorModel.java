package net.hazen.enigmatic_arcana.Items.Equipment.Armor.AgroconicSets.ApothicCrusader;

import net.hazen.enigmatic_arcana.EnigmaticArcana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ApothicCrusaderArmorModel extends DefaultedEntityGeoModel<ApothicCrusaderArmorItem> {
    public static final ResourceLocation MODEL = new ResourceLocation(EnigmaticArcana.MOD_ID, "geo/armor/apothic_crusader_armor.geo.json");
    public ApothicCrusaderArmorModel() {
        super(new ResourceLocation(EnigmaticArcana.MOD_ID, ""));
    }

    // Just replace where the path is with the file path of your texture, EZ PZ
    @Override
    public ResourceLocation getModelResource(ApothicCrusaderArmorItem animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "geo/armor/apothic_crusader_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ApothicCrusaderArmorItem animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "textures/armor/apothic_crusader_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ApothicCrusaderArmorItem animatable) {
        return new ResourceLocation(EnigmaticArcana.MOD_ID, "animations/armor/apothic_crusader_armor.animation.json");
    }
}

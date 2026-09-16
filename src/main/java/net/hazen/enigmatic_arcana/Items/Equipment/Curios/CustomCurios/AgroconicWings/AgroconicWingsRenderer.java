package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicWings;

import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class AgroconicWingsRenderer extends GeoItemRenderer<AgroconicWings> {
    public AgroconicWingsRenderer() {
        super(new AgroconicWingsModel());

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}
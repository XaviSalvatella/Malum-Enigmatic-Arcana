package net.hazen.enigmatic_arcana.Items.Equipment.Curios.CustomCurios.AgroconicBulwark;

import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class AgroconicBulwarkRenderer extends GeoItemRenderer<AgroconicBulwark> {
    public AgroconicBulwarkRenderer() {
        super(new AgroconicBulwarkModel());

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}

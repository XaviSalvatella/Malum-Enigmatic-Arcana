package net.hazen.enigmatic_arcana.Items.Equipment.Curios.Spellbooks.Antonomos;

import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class AntonomosRenderer extends GeoItemRenderer<Antonomos> {
    public AntonomosRenderer() {
        super(new AntonomosModel());

        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}

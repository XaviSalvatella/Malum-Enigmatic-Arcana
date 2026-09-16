package net.hazen.enigmatic_arcana.Items.Equipment.Armor.AgroconicSets.ApothicCrusader;

import io.redspace.ironsspellbooks.item.armor.IDisableHat;
import io.redspace.ironsspellbooks.item.armor.IDisableJacket;
import net.hazen.enigmatic_arcana.Items.Utils.EAArmorMaterials;
import net.hazen.enigmatic_arcana.Items.Utils.EAImbuableArmorItem;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Unbreakable;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ApothicCrusaderArmorItem extends EAImbuableArmorItem implements IDisableJacket, IDisableHat {

    public ApothicCrusaderArmorItem(Type type, Properties properties) {
        super(EAArmorMaterials.AGROCONIC_MATERIAL,
                type,
                properties
                        .stacksTo(1)
                        .rarity(EARarities.AGROCONIC_RARITY.getValue())
                        .fireResistant()
                        .component(DataComponents.UNBREAKABLE, new Unbreakable(false))
                ,
                pureTierMalum(250, 0.15f, 0.5f, 4, 0.2F)
        );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public GeoArmorRenderer<?> supplyRenderer() {
        return new ApothicCrusaderArmorRenderer(new ApothicCrusaderArmorModel());
    }
}
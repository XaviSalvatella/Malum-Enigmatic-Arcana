package net.hazen.enigmatic_arcana.Utils;
import com.sammy.malum.common.item.curiosities.weapons.scythe.MagicScytheItem;
import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
public class ArcaneScytheItem extends MagicScytheItem implements IPresetSpellContainer {
    public ArcaneScytheItem(Tier tier, float damage, float speed, float magicDamage, Properties properties) {
        super(tier, damage, speed, magicDamage, properties);
    }
    @Override public void initializeSpellContainer(ItemStack stack) { }
}

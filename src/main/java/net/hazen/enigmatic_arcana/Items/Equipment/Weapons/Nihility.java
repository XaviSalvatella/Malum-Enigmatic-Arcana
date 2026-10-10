package net.hazen.enigmatic_arcana.Items.Equipment.Weapons;
import com.google.common.collect.ImmutableMultimap;
import io.redspace.ironsspellbooks.api.events.ModifySpellLevelEvent;
import io.redspace.ironsspellbooks.api.item.curios.AffinityData;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import net.hazen.enigmatic_arcana.Items.Utils.EAExtendedWeaponsTiers;
import net.hazen.enigmatic_arcana.Utils.ArcaneScytheItem;
import net.hazen.enigmatic_arcana.Utils.EARarities;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.UUID;
public class Nihility extends ArcaneScytheItem {
    private static final UUID ID = UUID.fromString("d66f24e2-97a7-4d09-8bd0-7b5ebdc7f490");
    public Nihility() {
        super(EAExtendedWeaponsTiers.NIHILITY, 2F, 0.6F, 6F,
            new Properties().fireResistant().rarity(EARarities.AGROCONIC_RARITY.getValue()));
    }
    @Override public ImmutableMultimap.Builder<Attribute, AttributeModifier> createExtraAttributes() {
        var b = super.createExtraAttributes();
        b.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(ID, "Nihility power", 0.15, AttributeModifier.Operation.MULTIPLY_BASE));
        b.put(AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier(ID, "Nihility resistance", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
        b.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(ID, "Nihility reach", 2, AttributeModifier.Operation.ADDITION));
        b.put(io.redspace.ironslib.registry.IronsLibRegistries.AttributeRegistry.CRIT_DAMAGE.get(), new AttributeModifier(ID, "Nihility critical damage", 0.15, AttributeModifier.Operation.ADDITION));
        return b;
    }
    @Override public void initializeSpellContainer(ItemStack stack) {
        if (stack != null) AffinityData.setAffinityData(stack, SpellRegistry.ECHOING_STRIKES_SPELL.get());
    }
    @Mod.EventBusSubscriber(modid = "enigmatic_arcana")
    public static class SpellEvents {
        @SubscribeEvent public static void onModifySpellLevel(ModifySpellLevelEvent event) {
            var caster = event.getEntity();
            if (caster != null && event.getSpell() == SpellRegistry.ECHOING_STRIKES_SPELL.get()
                && (caster.getMainHandItem().getItem() instanceof Nihility || caster.getOffhandItem().getItem() instanceof Nihility)) event.addLevels(1);
        }
    }
}

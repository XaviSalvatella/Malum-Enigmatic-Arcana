package net.hazen.enigmatic_arcana.Utils;
import net.minecraft.world.item.Rarity;
public class EARarities {
    public record RarityValue(Rarity value) { public Rarity getValue() { return value; } }
    public static final RarityValue AGROCONIC_RARITY = new RarityValue(Rarity.create("ENIGMATIC_ARCANA_AGROCONIC", style -> style.withColor(0x841236)));
    public static final RarityValue UMBRAL_RARITY = new RarityValue(Rarity.create("ENIGMATIC_ARCANA_UMBRAL", style -> style.withColor(0xe77e0b)));
    public static final RarityValue SOUL_STAINED_RARITY = new RarityValue(Rarity.create("ENIGMATIC_ARCANA_SOUL_STAINED", style -> style.withColor(0xee8fff)));
}

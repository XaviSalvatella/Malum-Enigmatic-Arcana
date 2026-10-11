package net.hazen.enigmatic_arcana.Items.Utils;
import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
public enum EAArmorMaterials implements ArmorMaterial {
    AGROCONIC_MATERIAL;
    public int getDurabilityForType(ArmorItem.Type type) {
        return 64 * switch(type) { case HELMET -> 13; case CHESTPLATE -> 16; case LEGGINGS -> 15; case BOOTS -> 11; };
    }
    public int getDefenseForType(ArmorItem.Type type) {
        return switch(type) { case HELMET, BOOTS -> 4; case CHESTPLATE -> 9; case LEGGINGS -> 7; };
    }
    public int getEnchantmentValue() { return 20; }
    public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_NETHERITE; }
    public Ingredient getRepairIngredient() { return Ingredient.of(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get()); }
    public String getName() { return "enigmatic_arcana:agroconic"; }
    public float getToughness() { return 4; }
    public float getKnockbackResistance() { return 0.15F; }
}

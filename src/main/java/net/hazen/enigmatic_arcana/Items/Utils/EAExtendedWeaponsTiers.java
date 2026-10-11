package net.hazen.enigmatic_arcana.Items.Utils;
import com.sammy.malum.registry.common.item.ItemRegistry;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
public enum EAExtendedWeaponsTiers implements Tier {
    NIHILITY;
    public int getUses() { return 8064; }
    public float getSpeed() { return 1.3F; }
    public float getAttackDamageBonus() { return 2F; }
    public int getLevel() { return 4; }
    public int getEnchantmentValue() { return 10; }
    public Ingredient getRepairIngredient() { return Ingredient.of(ItemRegistry.SOUL_STAINED_STEEL_PLATING.get()); }
}

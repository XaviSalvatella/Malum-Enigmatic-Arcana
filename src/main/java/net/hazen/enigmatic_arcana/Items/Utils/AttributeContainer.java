package net.hazen.enigmatic_arcana.Items.Utils;
import java.util.function.Supplier;
import java.util.UUID;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
public record AttributeContainer(Supplier<Attribute> attribute, double amount, AttributeModifier.Operation operation) {
    public AttributeModifier modifier(UUID id) {
        return new AttributeModifier(id, "Enigmatic Arcana equipment", amount, operation);
    }
}

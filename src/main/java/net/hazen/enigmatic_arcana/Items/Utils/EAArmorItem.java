package net.hazen.enigmatic_arcana.Items.Utils;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;
public abstract class EAArmorItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Multimap<Attribute, AttributeModifier> modifiers;
    public EAArmorItem(ArmorMaterial material, Type type, Properties properties, AttributeContainer... extras) {
        super(material, type, properties);
        var builder = ImmutableMultimap.<Attribute, AttributeModifier>builder();
        builder.putAll(super.getDefaultAttributeModifiers(type.getSlot()));
        UUID id = UUID.nameUUIDFromBytes(("enigmatic_arcana:armor/" + type.getName()).getBytes(StandardCharsets.UTF_8));
        for (var extra : extras) builder.put(extra.attribute().get(), extra.modifier(id));
        modifiers = builder.build();
    }
    public static AttributeContainer[] pureTierMalum(int mana, float power, float resistance, float ward, float recovery) {
        return new AttributeContainer[]{
            new AttributeContainer(AttributeRegistry.MAX_MANA, mana, AttributeModifier.Operation.ADDITION),
            new AttributeContainer(AttributeRegistry.SPELL_POWER, power, AttributeModifier.Operation.MULTIPLY_BASE),
            new AttributeContainer(AttributeRegistry.SPELL_RESIST, resistance, AttributeModifier.Operation.MULTIPLY_BASE),
            new AttributeContainer(com.sammy.malum.registry.common.AttributeRegistry.SOUL_WARD_CAP, ward, AttributeModifier.Operation.ADDITION),
            new AttributeContainer(com.sammy.malum.registry.common.AttributeRegistry.SOUL_WARD_RECOVERY_RATE, recovery, AttributeModifier.Operation.MULTIPLY_BASE)
        };
    }
    @Override public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == getType().getSlot() ? modifiers : ImmutableMultimap.of();
    }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            state.setAnimation(RawAnimation.begin().thenLoop("idle"));
            return PlayState.CONTINUE;
        }));
    }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }
    @Override public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;
            @Override public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                if (renderer == null) renderer = supplyRenderer();
                renderer.prepForRender(entity, stack, slot, original);
                return renderer;
            }
        });
    }
    public abstract GeoArmorRenderer<?> supplyRenderer();
}

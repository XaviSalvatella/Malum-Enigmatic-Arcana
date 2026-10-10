package net.hazen.enigmatic_arcana.Client;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;
public class EACurioRenderer implements ICurioRenderer {
    private final boolean body;
    public EACurioRenderer(boolean body) { this.body = body; }
    @Override public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slot, PoseStack pose, RenderLayerParent<T, M> parent, MultiBufferSource buffers, int light, float limbSwing, float limbAmount, float partialTick, float age, float yaw, float pitch) {
        if (!(parent.getModel() instanceof HumanoidModel<?> model)) return;
        pose.pushPose();
        model.body.translateAndRotate(pose);
        if (body) {
            pose.mulPose(Axis.ZP.rotationDegrees(180));
        } else {
            pose.translate(-0.34, 0.56, 0);
            pose.mulPose(Axis.YP.rotationDegrees(180));
            pose.mulPose(Axis.ZP.rotationDegrees(175));
            pose.scale(0.625F, 0.625F, 0.625F);
        }
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, pose, buffers, slot.entity().level(), 0);
        pose.popPose();
    }
}

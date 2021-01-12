package net.fabricmc.example.mixin;

import com.mojang.blaze3d.platform.GlStateManager;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.example.DougMod;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(method = "render", at = @At("HEAD"))
    public <T extends LivingEntity> void chamsPre(T livingEntity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo info) {
        if (DougMod.client.utilities().get("chams").enabled()) {
            GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
            GL11.glPolygonOffset(1.0F, -1000000F);
            GlStateManager.disableLighting();
            GL11.glColor3f(0, 1, 0);
            // GL11.glDisable(GL11.GL_ALPHA);
        }

    }

    @Inject(method = "render", at = @At("RETURN"))
    public <T extends LivingEntity> void chamsPost(T livingEntity, float f, float g, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo info) {
                
        if (DougMod.client.utilities().get("chams").enabled()) {
            GL11.glPolygonOffset(1.0F, 1000000F);
            GlStateManager.enableLighting();
            GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
            // GL11.glEnable(GL11.GL_ALPHA);
        }
    }
}

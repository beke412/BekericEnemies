package bekerickibami.bekericenemies.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderSecurityG extends RenderLiving<EntityLiving> {
    public static final ResourceLocation texture = new ResourceLocation("bekericenemies:textures/player/security_g.png");
    public RenderSecurityG(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLiving entity) {
        return texture;
    }

    @Override
    protected void preRenderCallback(EntityLiving entityLivingBaseIn, float partialTickTime) {
        GlStateManager.scale(3.0F, 3.0F, 3.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}

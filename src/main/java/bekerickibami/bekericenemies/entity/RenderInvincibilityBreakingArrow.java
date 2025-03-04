package bekerickibami.bekericenemies.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInvincibilityBreakingArrow extends RenderArrow<InvincibilityBreakingArrow> {
    public static final ResourceLocation texture = new ResourceLocation("bekericenemies:textures/arrow/invincibility_breaking_arrow.png");
    public RenderInvincibilityBreakingArrow(RenderManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(InvincibilityBreakingArrow iBArrow) {
        return texture;
    }
}

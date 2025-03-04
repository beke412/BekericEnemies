package bekerickibami.bekericenemies.entity;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDeletionArrow extends RenderArrow<DeletionArrow> {
    public static final ResourceLocation texture = new ResourceLocation("bekericenemies:textures/arrow/deletion_arrow.png");
    public RenderDeletionArrow(RenderManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(DeletionArrow deletionArrow) {
        return texture;
    }
}

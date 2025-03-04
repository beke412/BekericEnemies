package bekerickibami.bekericenemies.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class InvincibilityBreakingArrow extends EntityArrow {
    public InvincibilityBreakingArrow(World worldIn) {
        super(worldIn);
        this.pickupStatus = PickupStatus.DISALLOWED;
    }

    public InvincibilityBreakingArrow(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.setPosition(x, y, z);
    }

    public InvincibilityBreakingArrow(World worldIn, EntityLivingBase shooter) {
        this(worldIn, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
        this.shootingEntity = shooter;
    }

    @Override
    protected Entity findEntityOnPath(Vec3d start, Vec3d end) {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
        double distanceA = 0.0D;
        for (Entity target : list) {
            if (target != this.shootingEntity) {
                AxisAlignedBB axisAlignedBB = target.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult rayTraceResult = axisAlignedBB.calculateIntercept(start, end);
                if (rayTraceResult != null) {
                    double distanceB = start.squareDistanceTo(rayTraceResult.hitVec);
                    if (distanceB < distanceA || distanceA == 0.0D) {
                        entity = target;
                        distanceA = distanceB;
                    }
                }
            }
        }
        return entity;
    }

    @Override
    protected void onHit(RayTraceResult rayTraceResultIn) {
        Entity entity = rayTraceResultIn.entityHit;
        if (entity != null) {
            if (entity instanceof MultiPartEntityPart) {
                Entity entityTrue = (Entity) ((MultiPartEntityPart) entity).parent;
                if (entityTrue instanceof EntityLivingBase) {
                    entity = entityTrue;
                }
            }
            if (!this.world.isRemote && !entity.equals(this.shootingEntity)) {
                if (entity instanceof EntityLivingBase){
                    ((EntityLivingBase) entity).setHealth(0.0F);
                    ((EntityLivingBase) entity).onDeath(DamageSource.GENERIC);
                } else {
                    super.onHit(rayTraceResultIn);
                }
                this.setDead();
            }
        } else {
            super.onHit(rayTraceResultIn);
            this.setDead();
        }
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }
}

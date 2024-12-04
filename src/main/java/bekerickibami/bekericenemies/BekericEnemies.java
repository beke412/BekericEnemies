package bekerickibami.bekericenemies;

import bekerickibami.bekericenemies.entity.*;
import bekerickibami.bekericenemies.item.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@Mod(modid = "bekericenemies", version = "1.0.1", name = "BekericEnemies", guiFactory = "bekerickibami.bekericenemies.gui.BekeGuiFactory")
public class BekericEnemies {
    public static Configuration config;
    public static BekeConfig CONFIG = new BekeConfig();
    public static boolean damageLimit;
    public static boolean useIntDamageLimit;
    public static boolean useIntHealthLimit;
    public static boolean useFinalDamageLimit;
    public static boolean bekericRandomDamage;
    public static boolean showDamageParticle;
    public static boolean spawnStatue;
    public static boolean spawnStatue2;
    public static boolean spawnStatue3;
    public static boolean spawnSecurityC;
    public static boolean spawnSecurityF;
    public static boolean spawnSecurityG;
    public static boolean spawnSecurityFR;
    public static boolean spawnSecurityMN;
    public static boolean randomCritical;
    public static float randomCriticalChance;
    public static float criticalDamageModifier;
    public static int intDamageLimitValue;
    public static int intHealthLimitValue;
    public static int finalDamageLimitValue;
    public static int weightStatue;
    public static int weightStatue2;
    public static int weightStatue3;
    public static int weightSecurityC;
    public static int weightSecurityF;
    public static int weightSecurityG;
    public static int weightSecurityFR;
    public static int weightSecurityMN;
    public static int minStatue;
    public static int minStatue2;
    public static int minStatue3;
    public static int minSecurityC;
    public static int minSecurityF;
    public static int minSecurityG;
    public static int minSecurityFR;
    public static int minSecurityMN;
    public static int maxStatue;
    public static int maxStatue2;
    public static int maxStatue3;
    public static int maxSecurityC;
    public static int maxSecurityF;
    public static int maxSecurityG;
    public static int maxSecurityFR;
    public static int maxSecurityMN;
    public static final Item BE_HEALTH_BOTTLE = new BEHealthBottle();
    public static final Item BE_HEALTH_RESET_BOTTLE = new BEHealthResetBottle();
    public static final Item BE_MAX_HEALTH_BOTTLE = new BEMaxHealthBottle();
    public static final Item BE_STRENGTH_BOTTLE = new BEStrengthBottle();
    public static final Item BE_STRENGTH_RESET_BOTTLE = new BEStrengthResetBottle();

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide().isClient()) {
            RenderingRegistry.registerEntityRenderingHandler(Statue.class, manager -> new RenderStatue(manager, new ModelZombie(), 0.0F));
            RenderingRegistry.registerEntityRenderingHandler(Statue2.class, manager -> new RenderStatue2(manager, new ModelZombie(), 0.0F));
            RenderingRegistry.registerEntityRenderingHandler(Statue3.class, manager -> new RenderStatue3(manager, new ModelZombie(), 0.0F));
            RenderingRegistry.registerEntityRenderingHandler(SecurityC.class, manager -> new RenderSecurityC(manager, new ModelSlime(0), 0.0F));
            RenderingRegistry.registerEntityRenderingHandler(SecurityF.class, manager -> new RenderSecurityF(manager, new ModelGhast(), 0.0F));
            RenderingRegistry.registerEntityRenderingHandler(SecurityG.class, manager -> new RenderSecurityG(manager, new ModelPlayer(0.0F, false), 0.0F));
            RenderingRegistry.registerEntityRenderingHandler(SecurityFR.class, manager -> new RenderSecurityFR(manager, new ModelGhast(), 0.0F));
            RenderingRegistry.registerEntityRenderingHandler(SecurityMN.class, manager -> new RenderSecurityMN(manager, new ModelSilverfish(), 0.0F));
        }

        Configuration cfg2 = new Configuration(event.getSuggestedConfigurationFile());
        cfg2.load();
        cfg2.save();
        loadConfig();
        syncConfig();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "statue"), Statue.class, "Statue", 0, this, 64, 1, true, 16777215, 11184810);
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "statue2"), Statue2.class, "Statue2", 1, this, 64, 1, true, 16777215, 11184810);
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "statue3"), Statue3.class, "Statue3", 2, this, 64, 1, true, 16777215, 11184810);
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "security_c"), SecurityC.class, "SecurityC", 3, this, 64, 1, true, 16777215, 11184810);
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "security_f"), SecurityF.class, "SecurityF", 4, this, 64, 1, true, 16777215, 11184810);
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "security_g"), SecurityG.class, "SecurityG", 5, this, 64, 1, true, 16777215, 11184810);
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "security_fr"), SecurityFR.class, "SecurityFR", 6, this, 64, 1, true, 16777215, 11184810);
        EntityRegistry.registerModEntity(new ResourceLocation("bekericenemies", "security_mn"), SecurityMN.class, "SecurityMN", 7, this, 64, 1, true, 16777215, 11184810);
        CONFIG.setting();
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(BE_HEALTH_BOTTLE);
        event.getRegistry().register(BE_HEALTH_RESET_BOTTLE);
        event.getRegistry().register(BE_MAX_HEALTH_BOTTLE);
        event.getRegistry().register(BE_STRENGTH_BOTTLE);
        event.getRegistry().register(BE_STRENGTH_RESET_BOTTLE);
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        float rand = bekericRandomDamage ? (float)event.getEntityLiving().world.rand.nextInt(16) / 100 + 1 : 1;
        float finalDamage = event.getAmount() * rand;
        if (finalDamage > Float.MAX_VALUE && damageLimit) {
            finalDamage = Float.MAX_VALUE;
        }
        if (useFinalDamageLimit && finalDamage > finalDamageLimitValue) {
            event.setAmount(finalDamageLimitValue);
        } else {
            event.setAmount(finalDamage);
        }
    }

    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event) {
        Entity target = event.getTarget();
        EntityPlayer player = event.getEntityPlayer();
        if (target.canBeAttackedWithItem()) {
            if (!target.hitByEntity(player)) {
                float damage = (float)player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
                float enchDamage;
                if (target instanceof EntityLivingBase){
                    enchDamage = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), ((EntityLivingBase) target).getCreatureAttribute());
                } else {
                    enchDamage = EnchantmentHelper.getModifierForCreature(player.getHeldItemMainhand(), EnumCreatureAttribute.UNDEFINED);
                }
                float charge = player.getCooledAttackStrength(0.5F);
                damage = damage * (0.2F + charge * charge * 0.8F);
                enchDamage = enchDamage * charge;
                player.resetCooldown();
                if (damage > 0.0F || enchDamage > 0.0F) {
                    boolean fullCharge = charge > 0.9F;
                    boolean dashAttack = false;
                    int kb = 0;
                    kb = kb + EnchantmentHelper.getKnockbackModifier(player);
                    if (player.isSprinting() && fullCharge) {
                        player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, player.getSoundCategory(), 1.0F, 1.0F);
                        ++kb;
                        dashAttack = true;
                    }
                    boolean critical;
                    if (randomCritical) {
                        critical = player.world.rand.nextFloat() < randomCriticalChance && !player.isSprinting() && fullCharge && !player.isPotionActive(MobEffects.BLINDNESS) && target instanceof EntityLivingBase;
                    } else {
                        critical = !player.isSprinting() && fullCharge && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(MobEffects.BLINDNESS) && !player.isRiding() && target instanceof EntityLivingBase;
                    }
                    CriticalHitEvent criticalHitEvent = ForgeHooks.getCriticalHit(player, target, critical, critical ? criticalDamageModifier : 1.0F);
                    critical = criticalHitEvent != null;
                    if (critical) {
                        damage *= criticalHitEvent.getDamageModifier();
                    }
                    damage = damage + enchDamage;
                    boolean sweep = false;
                    double move = (player.distanceWalkedModified - player.prevDistanceWalkedModified);
                    if (fullCharge && !critical && !dashAttack && player.onGround && move < (double)player.getAIMoveSpeed()) {
                        ItemStack itemStack = player.getHeldItem(EnumHand.MAIN_HAND);
                        if (itemStack.getItem() instanceof ItemSword) {
                            sweep = true;
                        }
                    }
                    float keepHealth = 0.0F;
                    boolean fireAspect = false;
                    int modifier = EnchantmentHelper.getFireAspectModifier(player);
                    if (target instanceof EntityLivingBase) {
                        keepHealth = ((EntityLivingBase)target).getHealth();
                        if (modifier > 0 && !target.isBurning()) {
                            fireAspect = true;
                            target.setFire(1);
                        }
                    }
                    double targetMotionX = target.motionX;
                    double targetMotionY = target.motionY;
                    double targetMotionZ = target.motionZ;
                    if (damage > Float.MAX_VALUE && damageLimit) {
                        damage = Float.MAX_VALUE;
                    }
                    boolean success = target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
                    if (success) {
                        if (kb > 0) {
                            if (target instanceof EntityLivingBase) {
                                ((EntityLivingBase)target).knockBack(player, (float)kb * 0.5F, MathHelper.sin(player.rotationYaw * 0.017453292F), (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                            } else {
                                target.addVelocity((-MathHelper.sin(player.rotationYaw * 0.017453292F) * (float)kb * 0.5F), 0.1D, (MathHelper.cos(player.rotationYaw * 0.017453292F) * (float)kb * 0.5F));
                            }
                            player.motionX *= 0.6D;
                            player.motionZ *= 0.6D;
                            player.setSprinting(false);
                        }
                        if (sweep) {
                            float sweepDamage = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * damage;
                            for (EntityLivingBase entitylivingbase : player.world.getEntitiesWithinAABB(EntityLivingBase.class, target.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D))) {
                                if (entitylivingbase != player && entitylivingbase != target && !player.isOnSameTeam(entitylivingbase) && player.getDistanceSq(entitylivingbase) < 9.0D) {
                                    entitylivingbase.knockBack(player, 0.4F, MathHelper.sin(player.rotationYaw * 0.017453292F), (-MathHelper.cos(player.rotationYaw * 0.017453292F)));
                                    entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage(player), sweepDamage);
                                }
                            }
                            player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
                            player.spawnSweepParticles();
                        }
                        if (target instanceof EntityPlayerMP && target.velocityChanged) {
                            ((EntityPlayerMP)target).connection.sendPacket(new SPacketEntityVelocity(target));
                            target.velocityChanged = false;
                            target.motionX = targetMotionX;
                            target.motionY = targetMotionY;
                            target.motionZ = targetMotionZ;
                        }
                        if (critical) {
                            player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, player.getSoundCategory(), 1.0F, 1.0F);
                            player.onCriticalHit(target);
                        }
                        if (!critical && !sweep) {
                            if (fullCharge) {
                                player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                            else
                            {
                                player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, player.getSoundCategory(), 1.0F, 1.0F);
                            }
                        }
                        if (enchDamage > 0.0F) {
                            player.onEnchantmentCritical(target);
                        }
                        player.setLastAttackedEntity(target);
                        if (target instanceof EntityLivingBase) {
                            EnchantmentHelper.applyThornEnchantments((EntityLivingBase)target, player);
                        }
                        EnchantmentHelper.applyArthropodEnchantments(player, target);
                        ItemStack itemStack1 = player.getHeldItemMainhand();
                        Entity entity = target;
                        if (target instanceof MultiPartEntityPart) {
                            IEntityMultiPart ientitymultipart = ((MultiPartEntityPart)target).parent;
                            if (ientitymultipart instanceof EntityLivingBase) {
                                entity = (EntityLivingBase)ientitymultipart;
                            }
                        }
                        if (!itemStack1.isEmpty() && entity instanceof EntityLivingBase) {
                            ItemStack beforeHitCopy = itemStack1.copy();
                            itemStack1.hitEntity((EntityLivingBase)entity, player);
                            if (itemStack1.isEmpty()) {
                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, beforeHitCopy, EnumHand.MAIN_HAND);
                                player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
                            }
                        }
                        if (target instanceof EntityLivingBase) {
                            float damageDealt = keepHealth - ((EntityLivingBase)target).getHealth();
                            player.addStat(StatList.DAMAGE_DEALT, Math.round(damageDealt * 10.0F));
                            if (modifier > 0) {
                                target.setFire(modifier * 4);
                            }
                            if (player.world instanceof WorldServer && damageDealt > 2.0F && showDamageParticle) {
                                int k = (int)((double)damageDealt * 0.5D);
                                ((WorldServer)player.world).spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, target.posX, target.posY + (double)(target.height * 0.5F), target.posZ, k, 0.1D, 0.0D, 0.1D, 0.2D);
                            }
                        }
                        player.addExhaustion(0.1F);
                    } else {
                        player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, player.getSoundCategory(), 1.0F, 1.0F);
                        if (fireAspect) {
                            target.extinguish();
                        }
                    }
                }
            }
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase("bekericenemies")) {
            syncConfig();
        }
    }

    public static void loadConfig() {
        File cfgFile = new File(Loader.instance().getConfigDir(), "bekericenemies.cfg");
        config = new Configuration(cfgFile);
        if (cfgFile.exists()) {
            config.load();
        } else {
            try {
                config.load();
                CONFIG.init(config);
            } finally {
                config.save();
            }
        }
    }

    public static void syncConfig() {
        CONFIG.init(config);
        CONFIG.setting();
        config.save();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(BE_HEALTH_BOTTLE, 0, new ModelResourceLocation(new ResourceLocation("bekericenemies","be_health_bottle"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BE_HEALTH_RESET_BOTTLE, 0, new ModelResourceLocation(new ResourceLocation("bekericenemies","be_health_reset_bottle"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BE_MAX_HEALTH_BOTTLE, 0, new ModelResourceLocation(new ResourceLocation("bekericenemies","be_max_health_bottle"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BE_STRENGTH_BOTTLE, 0, new ModelResourceLocation(new ResourceLocation("bekericenemies","be_strength_bottle"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(BE_STRENGTH_RESET_BOTTLE, 0, new ModelResourceLocation(new ResourceLocation("bekericenemies","be_strength_reset_bottle"), "inventory"));
    }
}

package bekerickibami.bekericenemies;

import bekerickibami.bekericenemies.entity.*;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.lang.reflect.Field;

public class BekeConfig {
    static final String split = Configuration.CATEGORY_SPLITTER;
    public static final String CATEGORY_ALL = "main";
    public static final String CATEGORY_CAN_SPAWN = CATEGORY_ALL + split + "can_spawn";
    public static final String CATEGORY_WEIGHTED_PROB = CATEGORY_ALL + split + "weighted_prob";
    public static final String CATEGORY_MIN_SPAWN = CATEGORY_ALL + split + "min_spawn";
    public static final String CATEGORY_MAX_SPAWN = CATEGORY_ALL + split + "max_spawn";
    public static final String CATEGORY_ATTRIBUTES = CATEGORY_ALL + split + "attributes";
    public static final String CATEGORY_PARTICLE = CATEGORY_ALL + split + "particle";
    public static final String CATEGORY_CRITICAL = CATEGORY_ALL + split + "critical";

    public void init(Configuration config) {
        BekericEnemies.spawnStatue = config.getBoolean("spawnStatue", CATEGORY_CAN_SPAWN, false, "true: Statue can spawn");
        BekericEnemies.spawnStatue2 = config.getBoolean("spawnStatue2", CATEGORY_CAN_SPAWN, false, "true: Statue2 can spawn");
        BekericEnemies.spawnStatue3 = config.getBoolean("spawnStatue3", CATEGORY_CAN_SPAWN, false, "true: Statue3 can spawn");
        BekericEnemies.spawnSecurityC = config.getBoolean("spawnSecurityC", CATEGORY_CAN_SPAWN, false, "true: Security-Cube can spawn");
        BekericEnemies.spawnSecurityF = config.getBoolean("spawnSecurityF", CATEGORY_CAN_SPAWN, false, "true: Security-Fire can spawn");
        BekericEnemies.spawnSecurityG = config.getBoolean("spawnSecurityG", CATEGORY_CAN_SPAWN, false, "true: Security-Gravity can spawn");
        BekericEnemies.spawnSecurityFR = config.getBoolean("spawnSecurityFR", CATEGORY_CAN_SPAWN, false, "true: Security-Fire-R can spawn");
        BekericEnemies.spawnSecurityMN = config.getBoolean("spawnSecurityMN", CATEGORY_CAN_SPAWN, false, "true: Security-Mechanical-Nakumeji(silverfish) can spawn");
        BekericEnemies.weightStatue = config.getInt("weightStatue", CATEGORY_WEIGHTED_PROB, 1000, 0, 2147483647, "The frequency of spawning Statue");
        BekericEnemies.weightStatue2 = config.getInt("weightStatue2", CATEGORY_WEIGHTED_PROB, 600, 0, 2147483647, "The frequency of spawning Statue2");
        BekericEnemies.weightStatue3 = config.getInt("weightStatue3", CATEGORY_WEIGHTED_PROB, 200, 0, 2147483647, "The frequency of spawning Statue3");
        BekericEnemies.weightSecurityC = config.getInt("weightSecurityC", CATEGORY_WEIGHTED_PROB, 1000, 0, 2147483647, "The frequency of spawning Security-Cube");
        BekericEnemies.weightSecurityF = config.getInt("weightSecurityF", CATEGORY_WEIGHTED_PROB, 1000, 0, 2147483647, "The frequency of spawning Security-Fire");
        BekericEnemies.weightSecurityG = config.getInt("weightSecurityG", CATEGORY_WEIGHTED_PROB, 100, 0, 2147483647, "The frequency of spawning Security-Gravity");
        BekericEnemies.weightSecurityFR = config.getInt("weightSecurityFR", CATEGORY_WEIGHTED_PROB, 200, 0, 2147483647, "The frequency of spawning Security-Fire-R");
        BekericEnemies.weightSecurityMN = config.getInt("weightSecurityMN", CATEGORY_WEIGHTED_PROB, 1000, 0, 2147483647, "The frequency of spawning Security-Mechanical-Nakumeji");
        BekericEnemies.minStatue = config.getInt("minStatue", CATEGORY_MIN_SPAWN, 5, 0, 2147483647, "How many Statues appear at once? (min)");
        BekericEnemies.minStatue2 = config.getInt("minStatue2", CATEGORY_MIN_SPAWN, 3, 0, 2147483647, "How many Statue2s appear at once? (min)");
        BekericEnemies.minStatue3 = config.getInt("minStatue3", CATEGORY_MIN_SPAWN, 1, 0, 2147483647, "How many Statue3s appear at once? (min)");
        BekericEnemies.minSecurityC = config.getInt("minSecurityC", CATEGORY_MIN_SPAWN, 5, 0, 2147483647, "How many SecurityCs appear at once? (min)");
        BekericEnemies.minSecurityF = config.getInt("minSecurityF", CATEGORY_MIN_SPAWN, 5, 0, 2147483647, "How many SecurityFs appear at once? (min)");
        BekericEnemies.minSecurityG = config.getInt("minSecurityG", CATEGORY_MIN_SPAWN, 1, 0, 2147483647, "How many SecurityGs appear at once? (min)");
        BekericEnemies.minSecurityFR = config.getInt("minSecurityFR", CATEGORY_MIN_SPAWN, 1, 0, 2147483647, "How many SecurityFRs appear at once? (min)");
        BekericEnemies.minSecurityMN = config.getInt("minSecurityMN", CATEGORY_MIN_SPAWN, 5, 0, 2147483647, "How many SecurityMNs appear at once? (min)");
        BekericEnemies.maxStatue = config.getInt("maxStatue", CATEGORY_MAX_SPAWN, 15, 0, 2147483647, "How many Statues appear at once? (max)");
        BekericEnemies.maxStatue2 = config.getInt("maxStatue2", CATEGORY_MAX_SPAWN, 8, 0, 2147483647, "How many Statue2s appear at once? (max)");
        BekericEnemies.maxStatue3 = config.getInt("maxStatue3", CATEGORY_MAX_SPAWN, 5, 0, 2147483647, "How many Statue3s appear at once? (max)");
        BekericEnemies.maxSecurityC = config.getInt("maxSecurityC", CATEGORY_MAX_SPAWN, 15, 0, 2147483647, "How many SecurityCs appear at once? (max)");
        BekericEnemies.maxSecurityF = config.getInt("maxSecurityF", CATEGORY_MAX_SPAWN, 15, 0, 2147483647, "How many SecurityFs appear at once? (max)");
        BekericEnemies.maxSecurityG = config.getInt("maxSecurityG", CATEGORY_MAX_SPAWN, 1, 0, 2147483647, "How many SecurityGs appear at once? (max)");
        BekericEnemies.maxSecurityFR = config.getInt("maxSecurityFR", CATEGORY_MAX_SPAWN, 5, 0, 2147483647, "How many SecurityFRs appear at once? (max)");
        BekericEnemies.maxSecurityMN = config.getInt("maxSecurityMN", CATEGORY_MAX_SPAWN, 15, 0, 2147483647, "How many SecurityMNs appear at once? (max)");
        BekericEnemies.damageLimit = config.getBoolean("damageLimit", CATEGORY_ATTRIBUTES, true, "true: Max damage is Float.MAX_VALUE, false: Max damage is Double.POSITIVE_INFINITY");
        BekericEnemies.useIntDamageLimit = config.getBoolean("useIntDamageLimit", CATEGORY_ATTRIBUTES, false, "true: Max damage is variable, false: Max damage is MAX_VALUE or POSITIVE_INFINITY");
        BekericEnemies.intDamageLimitValue = config.getInt("intDamageLimitValue", CATEGORY_ATTRIBUTES, 2147483647, 0, 2147483647, "Max damage when useIntDamageLimit is true");
        BekericEnemies.useIntHealthLimit = config.getBoolean("useIntHealthLimit", CATEGORY_ATTRIBUTES, false, "true: Max health is variable, false: Max health is POSITIVE_INFINITY");
        BekericEnemies.intHealthLimitValue = config.getInt("intHealthLimitValue", CATEGORY_ATTRIBUTES, 2147483647, 0, 2147483647, "Max health when useIntHealthLimit is true");
        BekericEnemies.useFinalDamageLimit = config.getBoolean("useFinalDamageLimit", CATEGORY_ATTRIBUTES, false, "true: Max final damage is variable, false: Max final damage is MAX_VALUE or POSITIVE_INFINITY");
        BekericEnemies.finalDamageLimitValue = config.getInt("finalDamageLimitValue", CATEGORY_ATTRIBUTES, 2147483647, 0, 2147483647, "Max final damage when useFinalDamageLimit is true");
        BekericEnemies.bekericRandomDamage = config.getBoolean("bekericRandomDamage", CATEGORY_ATTRIBUTES, false, "true: Final damage is random(100%~115%)");
        BekericEnemies.showDamageParticle = config.getBoolean("showDamageParticle", CATEGORY_PARTICLE, false, "true: Damage particles(DAMAGE_INDICATOR) are displayed");
        BekericEnemies.randomCritical = config.getBoolean("randomCritical", CATEGORY_CRITICAL, false, "true: Melee attacks have chance to be critical, false: Melee attacks will be critical while falling");
        BekericEnemies.randomCriticalChance = config.getFloat("randomCriticalChance", CATEGORY_CRITICAL, 0.5F, 0, 1.0F, "The chance of a critical hit when randomCritical is true (default: 50%)");
        BekericEnemies.criticalDamageModifier = config.getFloat("criticalDamageModifier", CATEGORY_CRITICAL, 1.5F, 0, Float.MAX_VALUE, "Damage multiplier when a critical hit occurs");
    }

    public void setting() {
        Field field = ObfuscationReflectionHelper.findField(RangedAttribute.class, "field_111118_b");
        try {
            if (BekericEnemies.useIntHealthLimit) {
                field.setInt(SharedMonsterAttributes.MAX_HEALTH, BekericEnemies.intHealthLimitValue);
            } else {
                field.setDouble(SharedMonsterAttributes.MAX_HEALTH, Double.POSITIVE_INFINITY);
            }
            if (BekericEnemies.useIntDamageLimit) {
                field.setInt(SharedMonsterAttributes.ATTACK_DAMAGE, BekericEnemies.intDamageLimitValue);
            } else {
                field.setDouble(SharedMonsterAttributes.ATTACK_DAMAGE, BekericEnemies.damageLimit ? Float.MAX_VALUE : Double.POSITIVE_INFINITY);
            }
            field.setDouble(SharedMonsterAttributes.ATTACK_SPEED, Double.POSITIVE_INFINITY);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= 39; i++) {
            if (BekericEnemies.spawnStatue) {
                EntityRegistry.addSpawn(Statue.class, BekericEnemies.weightStatue, BekericEnemies.minStatue, BekericEnemies.maxStatue, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(Statue.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
            if (BekericEnemies.spawnStatue2) {
                EntityRegistry.addSpawn(Statue2.class, BekericEnemies.weightStatue2, BekericEnemies.minStatue2, BekericEnemies.maxStatue2, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(Statue2.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
            if (BekericEnemies.spawnStatue3) {
                EntityRegistry.addSpawn(Statue3.class, BekericEnemies.weightStatue3, BekericEnemies.minStatue3, BekericEnemies.maxStatue3, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(Statue3.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
            if (BekericEnemies.spawnSecurityC) {
                EntityRegistry.addSpawn(SecurityC.class, BekericEnemies.weightSecurityC, BekericEnemies.minSecurityC, BekericEnemies.maxSecurityC, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(SecurityC.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
            if (BekericEnemies.spawnSecurityF) {
                EntityRegistry.addSpawn(SecurityF.class, BekericEnemies.weightSecurityF, BekericEnemies.minSecurityF, BekericEnemies.maxSecurityF, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(SecurityF.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
            if (BekericEnemies.spawnSecurityG) {
                EntityRegistry.addSpawn(SecurityG.class, BekericEnemies.weightSecurityG, BekericEnemies.minSecurityG, BekericEnemies.maxSecurityG, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(SecurityG.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
            if (BekericEnemies.spawnSecurityFR) {
                EntityRegistry.addSpawn(SecurityFR.class, BekericEnemies.weightSecurityFR, BekericEnemies.minSecurityFR, BekericEnemies.maxSecurityFR, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(SecurityFR.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
            if (BekericEnemies.spawnSecurityMN) {
                EntityRegistry.addSpawn(SecurityMN.class, BekericEnemies.weightSecurityMN, BekericEnemies.minSecurityMN, BekericEnemies.maxSecurityMN, EnumCreatureType.MONSTER, Biome.getBiome(i));
            } else {
                EntityRegistry.removeSpawn(SecurityMN.class, EnumCreatureType.MONSTER, Biome.getBiome(i));
            }
        }
    }
}

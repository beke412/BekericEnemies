package bekerickibami.bekericenemies.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class InvincibilityBreaker extends ItemSword {
    public InvincibilityBreaker() {
        super(ToolMaterial.DIAMOND);
        this.setRegistryName("bekericenemies", "invincibility_breaker");
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setUnlocalizedName("invincibility_breaker");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!attacker.world.isRemote) {
            target.setHealth(0.0F);
            target.onDeath(DamageSource.GENERIC);
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        TextComponentTranslation info = new TextComponentTranslation("bekericenemies.invincibility_breaker1");
        tooltip.add(TextFormatting.AQUA + info.getFormattedText());
        TextComponentTranslation info2 = new TextComponentTranslation("bekericenemies.invincibility_breaker2");
        tooltip.add(TextFormatting.YELLOW + info2.getFormattedText());
    }
}

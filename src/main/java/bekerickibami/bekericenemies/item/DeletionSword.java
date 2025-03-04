package bekerickibami.bekericenemies.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class DeletionSword extends ItemSword {
    public DeletionSword() {
        super(ToolMaterial.DIAMOND);
        this.setRegistryName("bekericenemies", "deletion_sword");
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setUnlocalizedName("deletion_sword");
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (!attacker.world.isRemote) {
            if (target instanceof EntityDragon) {
                target.onKillCommand();
                ((EntityDragon) target).deathTicks = 199;
            } else {
                target.setDead();
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        TextComponentTranslation info = new TextComponentTranslation("bekericenemies.deletion_sword1");
        tooltip.add(TextFormatting.AQUA + info.getFormattedText());
        TextComponentTranslation info2 = new TextComponentTranslation("bekericenemies.deletion_sword2");
        tooltip.add(TextFormatting.RED + info2.getFormattedText());
    }
}

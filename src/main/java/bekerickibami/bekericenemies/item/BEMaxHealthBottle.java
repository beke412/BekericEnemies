package bekerickibami.bekericenemies.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BEMaxHealthBottle extends ItemFood {
    public BEMaxHealthBottle() {
        super(1, 5.0F, false);
        this.setRegistryName("bekericenemies", "be_max_health_bottle");
        this.setCreativeTab(CreativeTabs.FOOD);
        this.setUnlocalizedName("be_max_health_bottle");
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Double.POSITIVE_INFINITY);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 16;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        TextComponentTranslation info = new TextComponentTranslation("bekericenemies.max_health_bottle1");
        tooltip.add(TextFormatting.AQUA + info.getFormattedText());
        TextComponentTranslation info2 = new TextComponentTranslation("bekericenemies.max_health_bottle2");
        tooltip.add(TextFormatting.RED + info2.getFormattedText());
    }
}

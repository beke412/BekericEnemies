package bekerickibami.bekericenemies.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
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

public class BEHealthBottle extends ItemFood {
    public BEHealthBottle() {
        super(1, 5.0F, false);
        this.setRegistryName("bekericenemies", "be_health_bottle");
        this.setCreativeTab(CreativeTabs.FOOD);
        this.setUnlocalizedName("be_health_bottle");
        this.setAlwaysEdible();
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        player.heal(player.getMaxHealth());
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
        TextComponentTranslation info = new TextComponentTranslation("bekericenemies.health_bottle1");
        tooltip.add(TextFormatting.AQUA + info.getFormattedText());
    }
}

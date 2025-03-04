package bekerickibami.bekericenemies.item;

import bekerickibami.bekericenemies.entity.DeletionArrow;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class DeletionBow extends ItemBow {
    public DeletionBow() {
        super();
        this.setRegistryName("bekericenemies", "deletion_bow");
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setUnlocalizedName("deletion_bow");
        this.setMaxDamage(Integer.MAX_VALUE);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, true);
            if (i < 0) return;
            float f = getArrowVelocity(i);
            if ((double)f >= 0.1D && !worldIn.isRemote) {
                DeletionArrow deletionArrow = new DeletionArrow(worldIn, entityplayer);
                deletionArrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 6.0F, 0.0F);
                if (f == 1.0F) {
                    deletionArrow.setIsCritical(true);
                }
                worldIn.spawnEntity(deletionArrow);
                worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 0.75F + f * 0.5F);
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        TextComponentTranslation info = new TextComponentTranslation("bekericenemies.deletion_bow1");
        tooltip.add(TextFormatting.AQUA + info.getFormattedText());
        TextComponentTranslation info2 = new TextComponentTranslation("bekericenemies.deletion_bow2");
        tooltip.add(TextFormatting.RED + info2.getFormattedText());
    }
}

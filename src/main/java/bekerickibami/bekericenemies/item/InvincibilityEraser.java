package bekerickibami.bekericenemies.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class InvincibilityEraser extends Item {
    public InvincibilityEraser() {
        super();
        this.setRegistryName("bekericenemies", "invincibility_eraser");
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setUnlocalizedName("invincibility_eraser");
        this.maxStackSize = 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 5.0F, 0);
        List<Entity> list = world.getLoadedEntityList();
        for (Entity target : list) {
            if (!player.world.isRemote && target instanceof EntityLivingBase && !target.equals(player)) {
                ((EntityLivingBase) target).setHealth(0.0F);
                ((EntityLivingBase) target).onDeath(DamageSource.GENERIC);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        TextComponentTranslation info = new TextComponentTranslation("bekericenemies.invincibility_eraser1");
        tooltip.add(TextFormatting.AQUA + info.getFormattedText());
    }
}

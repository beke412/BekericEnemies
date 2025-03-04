package bekerickibami.bekericenemies.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
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

public class DeletionHoe extends Item {
    public DeletionHoe() {
        super();
        this.setRegistryName("bekericenemies", "deletion_hoe");
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setUnlocalizedName("deletion_hoe");
        this.maxStackSize = 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 5.0F, 0);
        List<Entity> list = world.getLoadedEntityList();
        for (Entity target : list) {
            if (!player.world.isRemote && !target.equals(player)) {
                if (target instanceof EntityDragon) {
                    target.onKillCommand();
                    ((EntityDragon) target).deathTicks = 199;
                } else {
                    target.setDead();
                }
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        TextComponentTranslation info = new TextComponentTranslation("bekericenemies.deletion_hoe1");
        tooltip.add(TextFormatting.AQUA + info.getFormattedText());
        TextComponentTranslation info2 = new TextComponentTranslation("bekericenemies.deletion_hoe2");
        tooltip.add(TextFormatting.RED + info2.getFormattedText());
    }
}

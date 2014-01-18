package torchrifle;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGun extends Item {

	EnumItem type;
	public ItemGun(int par1, EnumItem type) {
		super(par1);
		setMaxDamage(EnumToolMaterial.EMERALD.getMaxUses());
		this.type = type;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		if(type == EnumItem.TORCH_RIFLE) {
			this.itemIcon = par1IconRegister.registerIcon("torchrifle:itemgun1");
		}
		else {
			this.itemIcon = par1IconRegister.registerIcon("torchrifle:itemgun2");
		}
	}

	public static List<Integer> getBullet(EnumItem type) {
		List<Integer> list = new LinkedList();
		switch(type) {
		case TORCH_RIFLE:
			list.add(Block.torchWood.blockID);
			break;
		case PICKAXE_RIFLE:
			list.add(Item.pickaxeIron.itemID);
			list.add(Item.pickaxeGold.itemID);
			list.add(Item.pickaxeWood.itemID);
			list.add(Item.pickaxeStone.itemID);
			list.add(Item.pickaxeDiamond.itemID);
			break;
		default:
			break;
		}
		return list;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world,
			EntityPlayer entityPlayer) {
		List<Integer> bulletList = getBullet(type);
		ItemStack itemBullet = null;

		for(int i = 0; i < entityPlayer.inventory.mainInventory.length; ++i) {
			if(entityPlayer.inventory.mainInventory[i] == null) {
				continue;
			}
			if(bulletList.contains(entityPlayer.inventory.mainInventory[i].itemID) == false){
				continue;
			}
			if(entityPlayer.inventory.mainInventory[i].isItemEnchanted()) {
				continue;
			}
			itemBullet = entityPlayer.inventory.mainInventory[i].copy();
			if(!entityPlayer.capabilities.isCreativeMode) {
				if(entityPlayer.inventory.mainInventory[i].isItemStackDamageable()) {
					entityPlayer.inventory.mainInventory[i].damageItem(2, entityPlayer);
					if(entityPlayer.inventory.mainInventory[i].getItemDamage() == 0) {
						entityPlayer.inventory.mainInventory[i] = null;
						break;
					}
				}
				else {
					itemBullet.stackSize = 1;
					if(--entityPlayer.inventory.mainInventory[i].stackSize <= 0) {
						entityPlayer.inventory.mainInventory[i] = null;
						break;
					}
				}
			}
			break;
		}

		if(itemBullet == null) {
			return itemStack;
		}

		if(!world.isRemote){
			Vec3 vec3d = entityPlayer.getLook(1.0F);
			EntityBullet bullet = new EntityBullet(world, entityPlayer, vec3d.xCoord * 4D, vec3d.yCoord * 4D, vec3d.zCoord * 4D, 0.2F, itemBullet);
			bullet.posX = entityPlayer.posX;
			bullet.posY = entityPlayer.posY + 0.75D;
			bullet.posZ = entityPlayer.posZ;
			world.spawnEntityInWorld(bullet);
			itemStack.damageItem(1, entityPlayer);
		}

		// TODO 自動生成されたメソッド・スタブ
		return super.onItemRightClick(itemStack, world, entityPlayer);
	}
}

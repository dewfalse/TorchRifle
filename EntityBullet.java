package torchrifle;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {

	public ItemStack item;

	public EntityBullet(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EntityBullet(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EntityBullet(World par1World) {
		super(par1World);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public EntityBullet(World world, EntityLiving entity, double dx, double dy, double dz, float accuracy, ItemStack itemBullet) {
		super(world);
		setSize(0.2F, 0.2F);
		setLocationAndAngles(entity.posX, entity.posY + 10, entity.posZ, entity.rotationYaw, entity.rotationPitch);
		// setPosition(posX, posY, posZ);
		this.item = itemBullet;
		dataWatcher.updateObject(20, itemBullet.itemID);
		dataWatcher.updateObject(21, itemBullet.getItemDamage());
		yOffset = 0.0F;
		motionX = motionY = motionZ = 0.0D;
		// double accuracy=0.2D;
		dx += rand.nextGaussian() * accuracy;
		dy += rand.nextGaussian() * accuracy;
		dz += rand.nextGaussian() * accuracy;
		double d3 = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
		motionX = (dx / d3) * 1.5D;
		motionY = (dy / d3) * 1.5D;
		motionZ = (dz / d3) * 1.5D;
	}

	@Override
	protected void onImpact(MovingObjectPosition var1) {
		if (var1.entityHit != null) {
			var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
		} else {
			int x = var1.blockX;
			int y = var1.blockY;
			int z = var1.blockZ;

			if (item != null) {
				if (ItemGun.getBullet(EnumItem.TORCH_RIFLE).contains(item.itemID)) {
					switch (var1.sideHit) {
					case 0:
						--y;
						break;
					case 1:
						++y;
						break;
					case 2:
						--z;
						break;
					case 3:
						++z;
						break;
					case 4:
						--x;
						break;
					case 5:
						++x;
					}

					if (Block.torchWood.canPlaceBlockAt(this.worldObj, x, y, z) && this.worldObj.isAirBlock(x, y, z)) {
						if (this.worldObj.setBlockAndMetadataWithNotify(x, y, z, Block.torchWood.blockID, 0, 3) == false) {
							this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, x, y, z, item));
						}
					} else {

						this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, x, y, z, new ItemStack(Block.torchWood, 1)));
					}
				} else if (ItemGun.getBullet(EnumItem.PICKAXE_RIFLE).contains(item.itemID)) {
					int blockId = this.worldObj.getBlockId(x, y, z);
					if (Block.blocksList[blockId] != null) {
						if (Block.blocksList[blockId].getBlockHardness(this.worldObj, x, y, z) >= 0.0F) {
							int metadata = this.worldObj.getBlockMetadata(x, y, z);
							Block.blocksList[blockId].dropBlockAsItem(worldObj, x, y, z, metadata, 0);
							this.worldObj.setBlockAndMetadataWithNotify(x, y, z, 0, 0, 3);
						}
					}
				}
			}
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		if (this.item != null) {
			par1nbtTagCompound.setCompoundTag("Item", this.item.writeToNBT(new NBTTagCompound()));
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		NBTTagCompound var2 = par1nbtTagCompound.getCompoundTag("Item");
		this.item = ItemStack.loadItemStackFromNBT(var2);
		if (this.item == null || this.item.stackSize <= 0) {
			this.setDead();
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataWatcher.addObject(20, 0);
		dataWatcher.addObject(21, 0);
	}

	int getItemId() {
		return dataWatcher.getWatchableObjectInt(20);
	}

	int getItemDamage() {
		return dataWatcher.getWatchableObjectInt(21);
	}
}

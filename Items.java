package torchrifle;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class Items {

	private static final String ITEMS_PNG = "/torchrifle/items.png";

	public static Item itemTorchRifle = new ItemGun(TorchRifle.getId(EnumItem.TORCH_RIFLE), EnumItem.TORCH_RIFLE)
	.setUnlocalizedName("TorchRifle")
	.setCreativeTab(TorchRifle.tabTorchRifle);

	public static Item itemPickaxeRifle = new ItemGun(TorchRifle.getId(EnumItem.PICKAXE_RIFLE), EnumItem.PICKAXE_RIFLE)
	.setUnlocalizedName("PickaxeRifle")
	.setCreativeTab(TorchRifle.tabTorchRifle);

	public static void init() {
		LanguageRegistry.instance().addNameForObject(itemTorchRifle, "en_US", "Torch Rifle");
		LanguageRegistry.instance().addNameForObject(itemPickaxeRifle, "en_US", "Pickaxe Rifle");
	}

	@SideOnly(Side.CLIENT)
	public static void registerTextures() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
	}

}

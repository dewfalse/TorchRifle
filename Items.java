package torchrifle;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static Item itemTorchRifle = new ItemGun(TorchRifle.getId(EnumItem.TORCH_RIFLE), EnumItem.TORCH_RIFLE)
	.setUnlocalizedName("TorchRifle")
	.setTextureName("torchrifle:itemgun1")
	.setCreativeTab(TorchRifle.tabTorchRifle);

	public static Item itemPickaxeRifle = new ItemGun(TorchRifle.getId(EnumItem.PICKAXE_RIFLE), EnumItem.PICKAXE_RIFLE)
	.setUnlocalizedName("PickaxeRifle")
	.setTextureName("torchrifle:itemgun2")
	.setCreativeTab(TorchRifle.tabTorchRifle);

	public static void init() {
		LanguageRegistry.instance().addNameForObject(itemTorchRifle, "en_US", "Torch Rifle");
		LanguageRegistry.instance().addNameForObject(itemPickaxeRifle, "en_US", "Pickaxe Rifle");
	}

}

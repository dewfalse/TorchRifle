package torchrifle;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void init() {
		GameRegistry.addRecipe(new ItemStack(Items.itemPickaxeRifle, 1, 0), new Object[] {
			"PII",
			"  I",
			'P', Item.pickaxeDiamond,
			'I', Item.ingotIron
		});
		GameRegistry.addRecipe(new ItemStack(Items.itemTorchRifle, 1, 0), new Object[] {
			"TII",
			"  I",
			'T', Block.torchWood,
			'I', Item.ingotIron
		});
	}
}

package torchrifle;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabTorchRifle extends CreativeTabs {

	public CreativeTabTorchRifle(String label) {
		super(label);
	}

	@Override
	public String getTabLabel() {
		return "TorchRifle";
	}

	@Override
	public Item getTabIconItem() {
		return Items.itemTorchRifle;
	}

}

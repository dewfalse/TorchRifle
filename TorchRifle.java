package torchrifle;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "TorchRifle", name = "TorchRifle", version = "1.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = true, channels = { "torch" }, packetHandler = PacketHandler.class, connectionHandler = ConnectionHandler.class, versionBounds = "[1.0]")
public class TorchRifle {
	@SidedProxy(clientSide = "torchrifle.ClientProxy", serverSide = "torchrifle.CommonProxy")
	public static CommonProxy proxy;

	@Instance("TorchRifle")
	public static TorchRifle instance;

	public static Logger logger = Logger.getLogger("Minecraft");

	public static boolean debug = false;

	public static final CreativeTabs tabTorchRifle = new CreativeTabTorchRifle("TorchRifle");

	public static int id = 1800;

	public static int entityBulletId;

	@Mod.Init
	public void load(FMLInitializationEvent event) {
		Items.init();
		Entities.init();
		Recipes.init();
		proxy.init();
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

		try {
			cfg.load();
			id = cfg.get(Configuration.CATEGORY_ITEM, "itemId", 1800).getInt();
			entityBulletId = cfg.get(Configuration.CATEGORY_GENERAL, "entityBulletId", 116).getInt();

			cfg.save();

			Property debug = cfg.get(Configuration.CATEGORY_GENERAL, "debug", false);
			this.debug = debug.getBoolean(false);
		}
		catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, "TorchRifle load config exception");
        }
        finally
        {
            cfg.save();
        }
	}

	public static int getId(EnumItem e) {
		return e.ordinal() + id;
	}

}

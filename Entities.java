package torchrifle;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Entities {

	public static void init() {
		EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", TorchRifle.entityBulletId, TorchRifle.instance, 64, 2, true);
		EntityRegistry.registerGlobalEntityID(EntityBullet.class, "Bullet", TorchRifle.entityBulletId);

	}

	@SideOnly(Side.CLIENT)
	public static void registerRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());

	}

}

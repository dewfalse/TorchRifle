package torchrifle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		Items.registerTextures();
		Entities.registerRenderer();
	    MinecraftForgeClient.preloadTexture("/torchrifle/items.png");
		copyResources();
	}

	@Override
	File getDirectory() {
		return Minecraft.getMinecraftDir();
	}

	public void copyResources() {
		File root = new File(Minecraft.getMinecraftDir(), "mods/torchrifle/textures/items/");
		root.mkdirs();
		try {
			File resource1 = new File(root, "itemgun1.png");
			if (!resource1.exists()) {
				copyFromResource(resource1, "/itemgun1.png");
			}
			File resource2 = new File(root, "itemgun2.png");
			if (!resource2.exists()) {
				copyFromResource(resource2, "/itemgun2.png");
			}
		} catch (IOException e) {
			// TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
			e.printStackTrace();
		}

	}

	public void copyFromResource(File file, String res) throws IOException {
		InputStream inputRes = this.getClass().getResourceAsStream(res);
		if (inputRes == null) {
			return;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputRes));
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(
				file, true)));

		try {
			while (reader.ready()) {
				writer.println(reader.readLine());
			}
		} finally {
			reader.close();
			writer.close();
		}
	}

}

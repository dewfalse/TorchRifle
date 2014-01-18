package torchrifle;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderBullet extends Render {

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {

        if(var1 instanceof EntityBullet) {
        	EntityBullet e = (EntityBullet)var1;
            int itemId = e.getItemId();
            int itemDamage = e.getItemDamage();

            GL11.glPushMatrix();
            GL11.glTranslatef((float)var2, (float)var4, (float)var6);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(var1);
            Tessellator var10 = Tessellator.instance;

            //List<EntityBullet> list = var1.worldObj.getEntitiesWithinAABB(EntityBullet.class, e.boundingBox.expand((double)8F, (double)8F, (double)8F));
            //EntityBullet e2 = (EntityBullet)
	        this.func_77026_a(var10, Item.itemsList[itemId].getIconFromDamage(itemDamage));
	        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	        GL11.glPopMatrix();
        }
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1)
    {
        if(var1 instanceof EntityBullet) {
        	EntityBullet e = (EntityBullet)var1;
            int itemId = e.getItemId();
            if(itemId == Block.torchWood.blockID) {
            	return TextureMap.locationBlocksTexture;
            }
            else {
            	return TextureMap.locationItemsTexture;
            }
        }
        return null;
    }

    private void func_77026_a(Tessellator par1Tessellator, Icon par2Icon)
    {
        float f = par2Icon.getMinU();
        float f1 = par2Icon.getMaxU();
        float f2 = par2Icon.getMinV();
        float f3 = par2Icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV((0.0F - f5), (0.0F - f6), 0.0D, f, f3);
        par1Tessellator.addVertexWithUV((f4 - f5), (0.0F - f6), 0.0D, f1, f3);
        par1Tessellator.addVertexWithUV((f4 - f5), (f4 - f6), 0.0D, f1, f2);
        par1Tessellator.addVertexWithUV((0.0F - f5), (f4 - f6), 0.0D, f, f2);
        par1Tessellator.draw();
    }
}

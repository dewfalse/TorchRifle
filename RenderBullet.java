package torchrifle;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

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
            if(itemId == Block.torchWood.blockID) {
                this.loadTexture("/terrain.png");
            }
            else {
                this.loadTexture("/gui/items.png");
            }
            Tessellator var10 = Tessellator.instance;

            //List<EntityBullet> list = var1.worldObj.getEntitiesWithinAABB(EntityBullet.class, e.boundingBox.expand((double)8F, (double)8F, (double)8F));
            //EntityBullet e2 = (EntityBullet)
	        this.func_77026_a(var10, Item.itemsList[itemId].getIconFromDamage(itemDamage));
	        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
	        GL11.glPopMatrix();
        }
	}

    private void func_77026_a(Tessellator par1Tessellator, Icon par2Icon)
    {
        float var3 = par2Icon.func_94209_e();
        float var4 = par2Icon.func_94212_f();
        float var5 = par2Icon.func_94206_g();
        float var6 = par2Icon.func_94210_h();
        float var7 = 1.0F;
        float var8 = 0.5F;
        float var9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(0.0F - var9), 0.0D, (double)var3, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(0.0F - var9), 0.0D, (double)var4, (double)var6);
        par1Tessellator.addVertexWithUV((double)(var7 - var8), (double)(var7 - var9), 0.0D, (double)var4, (double)var5);
        par1Tessellator.addVertexWithUV((double)(0.0F - var8), (double)(var7 - var9), 0.0D, (double)var3, (double)var5);
        par1Tessellator.draw();
    }
}

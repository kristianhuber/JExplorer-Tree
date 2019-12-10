package explorer.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * @Author: Me
 * @Description: Class That Draws Font on the Screen
 * 
 */

public class Font {
	public static final String INDEX = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz0123456789";
	public static Texture letters;
	
	/**
	 * Loads the Font File
	 * 
	 */
	public static void initalizeFont(){
		
		// Loads the File Here
		try {

			letters = TextureLoader.getTexture("PNG", Font.class
					.getResourceAsStream("/explorer/assets/gui/font.png"));
			
			System.out.println("[Console]: Loaded Font Sheet");
		} catch (Exception e) {

			System.err.println("[Error]: Could not Find Texture for Fonts");
		}
	}
	/**
	 * Draws Font at the Regular Size
	 * 
	 */
	public static void drawFont(String text, int x, int y) {

		drawFont(text, x, y, 0.5F);
	}

	/**
	 * Draws Font at a Scale
	 * 
	 */
	public static void drawFont(String text, int x, int y, float scale) {
		float xx = 0.0F;
		float yy = 0.0F;
		
		glEnable(GL_TEXTURE_2D);

		//Runs Through Each Letter
		for (int i = 0; i < text.length(); i++) {

			//Gets the Index From INDEX
			for (int j = 0; j < INDEX.length(); j++) {

				//Sets the Coordinate on the Texture Map
				if (text.substring(i, i + 1).equals(INDEX.substring(j, j + 1))) {

					xx = (j % 8) * 0.125F;
					yy = (j / 8) * 0.125F;
					break;
				}
			}

			//Actually Renders the Font
			glPushMatrix();
			{

				glColor3f(1.0F, 1.0F, 1.0F);
				letters.bind();

				float q = 64 * scale;
				glBegin(GL_QUADS);
				{
					glTexCoord2f(xx, yy);
					glVertex2f(x + (i * q), y);

					glTexCoord2f(xx + 0.125F, yy);
					glVertex2f(x + (i * q) + q, y);

					glTexCoord2f(xx + 0.125F, yy + 0.125F);
					glVertex2f((x + (i * q) + q), y + q);

					glTexCoord2f(xx, yy + 0.125F);
					glVertex2f(x + (i * q), y + q);
				}
				glEnd();
			}
			glPopMatrix();
		}
		glDisable(GL_TEXTURE_2D);
	}
}

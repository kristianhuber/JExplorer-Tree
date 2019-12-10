package explorer.world;

import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ground {
	public float[][] height;

	public Ground() {

		height = new float[Sector.DIM + 1][Sector.DIM + 1];
	}

	public void loadGround() {

		try {

			BufferedImage heightmapImage = ImageIO
					.read(getClass().getResourceAsStream(
							"/explorer/assets/maps/height_map.png"));

			Color color;

			for (int z = 0; z < Sector.DIM; z++) {

				for (int x = 0; x < Sector.DIM; x++) {

					color = new Color(heightmapImage.getRGB(z, x));
					height[z][x] = color.getRed() - 100;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawGround(int x, int y) {

		glPushMatrix();
		{

			glColor3f(0.0F, 0.25F, 0.0F);
			for (int z = 0; z < height.length - 1; z++) {

				glBegin(GL_TRIANGLE_STRIP);
				for (int x2 = 0; x2 < height[z].length; x2++) {

					glVertex3f(x2 + x, height[z][x2], z + y);

					glVertex3f(x2 + x, height[z + 1][x2], z + 1 + y);
				}
				glEnd();
			}
		}
		glPopMatrix();
	}
}

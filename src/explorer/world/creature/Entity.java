package explorer.world.creature;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.util.vector.Vector3f;

import explorer.util.ModelManager;
import explorer.world.World;
import explorer.world.creature.Model.Face;

/**
 * @Author: Me
 * @Description: 'Living' Objects of the World
 * 
 */
public class Entity {

	public float x, y, z, prevX, prevY, prevZ, pitchX;

	public String ID;

	private Model m;

	/**
	 * Constructor Method
	 * 
	 */
	public Entity(String ID, World w, float x, float y, float z) {

		this.ID = ID;

		this.m = ModelManager.MODELS.get(ID);

		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Simplified Movement
	 * 
	 */
	public void move(float amt, float dir) {

		x += (amt * Math.cos(Math.toRadians(pitchX + 90 * dir)));
		z += (amt * Math.sin(Math.toRadians(pitchX + 90 * dir)));
	}

	/**
	 * Updates the Entity
	 * 
	 */
	public void tick() {

		prevX = x;
		prevY = y;
		prevZ = z;

		if (pitchX > 360) {

			pitchX = 0;
		}

		if (pitchX < 0) {

			pitchX = 360;
		}

		render();
	}

	public void render() {

		glPushMatrix();
		{
			if (!this.ID.equals("player")) {

				glTranslatef(x, y, z);
			}else{
				
				glTranslatef(0, -1, 0);
			}
			
			glColor3f(0.25F, 0.0F, 0.0F);

			glBegin(GL_TRIANGLES);
			{

				for (Face face : m.Faces) {

					Vector3f n1 = m.Normals.get((int) face.normal.x - 1);
					glNormal3f(n1.x, n1.y, n1.z);

					Vector3f v1 = m.Vertecies.get((int) face.vertex.x - 1);
					glVertex3f(v1.x, v1.y, v1.z);

					Vector3f n2 = m.Normals.get((int) face.normal.y - 1);
					glNormal3f(n2.x, n2.y, n2.z);

					Vector3f v2 = m.Vertecies.get((int) face.vertex.y - 1);
					glVertex3f(v2.x, v2.y, v2.z);

					Vector3f n3 = m.Normals.get((int) face.normal.z - 1);
					glNormal3f(n3.x, n3.y, n3.z);

					Vector3f v3 = m.Vertecies.get((int) face.vertex.z - 1);
					glVertex3f(v3.x, v3.y, v3.z);
				}
			}
			glEnd();

		}
		glPopMatrix();

	}
}

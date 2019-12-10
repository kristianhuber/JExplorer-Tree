package explorer.world.creature;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;

import explorer.main.JExplorer;
import explorer.main.JExplorer.RenderType;
import explorer.world.World;

/**
 * @Author: Me
 * @Description: This One is Self-Explanatory
 * 
 */

public class Player extends Entity {
	public static final float CAMERASPEED = 0.1F, SCROLLSPEED = 1.4F;
	private float pitchY, pitchZ;

	/**
	 * Constructor Method
	 * 
	 */
	public Player(World w) {

		super("player", w, -15, 0, -15);
		pitchY = 0;
		pitchZ = 0;

		// Initializes Projection
		JExplorer.init3D();
		
		// Initializes Variables
		this.x = -15;
		this.y = -2.5F;
		this.z = -15;
	}

	public FloatBuffer asFloatBuffer(float[] f) {
		FloatBuffer b = BufferUtils.createFloatBuffer(f.length);
		b.put(f);
		b.flip();

		return b;
	}

	/**
	 * Override the Entity Class
	 * 
	 */
	@Override
	public void tick() {
		super.tick();

		setView();
		keyTick();
		drawStatBar();
	}

	/**
	 * Displays the statistics
	 * 
	 */
	public void drawStatBar() {
		
	}

	/**
	 * Sets the Camera Position
	 * 
	 */
	private void setView() {

		glRotatef(pitchX, 0, 1, 0);
		glRotatef(pitchY, 1, 0, 0);
		glRotatef(pitchZ, 0, 0, 1);

		glTranslatef(x, y, z);
	}

	public void setAngle(float amt, float dir) {

		pitchY += (amt * Math.cos(Math.toRadians(pitchX + 90 * dir)));
		pitchZ += (amt * Math.sin(Math.toRadians(pitchX + 90 * dir)));
	}

	/**
	 * Handles the Keyboard
	 * 
	 */
	private void keyTick() {

		// Exits the Program
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {

			JExplorer.W = null;
			JExplorer.init2D();	
			JExplorer.R = RenderType.MAIN_MENU;
		}

		// Up and Down Movement
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {

			this.y -= 0.1F;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {

			this.y += 0.1F;
		}

		// Forward and Back Movement
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {

			move(CAMERASPEED, 1);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {

			move(-CAMERASPEED, 1);
		}

		// Side to Side Movement
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {

			move(CAMERASPEED, 0);
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {

			move(-CAMERASPEED, 0);
		}

		// Side to Side View
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {

			pitchX += SCROLLSPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {

			pitchX -= SCROLLSPEED;
		}
	}
}

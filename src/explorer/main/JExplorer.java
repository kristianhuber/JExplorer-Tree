package explorer.main;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_LIGHT_MODEL_AMBIENT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColorMaterial;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLightModel;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL20;

import explorer.gui.Font;
import explorer.gui.MainMenu;
import explorer.util.ModelManager;
import explorer.util.TextureManager;
import explorer.world.World;

/**
 * @Author: Me
 * @Description: The Main Class for the JExplorer Program
 * 
 */

public class JExplorer {
	public static RenderType R = RenderType.MAIN_MENU;
	public static int WIDTH = 1366, HEIGHT = 768;
	public static World W;

	/**
	 * Render Types
	 * 
	 */
	public enum RenderType {

		MAIN_MENU, GAME;
	}

	/**
	 * Constructor Method
	 * 
	 */
	public JExplorer() {

		// Creates the Display
		try {
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("JExplorer Alpha[v1.0]");
			Display.setVSyncEnabled(true);
			Display.setResizable(false);
			Display.sync(60);

			Display.create();

		} catch (LWJGLException e) {

			e.printStackTrace();
		}

		// Initializes Projection
		JExplorer.init2D();

		// Loads Resources
		this.loadResources();
	}

	/**
	 * Initializes Resources
	 * 
	 */
	public void loadResources() {

		// Loads All of the Fonts
		System.out
				.println("--------------------------------------------------\n"
						+ "[Console]: Loading Fonts...");
		Font.initalizeFont();
		MainMenu.init();

		// Loads All of the Textures
		System.out
				.println("--------------------------------------------------\n"
						+ "[Console]: Loading Textures...");
		TextureManager.registerTexture("grass");

		// Loads All of the Models
		System.out
				.println("--------------------------------------------------\n"
						+ "[Console]: Loading Models...");
		ModelManager.registerModel("tree_oak");
		ModelManager.registerModel("player");
	}

	/**
	 * Sets up the Projection for 2D
	 * 
	 */
	public static void init2D() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_CULL_FACE);
		glDisable(GL_LIGHTING);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}

	/**
	 * Sets up the Projection for 3D
	 * 
	 */
	public static void init3D() {

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(75, 1.6F, 0.3F, 125);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);

		// Lighting	
		glShadeModel(GL_SMOOTH);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
        glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{0, -50, 0, 1}));
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
		
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);

	}

	/**
	 * Makes float Arrays Easier
	 * 
	 */
	public static FloatBuffer asFloatBuffer(float[] f) {
		FloatBuffer b = BufferUtils.createFloatBuffer(f.length);
		b.put(f);
		b.flip();

		return b;
	}

	/**
	 * Where the Loop Runs
	 * 
	 */
	public void start() {

		// Main Loop
		while (!Display.isCloseRequested()) {

			// Resets the Screen
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();

			// Decides What to Render
			if (R == RenderType.MAIN_MENU) {

				MainMenu.render();
			} else if (R == RenderType.GAME) {

				W.tick();
			}

			// Updates the Display
			Display.update();
		}
		
		GL20.glDeleteProgram(W.lands[0][0].s.shaderProgram);

		// Ends the Program
		Display.destroy();
	}

	/**
	 * Entry Method
	 * 
	 */
	public static void main(String[] args) {

		new JExplorer().start();
	}
}

package explorer.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import explorer.main.JExplorer;

/**
 * @Author: Me
 * @Description: The Main Menu for the JExplorer Program
 * 
 */

public class MainMenu {
	private static Button singleplayer, multiplayer, settings, exit, mainmenu;
	
	/**
	 * Constructor Method
	 * 
	 */
	public static void init() {
		
		singleplayer = new Button("Eins", "Single Player", 425, 150, 500, 75);
		multiplayer = new Button("Swei", "Multiplayer", 425, 250, 500, 75);
		settings = new Button("Drei", "Settings", 425, 350, 500, 75);
		exit = new Button("Vear", "Exit", 425, 450, 500, 75);
		mainmenu = new Button("Funf", "Main Menu", 425, 350, 500, 75);
	}
	
	/**
	 * Renders the Screen
	 * 
	 */
	public static void render(){
		
		//Draws the Background
		glBegin(GL_QUADS);
		{
			
			glColor3f(0.0F, 0.0F, 0.0F);
			glVertex2f(0, 0);
			glVertex2f(JExplorer.WIDTH, 0);
			
			glColor3f(0.1F, 0.0F, 0.0F);
			glVertex2f(JExplorer.WIDTH, JExplorer.HEIGHT);
			glVertex2f(0, JExplorer.HEIGHT);
		}
		glEnd();
		
		Font.drawFont("Java Explorer", 300, 25, 1.0F);
		
		//Updates the Buttons
		singleplayer.tick();
		multiplayer.tick();
		settings.tick();
		exit.tick();
	}
	
	public static void gameOver(){
		
		glBegin(GL_QUADS);
		{
			
			glColor3f(0.0F, 0.0F, 0.0F);
			glVertex2f(0, 0);
			glVertex2f(JExplorer.WIDTH, 0);
			
			glColor3f(0.1F, 0.0F, 0.0F);
			glVertex2f(JExplorer.WIDTH, JExplorer.HEIGHT);
			glVertex2f(0, JExplorer.HEIGHT);
		}
		glEnd();
		
		Font.drawFont("You Have Died", 300, 25, 1.5F);
		
		exit.tick();
		mainmenu.tick();
	}
}

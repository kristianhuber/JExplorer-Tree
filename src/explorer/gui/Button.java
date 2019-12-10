package explorer.gui;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.input.Mouse;

import explorer.main.JExplorer;
import explorer.main.JExplorer.RenderType;
import explorer.world.World;

/**
 * @Author: Me
 * @Description: Click a Rectangle that Does Stuff
 * 
 */

public class Button {
	private int x, y, width, height;
	private boolean isSelected;
	private String text, ID;

	/**
	 * Constructor Method
	 * 
	 */
	public Button(String ID, String text, int x, int y, int width, int height) {

		//Initialize Variables
		this.height = height;
		this.width = width;
		this.text = text;
		this.ID = ID;
		this.x = x;
		this.y = y;
	}

	/**
	 * Handle the Events
	 * 
	 */
	public void tick() {

		//Decides if the Mouse is Over the Button
		if (Mouse.getX() > this.x && Mouse.getX() < this.x + width
				&& (JExplorer.HEIGHT - Mouse.getY()) > this.y
				&& (JExplorer.HEIGHT - Mouse.getY()) < this.y + height) {

			isSelected = true;
		} else {

			isSelected = false;
		}
		
		//Click Events
		if(this.isSelected && Mouse.isButtonDown(0)){
			
			switch(ID){
			case "Eins":
				
				//SinglePlayer: Starts the Game
				JExplorer.R = RenderType.GAME;
				JExplorer.W = new World();
				break;
			case "Vear":
				
				System.exit(0);
				break;
			case "Funf":
				JExplorer.R = RenderType.MAIN_MENU;
				break;
			}
		}

		render();
	}

	/**
	 * Draws the Button on the Screen
	 * 
	 */
	public void render() {
		
		//Draws the Square
		glPushMatrix();
		{

			glBegin(GL_QUADS);
			{
				glColor3f(0.25F, 0.0F, 0.0F);
				glVertex2f(x, y);
				glVertex2f(x + width, y);
				glVertex2f(x + width, y + height);
				glVertex2f(x, y + height);
				
				//Lightens the Color if Selected
				if(isSelected){
					glColor3f(0.05F, 0.05F, 0.05F);	
				}else{
					glColor3f(0.0F, 0.0F, 0.0F);
				}
				
				glVertex2f(x + 3, y + 3);
				glVertex2f(x + width - 3, y + 3);
				glVertex2f(x + width - 3, y + height - 3);
				glVertex2f(x + 3, y + height - 3);
			}
			glEnd();
		}
		glPopMatrix();
		
		Font.drawFont(text, x + (width / 10), y + (height / 3));
	}
}

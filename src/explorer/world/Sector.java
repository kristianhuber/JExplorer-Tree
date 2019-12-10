package explorer.world;

import java.util.ArrayList;

import org.lwjgl.opengl.GL20;

import explorer.world.creature.Entity;
import explorer.world.light.Shader;
import explorer.world.nature.Tree;

/**
 * @Author: Me
 * @Decscription: Similar to Minecraft Chunks, they are Subdivisions.
 * 
 */

public class Sector {
	public static final int DIM = 75;
	
	private int x, y;
	private ArrayList<Entity> entities;
	private Ground g;
	public World w;	
	public Shader s;

	/**
	 * Constructor Method
	 * 
	 */
	public Sector(World w, int x, int y) {

		// Initialize Variables
		entities = new ArrayList<Entity>();
		this.w = w;
		this.x = x;
		this.y = y;
		this.g = new Ground();
		
		s = new Shader();
		
		entities.add(new Tree(Tree.OAK, w, 15, 0, 15));
	}

	public void addEntity(Entity e) {

		this.entities.add(e);
	}

	/**
	 * Updates the Blocks in the Sector
	 * 
	 */
	public void tick() {
		GL20.glUseProgram(s.shaderProgram);
		
		GL20.glUniform1f(s.diffuseModifier, 5.0F);
		
		g.drawGround(x * Sector.DIM, y * Sector.DIM);
		
		for (int e = 0; e < entities.size(); e++) {

			entities.get(e).tick();
		}
		
		GL20.glUseProgram(0);
	}
}

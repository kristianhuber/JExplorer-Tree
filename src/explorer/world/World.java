package explorer.world;

import explorer.world.creature.Player;


/**
 * @Author: Me
 * @Description: Holds all of the Variables for Blocks and Entities
 * 
 */

public class World {
	public static float ACCELERATION = 9.8F;
	
	public Sector[][] lands;
	public Player player;

	/**
	 * Constructor Method
	 * 
	 */
	public World() {

		// Initializes the Variables
		lands = new Sector[1][1];
		player = new Player(this);
	}

	/**
	 * Updates the World and its objects
	 * 
	 */
	public void tick() {

		// Updates the Player
		player.tick();

		int px = (int) -(player.x / Sector.DIM);
		int py = (int) -(player.z / Sector.DIM);
		
		if(lands[px][py] != null){
			
			lands[px][py].tick();
		}else{
			
			lands[px][py] = new Sector(this, px, py);
		}
	}
}

package explorer.world.nature;

import explorer.world.World;
import explorer.world.creature.Entity;

public class Tree extends Entity{
	public static final String OAK = "oak";
	
	public Tree(String ID, World w, float x, float y, float z) {
		
		super("tree_" + ID, w, x, y, z);
	}
}

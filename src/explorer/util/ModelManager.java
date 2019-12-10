package explorer.util;

import java.util.HashMap;

import explorer.world.creature.Model;

public class ModelManager {
	public static HashMap<String, Model> MODELS = new HashMap<String, Model>();
	
	/**
	 * Loads the Texture
	 * 
	 */
	public static void registerModel(String ID) {

		//Loads via IO
		Model m = null;
		try {

			m = new Model(ID);
			
			System.out.println("[Console]: Loaded Model " + ID);
		} catch (Exception e) {
			
		}

		//Stores the Texture
		MODELS.put(ID, m);
	}
}

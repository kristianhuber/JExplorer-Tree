package explorer.util;

import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * @Author: Me
 * @Description: Registers Textures so the Game Doesn't Lag
 * 
 */

public class TextureManager {
	public static HashMap<String, Texture> TEXTURES = new HashMap<String, Texture>();

	/**
	 * Loads the Texture
	 * 
	 */
	public static void registerTexture(String ID) {

		//Loads via IO
		Texture t = null;
		try {

			t = TextureLoader.getTexture(
					"PNG",
					TextureManager.class.getResourceAsStream("/explorer/assets/textures/" + ID
							+ ".png"));
			
			System.out.println("[Console]: Loaded Texture " + ID);
		} catch (Exception e) {

			System.err.println("[Error]: Could Not Find Texture For " + ID);
		}

		//Stores the Texture
		TEXTURES.put(ID, t);
	}
}

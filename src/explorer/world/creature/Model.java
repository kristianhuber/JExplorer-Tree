package explorer.world.creature;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class Model {

	public class Face {

		public Vector3f vertex = new Vector3f();
		public Vector3f normal = new Vector3f();

		public Face(Vector3f vertex, Vector3f normal) {

			this.vertex = vertex;
			this.normal = normal;
		}
	}

	public List<Vector3f> Vertecies = new ArrayList<Vector3f>();
	public List<Vector3f> Normals = new ArrayList<Vector3f>();
	public List<Face> Faces = new ArrayList<Face>();

	public Model(String ID) {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					this.getClass().getResourceAsStream("/explorer/assets/models/" + ID + ".obj")));
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				if (line.startsWith("v ")) {

					float x = Float.valueOf(line.split(" ")[1]);
					float y = Float.valueOf(line.split(" ")[2]);
					float z = Float.valueOf(line.split(" ")[3]);

					Vertecies.add(new Vector3f(x, y, z));

				} else if (line.startsWith("vn ")) {

					float x = Float.valueOf(line.split(" ")[1]);
					float y = Float.valueOf(line.split(" ")[2]);
					float z = Float.valueOf(line.split(" ")[3]);

					Normals.add(new Vector3f(x, y, z));
				} else if (line.startsWith("f ")) {

					Vector3f vertexIndex = new Vector3f(Float.valueOf(line
							.split(" ")[1].split("/")[0]), Float.valueOf(line
							.split(" ")[2].split("/")[0]), Float.valueOf(line
							.split(" ")[3].split("/")[0]));
					
					Vector3f normalIndex = new Vector3f(Float.valueOf(line
							.split(" ")[1].split("/")[2]), Float.valueOf(line
							.split(" ")[2].split("/")[2]), Float.valueOf(line
							.split(" ")[3].split("/")[2]));
					
					Faces.add(new Face(vertexIndex, normalIndex));
				}
				
			}
			
			reader.close();
		} catch (Exception e) {

			System.err.println("[Error]: Could not Find Model for " + ID);
		}
	}
}

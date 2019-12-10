package explorer.world.light;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;

import java.io.BufferedReader;
import java.io.FileReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {

	public int fragmentShader;
	public int shaderProgram;
	public int vertexShader;
	public int diffuseModifier;

	public Shader() {

		shaderProgram = glCreateProgram();
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

		loadFiles();

		GL20.glAttachShader(shaderProgram, vertexShader);
		GL20.glAttachShader(shaderProgram, fragmentShader);

		GL20.glLinkProgram(shaderProgram);
		GL20.glValidateProgram(shaderProgram);

		diffuseModifier = GL20.glGetUniformLocation(shaderProgram,
				"diffuseIntensityModifier");
	}

	protected void loadFiles() {

		System.out
				.println("--------------------------------------------------\n"
						+ "[Console]: Loading Shaders...");

		StringBuilder fragmentShaderSource = new StringBuilder();
		StringBuilder vertexShaderSource = new StringBuilder();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(
					"src/explorer/world/light/VertexShader.txt"));

			String line;

			while ((line = reader.readLine()) != null) {

				vertexShaderSource.append(line).append("\n");
			}

			reader.close();

			BufferedReader reader2 = new BufferedReader(new FileReader(
					"src/explorer/world/light/FragmentShader.txt"));

			String line2;

			while ((line2 = reader2.readLine()) != null) {

				fragmentShaderSource.append(line2).append("\n");
			}

			reader2.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		GL20.glShaderSource(vertexShader, vertexShaderSource);
		GL20.glCompileShader(vertexShader);

		if (GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

			System.err
					.println("[Error]: Vertex Shader Wasn't Compiled Correctly");

			System.err.println(GL20.glGetShaderInfoLog(vertexShader, 500));
		} else {

			System.out
					.println("[Console]: Vertex Shader Was Sucessfully Compiled");
		}

		GL20.glShaderSource(fragmentShader, fragmentShaderSource);
		GL20.glCompileShader(fragmentShader);

		if (GL20.glGetShaderi(fragmentShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

			System.err
					.println("[Error]: Fragment Shader Wasn't Compiled Correctly");

			System.err.println(GL20.glGetShaderInfoLog(vertexShader, 500));
		} else {

			System.out
					.println("[Console]: Fragment Shader Was Sucessfully Compiled");
		}
	}
}

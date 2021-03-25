package engine.shaders;

import engine.MathUtils;
import engine.Shader;

public class BasicShader extends Shader {

	public BasicShader(int width, int height) {
		super("engine/shaders/shader.vert", "engine/shaders/shader.frag");
		
		enable();
		setUniformMat4f("projectionMatrix",
				MathUtils.createProjectionMatrix(width, height, 70, 0.01f, 100f));
		disable();
	}

}

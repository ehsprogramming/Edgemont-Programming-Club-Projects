package engine;

import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;

import java.awt.Color;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL45;

import engine.util.Logger;

public class Model {

	protected int vaoID;
	protected int vertexCount;
	
	protected int[] vbos;
	
	Color color;
	
	public Model(Color color, int vaoID, int vertexCount, int...vbos){
		this.color = color;
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.vbos = vbos;
	}
	
	/** Load 3d model */
	public Model(float[] positions, float[] textureCoords, int[] indices, float[] normals, Color color){
		this(color, createVertexArray(), indices.length);
		
		GL30.glBindVertexArray(vaoID);
		
		bindIndicesBuffer(indices);
		storeDataInAttribute(positions, 0, 3);
		storeDataInAttribute(textureCoords, 1, 2);
		storeDataInAttribute(normals, 2, 3);
		
		GL30.glBindVertexArray(0);
	}
	
	/** Load 2d model */
	public Model(float[] positions, float[] textureCoords, int[] indices, Color color){
		this(color, createVertexArray(), indices.length);
		
		GL30.glBindVertexArray(vaoID);
		Texture.error("BIND VERTEX ARRAY 1");
		bindIndicesBuffer(indices);
		Texture.error("STORE INDICES");
		storeDataInAttribute(positions, 0, 2);
		Texture.error("STORE POSITIONS");
		storeDataInAttribute(textureCoords, 1, 2);
		Texture.error("STORE TEXTURE COORDS");
		
		GL30.glBindVertexArray(0);
		Texture.error("BIND VERTEX ARRAY");
	}
	
	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
	public void unbindDraw() {
		GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
	}
	
	public void bind() {
		GL30.glBindVertexArray(vaoID);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
	}
	
	public void unbind() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	
	/** Draws the model and sets the color in the shader. If no color is specified in this model, 
	 * the color is set to black. The color then is added to the texture color. */
	public void draw(Shader shader){
		if(color != null)
			if(shader.hasUniform("color"))
				shader.setUniform4f("color", color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
			else
				Logger.warn("Warning: Attempted to set color of model when shader contains no such uniform variable", 1);
		else
			shader.setUniform4f("color", 0, 0, 0, 0);
		
		bind();
		unbindDraw();
		unbind();
	}
	
	/** Draws the model and sets the color in the shader. If no color is specified in this model, 
	 * the color is set to black. The color then is added to the texture color. */
	public void draw(Texture t, Shader s){
		t.bind();
		draw(s);
		t.unbind();
	}
	
	protected int bindIndicesBuffer(int[] indices) {
		int ibo = GL15.glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, MathUtils.toIntBuffer(indices), GL_STATIC_DRAW);
		return ibo;
	}
	
	protected int storeDataInAttribute(float[] data, int attribNum, int values) {
		int vbo = GL15.glGenBuffers();
		glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		glBufferData(GL15.GL_ARRAY_BUFFER, MathUtils.toFloatBuffer(data), GL_STATIC_DRAW);
		
		GL20.glVertexAttribPointer(attribNum, values, GL11.GL_FLOAT, false, 0, 0);
		GL20.glEnableVertexAttribArray(attribNum);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		return vbo;
	}
	
	static int createVertexArray() {
		if(GL.getCapabilities().OpenGL45) {
			return GL45.glCreateVertexArrays();
		}else{
			return GL30.glGenVertexArrays();
		}
	}
	
	public void destroy(){
		for(int v: vbos)
			GL15.glDeleteBuffers(v);
		GL30.glDeleteVertexArrays(vaoID);
	}
	
	@Override
	public int hashCode() {
		return vaoID;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Model)) return false;
		Model m = (Model) other;
		return m.vaoID == vaoID;
	}
	
}

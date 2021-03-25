package engine.models;

import java.awt.Color;

import engine.Model;

public class Cube extends Model {
	
	public Cube(Color color) {
		super(vertices, textureCoords, indices, normals, color);
	}
	
	static float[] vertices = {	//Vertices are scaled by 1/2 so they fit in a 1x1x1 area		
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,0.5f,-0.5f,		
			
			-0.5f,0.5f,0.5f,	
			-0.5f,-0.5f,0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			-0.5f,-0.5f,0.5f,	
			-0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,
			
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
			
	};
	
	static float[] normals = {			
			-1f,1f,-1f,	
			-1f,-1f,-1f,	
			1f,-1f,-1f,	
			1f,1f,-1f,		
			
			-1f,1f,1f,	
			-1f,-1f,1f,	
			1f,-1f,1f,	
			1f,1f,1f,
			
			1f,1f,-1f,	
			1f,-1f,-1f,	
			1f,-1f,1f,	
			1f,1f,1f,
			
			-1f,1f,-1f,	
			-1f,-1f,-1f,	
			-1f,-1f,1f,	
			-1f,1f,1f,
			
			-1f,1f,1f,
			-1f,1f,-1f,
			1f,1f,-1f,
			1f,1f,1f,
			
			-1f,-1f,1f,
			-1f,-1f,-1f,
			1f,-1f,-1f,
			1f,-1f,1f
			
	};
	
	static float[] textureCoords = {
			
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0
	};
	
	static int[] indices = {
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22
	};
	
}

package example;

import java.awt.Color;

import org.joml.Matrix4f;

import engine.Camera;
import engine.Launcher;
import engine.Texture;
import engine.models.Cube;
import engine.shaders.BasicShader;

public class ExampleGame extends Launcher {

	//how you start a game
	public static void main(String[] args) {
		new ExampleGame().run();
	}

	public ExampleGame() {
		super("Example Game");
	}

	//Note that you MUST instantiate them (i.e. use the new keyword) in the initialize method! Your program will NOT
	//work if you do it here!
	Cube red, colorless;
	BasicShader shader;
	Texture grayBricks;
	
	@Override
	public void initialize() {
		red = new Cube(Color.red); //red cube
		colorless = new Cube(null); //cube with no color
		
		shader = new BasicShader(width, height); //you need to pass in the width and height so it isn't distorted
		//the basic shader supports basic lighting (the farther something is from you, the dimmer it is) and basic
		//textured/colored shapes
		
		grayBricks = new Texture("res/wall.png"); //a texture for gray bricks
		
		Camera.create(); //initialize camera
		
		//set background color
		setBackgroundColor(Color.cyan);
	}

	//render and update are usually called 60 times a second
	@Override
	public void render() {
		//this is how you can draw shapes: 
		//initialize the shader
		shader.enable();
		
		shader.setUniform3f("camPos", Camera.getPos());
		
		//set the camera view matrix
		//var is the keyword for type inference
		var viewMatrix = Camera.viewMatrix;
		shader.setUniformMat4f("viewMatrix", viewMatrix);
		
		//create a transformation matrix and input it into the shader
		var transformRed = new Matrix4f().translate(0, 5, 0);
		shader.setUniformMat4f("transformationMatrix", transformRed);
		
		//then render the model
		red.draw(shader);
		
		//repeat for the other objects
		var transformColorless = new Matrix4f().scale(2);
		shader.setUniformMat4f("transformationMatrix", transformColorless);
		
		colorless.draw(grayBricks, shader);
		
		shader.disable();
	}

	//render and update are usually called 60 times a second
	@Override
	public void update() {
		Camera.acceptInput(); //get input from the mouse and keyboard and feed it into the camera
		//by default, it supports WASD movement and SPACE and SHIFT to go up and down
		
		//pretty self-explanatory
		if(keyPressed(ESCAPE))
			stop();
	}

	//called at the end of your program so you can clean up your resources
	@Override
	public void destroy() {
		red.destroy();
		colorless.destroy();
		
		shader.destroy();
		
		grayBricks.destroy();
	}

}

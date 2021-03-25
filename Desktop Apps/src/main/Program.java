package main;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) throws Exception {
		Program.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//start the program
		//design the scenes
		//etc. 
		
		// AWT and Swing
		SystemTray tray = SystemTray.getSystemTray();

		Image image = Toolkit.getDefaultToolkit().createImage("fitminder icon.png");

		TrayIcon icon = new TrayIcon(image, "System Tray Icon");
		icon.setImageAutoSize(true);
		tray.add(icon);
		
		Button btn = new Button("do not press");
		btn.setOnAction(event -> {
			System.out.println("You pressed the button! How dare you! Here are reasons why you should not have pressed the button. first off this is not a horrer movie where you do something that says not to do. second it says don't press so don't press it ");
			
			icon.displayMessage("Warning", "You pressed the button! How dare you! Here are reasons why you should not have pressed the button. first off this is not a horrer movie where you do something that says not to do. second it says don't press so don't press it. Third you don't know what could happen, your computer could have blown up", MessageType.WARNING);
		});
		
		//stage
		//on the stage, you have a scene
		//in the scene, you have a pane
		//in a pane, you have controls (aka elements)
		
		StackPane pane = new StackPane();
		pane.getChildren().add(btn);
		
		Scene root = new Scene(pane, 500, 500);
		
		primaryStage.setScene(root);
		primaryStage.show();
	}

}

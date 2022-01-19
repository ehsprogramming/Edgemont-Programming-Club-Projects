package main;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

	public static int SIZE;
	
	StackPane root;
	FlashcardPane cardPane;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Quizzical Testlet");
		
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		SIZE = (int) (screenBounds.getHeight()) * 2/3;
		
		root = new StackPane();
		
		List<Flashcard> list = new ArrayList<>();
		list.add(new Flashcard("Term", "Definition"));
		list.add(new Flashcard("Term2", "Definition2"));
		
		cardPane = new FlashcardPane(list);
		
		root.getChildren().add(cardPane);
		
		var scene = new Scene(root, 500, 500);
		var camera = new PerspectiveCamera();
		scene.setCamera(camera);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		cardPane.requestFocus();
	}

	public static void main(String[] args) {
		Main.launch(args);
	}
	
}

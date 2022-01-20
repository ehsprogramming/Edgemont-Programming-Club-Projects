package main;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static int SIZE;
	public static Main main;
	
	StackPane root;
	VBox buttons;
	FlashcardPane cardPane;
	Parent makeset;
	Camera camera;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.main = this;
		
		primaryStage.setTitle("Quizzical Testlet");
		
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		SIZE = (int) (screenBounds.getHeight()) * 2/3;
		
		root = new StackPane();
		
		List<Flashcard> list = new ArrayList<>();
		list.add(new Flashcard("Term", "Definition"));
		list.add(new Flashcard("Term2", "Definition2"));
		
		cardPane = new FlashcardPane(list);
		cardPane.setTranslateY(400);
		
		makeset = FXMLLoader.load(getClass().getResource("/res/makeset.fxml"));
		makeset.setTranslateY(-400);
		
		buttons = new VBox();
		
		Button study = new Button("Study");
		Button create = new Button("Create");
		
		study.setOnAction(this::study);
		create.setOnAction(this::create);
		
		buttons.getChildren().addAll(study, create);
		
		root.getChildren().add(buttons);
		root.getChildren().add(makeset);
		root.getChildren().add(cardPane);
		
		var scene = new Scene(root, 600, 400);
		camera = new ParallelCamera();//new PerspectiveCamera();
		scene.setCamera(camera);
		primaryStage.setScene(scene);
		//primaryStage.setResizable(false);
		primaryStage.show();
		
		root.getChildren().removeAll(makeset, cardPane);
	}

	public void study(ActionEvent evt) {
		root.getChildren().addAll(cardPane);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), root);
		tt.setByY(-400);
		tt.setOnFinished(e -> {
			cardPane.requestFocus();
			root.getChildren().remove(buttons);
			root.getChildren().remove(makeset);
			});
		tt.play();
	}
	
	public void create(ActionEvent evt) {
		root.getChildren().addAll(makeset);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), root);
		tt.setByY(400);
		tt.setOnFinished(e -> {
			root.getChildren().remove(buttons);
			root.getChildren().remove(cardPane);
		});
		tt.play();
	}
	
	public void showMenu() {
		root.getChildren().addAll(buttons);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), root);
		tt.setToY(0);
		tt.setOnFinished(e -> {
			root.getChildren().remove(makeset);
			root.getChildren().remove(cardPane);
		});
		tt.play();
	}
	public static void main(String[] args) {
		Main.launch(args);
	}
	
}

package main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.panes.SelectPane;

public class Main extends Application {

	public static int SIZE;
	public static Main main;
	
	StackPane root;
	VBox buttons;
	FlashcardPane cardPane;
	SelectPane select;
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
		cardPane.setTranslateY(-800);
		
		makeset = FXMLLoader.load(getClass().getResource("/res/makeset.fxml"));
		makeset.setTranslateY(-400);
		
		buttons = new VBox();
		
		buttons.setSpacing(10);
		
		Button selectBtn = new Button("Study");
		Button create = new Button("Create");
		
		StyleUtil.style("mediumaquamarine", selectBtn, create);
		
		buttons.setAlignment(Pos.CENTER);
		
		selectBtn.setOnAction(this::select);
		create.setOnAction(this::create);
		
		buttons.getChildren().addAll(create, selectBtn);
		
		
		select = new SelectPane();
		//select.setTranslateX(-600);
		select.setTranslateY(-400);
		select.rescan();
		
		
		
		
		root.getChildren().add(buttons);
		root.getChildren().add(makeset);
		root.getChildren().add(cardPane);
		root.getChildren().add(select);
		
		var scene = new Scene(root, 600, 400);
		camera = new ParallelCamera();//new PerspectiveCamera();
		scene.setCamera(camera);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		root.getChildren().removeAll(makeset, cardPane, select);
	}
	
	public void select(ActionEvent evt) {
		root.getChildren().addAll(select);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), root);
		//tt.setToX(600);
		//tt.setToY(0);
		tt.setToY(400);
		tt.setOnFinished(e -> {
			select.requestFocus();
			root.getChildren().remove(buttons);
			root.getChildren().remove(makeset);
			root.getChildren().remove(cardPane);
			});
		tt.play();
	}

	public void study(File f) throws IOException {
		root.getChildren().addAll(cardPane);
		
		cardPane.setCards(FileUtil.read(f));
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), root);
		//tt.setByY(-400);
		//tt.setByX(-600);
		tt.setToY(800);
		tt.setOnFinished(e -> {
			root.getChildren().remove(buttons);
			root.getChildren().remove(makeset);
			root.getChildren().remove(select);
			
			cardPane.requestFocus();
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
			root.getChildren().remove(select);
		});
		tt.play();
	}
	
	public void showMenu() {
		root.getChildren().addAll(buttons);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), root);
		tt.setToY(0);
		tt.setToX(0);
		tt.setOnFinished(e -> {
			root.getChildren().remove(makeset);
			root.getChildren().remove(cardPane);
			root.getChildren().remove(select);
		});
		tt.play();
	}
	public static void main(String[] args) {
		Main.launch(args);
	}
	
}

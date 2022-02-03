package main.panes;

import java.io.File;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.FileUtil;
import main.Main;
import main.StyleUtil;

public class SelectPane extends StackPane {

	VBox buttons;
	
	public SelectPane() {
		buttons = new VBox();
		buttons.setAlignment(Pos.CENTER);
		Button backButton = new Button("Back");
		backButton.setTranslateX(250);
		backButton.setTranslateY(-175);
		backButton.setStyle("-fx-background-color: lavender;"
				+ "-fx-background-radius: 10;");
		backButton.setPadding(new Insets(10, 20, 10, 20));
		getChildren().add(buttons);
		getChildren().add(backButton);
		
		backButton.setOnAction(evt -> Main.main.showMenu());
	}
	
	public void rescan() {
		buttons.getChildren().clear();
		
		File[] list = FileUtil.savedSets();
		
		if(list.length == 0) {
			buttons.getChildren().add(StyleUtil.style(new Label("No study sets found")));
		}
		
		for(File f: list) {
			String full = f.getName();
			String noExt = full.substring(0, full.lastIndexOf('.'));
			var btn = new Button(noExt);
			
			btn.setOnAction(evt -> {
				try {
					Main.main.study(f);
				} catch (IOException e) {
					e.printStackTrace();
					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Couldn't load study set");
					alert.setHeaderText("Look, an Information Dialog");
					alert.setContentText("I have a great message for you! The study set failed to load");

					alert.showAndWait();
				}
			});
			
			StyleUtil.style("lightcoral", btn);
			
			buttons.setSpacing(10);
			
			buttons.getChildren().add(btn);
		}
	}
	
}

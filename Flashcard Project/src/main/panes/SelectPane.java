package main.panes;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.FileUtil;
import main.Main;

public class SelectPane extends VBox {

	public SelectPane() {
		
	}
	
	public void rescan() {
		getChildren().clear();
		
		File[] list = FileUtil.savedSets();
		
		for(File f: list) {
			var btn = new Button(f.getName());
			
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
			getChildren().add(btn);
		}
	}
	
}

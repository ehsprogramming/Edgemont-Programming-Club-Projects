package main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class StyleUtil {

	public static void style(String color, Button... n) {
		for(var btn : n) {
			btn.setStyle("-fx-background-color: " + color + ";"
					+ "-fx-background-radius: 10;");
			btn.setPadding(new Insets(10, 20, 10, 20));
		}
	}
	
}

package main.panes;

import java.io.File;
import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.FileUtil;

public class SelectPane extends VBox {

	public SelectPane() {
		File[] list = FileUtil.savedSets();
		
		for(File f: list) {
			getChildren().add(new Button(f.getName()));
		}
		
		System.out.println(Arrays.toString(list));
		
		//FileUtil.read()
	}
	
}

package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class CreatePane implements Initializable {

	@FXML
	AnchorPane card;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initialize");
		
	}
	
	public void save(ActionEvent evt) {
		Main.main.showMenu();
	}

}

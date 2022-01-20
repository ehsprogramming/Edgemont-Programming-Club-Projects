package main;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateCardUI {

	public TextField term;
	public TextArea def;
	public Button button;
	
	public CreateCardUI() {
		
	}
	
	public CreateCardUI(TextField term, TextArea def, Button button) {
		this.term = term;
		this.def = def;
		this.button = button;
	}
	
}

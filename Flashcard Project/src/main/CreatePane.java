package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreatePane implements Initializable {
	
	@FXML
	TextField title;
	
	@FXML
	AnchorPane scroll, card;
	
	@FXML
	Button addTerm;
	
	@FXML
	TextField termText;
	@FXML
	TextArea defText;
	@FXML
	Button delete1;
	
	List<CreateCardUI> cards;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cards = new ArrayList<>();
		
		var ccu = new CreateCardUI(termText, defText, delete1);
		
		cards.add(ccu);
		
		delete1.setOnAction(evt -> {
			scroll.getChildren().remove(card);
			cards.remove(ccu);
			AnchorPane.setTopAnchor(addTerm, AnchorPane.getTopAnchor(addTerm) - 89.6);
		});
		
		System.out.println("Initialize");
	}
	
	public void save(ActionEvent evt) {
		System.out.println(title.getText());
		
		Main.main.showMenu();
	}
	
	public void addTerm(ActionEvent evt) {
		
		AnchorPane newCard = new AnchorPane();
		AnchorPane.setLeftAnchor(newCard, 0.0);
		AnchorPane.setRightAnchor(newCard, 0.0);
		AnchorPane.setTopAnchor(newCard, cards.size() * 89.6);
		
		newCard.setPrefHeight(89.6);
		newCard.setPrefWidth(600.0);
		
		TextField term = new TextField();
		term.setPrefHeight(26.0);
		term.setPrefWidth(182.0);
		term.setPromptText("Term");
		
		AnchorPane.setBottomAnchor(term, 32.0);
		AnchorPane.setTopAnchor(term, 32.0);
		AnchorPane.setLeftAnchor(term, 29.0);
		
		TextArea def = new TextArea();
		def.setPrefHeight(38.0);
		def.setPrefWidth(252.0);
		def.setPromptText("Definition");
		
		AnchorPane.setBottomAnchor(def, 27.0);
		AnchorPane.setTopAnchor(def, 25.0);
		AnchorPane.setLeftAnchor(def, 244.0);
		
		Button delete = new Button();
		
		delete.setText("Delete");
		AnchorPane.setBottomAnchor(delete, 32.0);
		AnchorPane.setTopAnchor(delete, 30.0);
		AnchorPane.setLeftAnchor(delete, 520.0);
		
		newCard.getChildren().addAll(term, def, delete);
		
		scroll.getChildren().add(newCard);
		
		AnchorPane.setTopAnchor(addTerm, AnchorPane.getTopAnchor(addTerm) + 89.6);
		
		CreateCardUI ccu = new CreateCardUI();
		ccu.button = delete;
		ccu.def = def;
		ccu.term = term;
		
		cards.add(ccu);
		
		delete.setOnAction(e -> {
			scroll.getChildren().remove(newCard);
			cards.remove(ccu);
			AnchorPane.setTopAnchor(addTerm, AnchorPane.getTopAnchor(addTerm) - 89.6);
		});
	}

}

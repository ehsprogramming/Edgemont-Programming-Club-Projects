package main;

import java.net.InetAddress;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class MessagePane extends ScrollPane {

	public double lowestMessage = 10;
	public AnchorPane messages;
	public String name;
	public InetAddress address;
	
	public MessagePane(String name, InetAddress address) {
		this.name = name;
		this.address = address;
		
		messages = new AnchorPane();
		
		setContent(messages);
		
		setFitToHeight(true);
		setFitToWidth(true);
		
		AnchorPane.setBottomAnchor(this, 40.0);
		AnchorPane.setLeftAnchor(this, 134.67);
		AnchorPane.setTopAnchor(this, 0.0);
		AnchorPane.setRightAnchor(this, 0.0);
	}
	
	public void displaySentMessage(String message) {
		Label text = new Label(message);
		text.setStyle("-fx-background-color: coral; -fx-padding: 10px;");
		
		messages.getChildren().add(text);
		AnchorPane.setRightAnchor(text, 10.0);
		AnchorPane.setTopAnchor(text, lowestMessage);
		
		lowestMessage += 40;
	}
	
	public void displayReceivedMessage(String message) {
		Label text = new Label(message);
		text.setStyle("-fx-background-color: coral; -fx-padding: 10px;");
		
		messages.getChildren().add(text);
		AnchorPane.setLeftAnchor(text, 10.0);
		AnchorPane.setTopAnchor(text, lowestMessage);
		
		lowestMessage += 40;
	}
	
}

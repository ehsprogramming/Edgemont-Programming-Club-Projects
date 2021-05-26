package main;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Program extends Application implements Initializable {

	public static void main(String[] args) throws Exception {
		Program.launch(args);
	}
	
	Networker networker;
	InetAddress address;
	String name;
	
	MessagePane messages;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//start the program
		//design the scenes
		//etc. 
		
		// AWT and Swing
		/*SystemTray tray = SystemTray.getSystemTray();

		Image image = Toolkit.getDefaultToolkit().createImage("fitminder icon.png");

		TrayIcon icon = new TrayIcon(image, "System Tray Icon");
		icon.setImageAutoSize(true);
		tray.add(icon);*/
		
		Button btn = new Button("do not press");
		btn.setOnAction(Program::button);
		
		Button btn2 = new Button("Button");
		
		
		/* 
		 * event -> {
			System.out.println("You pressed the button! How dare you! Here are reasons why you should not have pressed the button. first off this is not a horrer movie where you do something that says not to do. second it says don't press so don't press it ");
			
			icon.displayMessage("Warning", "You pressed the button! How dare you! Here are reasons why you should not have pressed the button. first off this is not a horrer movie where you do something that says not to do. second it says don't press so don't press it. Third you don't know what could happen, your computer could have blown up", MessageType.WARNING);
		}*/
		
		//stage
		//on the stage, you have a scene
		//in the scene, you have a pane
		//in a pane, you have controls (aka elements)
	    
		/**FXML loader: Parent root = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
		//Dependency name: javafx-fxml, version 11
		
		//FXML Example
		AnchorPane stuff = (AnchorPane) FXMLLoader.load(getClass().getResource("fxml_example.fxml"));**/
		
		VBox pane = new VBox();
		pane.getChildren().add(btn);
		pane.getChildren().add(btn2);
		pane.getChildren().add(new Button("dslkjdslkjf"));
		
		pane.setSpacing(10);
		//top, right, bottom, left
		pane.setPadding(new Insets(20));
		VBox.setMargin(btn, new Insets(10));
		
		TextArea area = new TextArea();
		pane.getChildren().add(area);
				
		//yournode.setStyle("-fx-background-color: #" + enteredByUser);
		pane.setStyle("-fx-background-color: #FF8000");
		
		AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource("chattingscene.fxml"));
		
		Scene root = new Scene(anchorPane, 750, 500);
		
		primaryStage.setScene(root);
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		System.exit(0);
	}
	
	public class ChattingInterface implements NetworkInterface {

		@Override
		public void online(String nameE, InetAddress addressE) {
			Platform.runLater(() -> {
				Button button = new Button(nameE);
				button.setMaxWidth(Double.POSITIVE_INFINITY);
				
				button.setOnAction((e) -> {
					name = nameE;
					address = addressE;
					
					messages = new MessagePane(name, address);
					
					scenePane.getChildren().add(messages);
				});
				
				onlineFriends.getChildren().add(button);
			});
		}
		
		@Override
		public void process(String message) {
			Platform.runLater(() -> {
				messages.displayReceivedMessage(message);
			});
		}
		
	}
	
	@FXML
	AnchorPane scenePane;
	
	@FXML
	VBox onlineFriends;
	
	@FXML
	TextField message;
	
	@FXML
	void send(ActionEvent event) {
		String s = message.getText();
		
		Platform.runLater(() -> {
			messages.displaySentMessage(s);
		});
		
		networker.send(s, address);
	}
	
	public static void button(ActionEvent event) {
		System.out.println("Hello!");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			networker = new Networker("Kenny", new ChattingInterface());
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		networker.findUsers();
	}

}
 
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Quizzical Testlet");
		
		StackPane pane = new StackPane();
		
		var card = createCard("Term", "Definition");
		
		pane.getChildren().add(card);
		
		var scene = new Scene(pane, 500, 500);
		var camera = new PerspectiveCamera();
		scene.setCamera(camera);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public Node createCard(String term, String definition) {
		StackPane pane = new StackPane();
		
		Label text = new Label(term);
		Rectangle card = new Rectangle();
		card.setWidth(300);
		card.setHeight(150);
		card.setFill(Color.ORANGE);
		card.setArcWidth(300);
		card.setArcHeight(150);
		
		pane.getChildren().addAll(card, text);
		
		BooleanProperty front = new SimpleBooleanProperty(true);
		
		RotateTransition flip1 = new RotateTransition(Duration.millis(500), pane);
		flip1.setAxis(Rotate.X_AXIS);
		flip1.setByAngle(90);
		flip1.setInterpolator(Interpolator.LINEAR);
		
		TranslateTransition tt = new TranslateTransition();
		tt.setDuration(Duration.ZERO);
		tt.setOnFinished(evt -> front.setValue(!front.get()));
		
		RotateTransition flip2 = new RotateTransition(Duration.millis(500), pane);
		flip2.setAxis(Rotate.X_AXIS);
		flip2.setByAngle(90);
		flip2.setInterpolator(Interpolator.LINEAR);
		
		SequentialTransition flip = new SequentialTransition(flip1, tt, flip2);
		
		text.textProperty().bind(Bindings.when(front).then(term).otherwise(definition));
		pane.scaleYProperty().bind(Bindings.when(front).then(1).otherwise(-1));
		
		flip.setAutoReverse(true);
		flip.setCycleCount(Transition.INDEFINITE);
		flip.play();
		
		return pane;
	}

	public static void main(String[] args) {
		Main.launch(args);
	}
	
}

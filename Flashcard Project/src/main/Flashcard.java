package main;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Flashcard extends StackPane {

	public String term, definition;
	SequentialTransition flip;
	public BooleanProperty front, inProgress;
	
	public Flashcard(String term, String definition) {
		this.term = term;
		this.definition = definition;
		
		Label text = new Label(term);
		Rectangle card = new Rectangle();
		card.setWidth(300);
		card.setHeight(150);
		card.setFill(Color.ORANGE);
		card.setArcWidth(50);
		card.setArcHeight(50);
		
		getChildren().addAll(card, text);
		
		front = new SimpleBooleanProperty(true);
		inProgress = new SimpleBooleanProperty(false);
		
		RotateTransition flip1 = new RotateTransition(Duration.millis(125), this);
		flip1.setAxis(Rotate.X_AXIS);
		flip1.setFromAngle(0);
		flip1.setToAngle(90);
		//flip1.setInterpolator(Interpolator.LINEAR);
		flip1.setInterpolator(Interpolator.EASE_IN);
		
		TranslateTransition tt = new TranslateTransition();
		tt.setDuration(Duration.ZERO);
		tt.setOnFinished(evt -> front.setValue(!front.get()));
		
		RotateTransition flip2 = new RotateTransition(Duration.millis(125), this);
		flip2.setAxis(Rotate.X_AXIS);
		flip2.setFromAngle(90);
		flip2.setToAngle(180);
		//flip2.setInterpolator(Interpolator.LINEAR);
		flip2.setInterpolator(Interpolator.EASE_OUT);
		
		flip = new SequentialTransition(flip1, tt, flip2);
		flip.setRate(-1);
		
		text.textProperty().bind(Bindings.when(front).then(term).otherwise(definition));
		scaleYProperty().bind(Bindings.when(front).then(1).otherwise(-1));
	}
	
	public void flip() {
		if(inProgress.get())
			return;
		
		inProgress.set(true);
		flip.setOnFinished(evt -> inProgress.set(false));
		flip.setRate(-1 * flip.getRate());
		flip.play();
	}
	
}

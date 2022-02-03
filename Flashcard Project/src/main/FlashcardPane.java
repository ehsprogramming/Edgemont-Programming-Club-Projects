package main;

import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.data.FlashcardData;
import main.data.StudySet;

public class FlashcardPane extends StackPane implements EventHandler<KeyEvent> {

	public List<Flashcard> cards;
	public Flashcard current;
	public int index = 0;
	public TranslateTransition goLeft, goRight;
	public BooleanProperty anim;
	public StackPane cardsPane;
	
	public FlashcardPane(List<Flashcard> cards) {
		this.cards = cards;
		current = cards.get(0);
		
		cardsPane = new StackPane();
		
		for(int i = 0; i < cards.size(); i++) {
			cardsPane.getChildren().add(cards.get(i));
			cards.get(i).setTranslateX(500 * i);
		}
		
		setOnKeyReleased(this);
		setOnMouseReleased(this::mouse);
		
		goLeft = new TranslateTransition(Duration.millis(150), cardsPane);
		goLeft.setByX(500);
		//goLeft.setInterpolator(Interpolator.LINEAR);
		goLeft.setInterpolator(Interpolator.EASE_BOTH);
		
		goRight = new TranslateTransition(Duration.millis(150), cardsPane);
		goRight.setByX(-500);
		goRight.setInterpolator(Interpolator.EASE_BOTH);
		//goRight.setInterpolator(Interpolator.LINEAR);
		
		goRight.setOnFinished(evt -> anim.set(false));
		goLeft.setOnFinished(evt -> anim.set(false));
		
		anim = new SimpleBooleanProperty(false);
		
		cardsPane.requestFocus();
		cardsPane.requestLayout();
		
		Button backButton = new Button("Back");
		backButton.setTranslateX(-250);
		backButton.setTranslateY(-175);
		
		backButton.setStyle("-fx-background-color: lavender;"
				+ "-fx-background-radius: 10;");
		backButton.setPadding(new Insets(10, 20, 10, 20));
		
		backButton.setOnAction(evt -> Main.main.select(evt));
		
		getChildren().add(cardsPane);
		getChildren().add(backButton);
	}
	
	public void clearCards() {
		System.out.println("Clear cards");
		
		index = 0;
		
		for(Flashcard f: cards) {
			cardsPane.getChildren().remove(f);
		}
		
		cards.clear();
		current = null;
		
		cardsPane.setTranslateX(0);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(0), cardsPane);
		
		tt.setToX(0);
		tt.setToY(0);
		
		tt.play();
	}
	
	public void addFlashcard(Flashcard f) {
		cardsPane.getChildren().add(f);
		f.setTranslateX(500 * cards.size());
		cards.add(f);
		
		if(current == null) {
			current = f;
		}
	}
	
	public void mouse(MouseEvent event) {
		current.flip();
	}

	@Override
	public void handle(KeyEvent event) {
		var code = event.getCode();
		
		switch(code) {
		case SPACE:
		case UP:
		case DOWN:
			if(current != null) current.flip();
			break;
		case LEFT:
			if(index - 1 >= 0) {
				if(anim.get())
					return;
				
				anim.set(true);
				index--;
				current = cards.get(index);
				goLeft.play();
			}
			break;
		case RIGHT:
			if(index + 1 < cards.size()) {
				if(anim.get())
					return;
				
				anim.set(true);
				index++;
				current = cards.get(index);
				goRight.play();
			}
			break;
		default:
			break;
		}
	}
	
	public void setCards(StudySet read) {
		clearCards();
		
		for(FlashcardData fd : read.data) {
			addFlashcard(new Flashcard(fd.term, fd.definition));
		}
	}
	
}

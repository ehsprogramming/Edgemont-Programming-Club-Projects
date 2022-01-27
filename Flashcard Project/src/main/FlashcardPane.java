package main;

import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
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
	
	public FlashcardPane(List<Flashcard> cards) {
		this.cards = cards;
		current = cards.get(0);
		
		for(int i = 0; i < cards.size(); i++) {
			getChildren().add(cards.get(i));
			cards.get(i).setTranslateX(500 * i);
		}
		
		setOnKeyReleased(this);
		setOnMouseReleased(this::mouse);
		
		goLeft = new TranslateTransition(Duration.millis(150), this);
		goLeft.setByX(500);
		//goLeft.setInterpolator(Interpolator.LINEAR);
		goLeft.setInterpolator(Interpolator.EASE_BOTH);
		
		goRight = new TranslateTransition(Duration.millis(150), this);
		goRight.setByX(-500);
		goRight.setInterpolator(Interpolator.EASE_BOTH);
		//goRight.setInterpolator(Interpolator.LINEAR);
		
		goRight.setOnFinished(evt -> anim.set(false));
		goLeft.setOnFinished(evt -> anim.set(false));
		
		anim = new SimpleBooleanProperty(false);
	}
	
	public void clearCards() {
		for(Flashcard f: cards) {
			getChildren().remove(f);
		}
		
		cards.clear();
		current = null;
	}
	
	public void addFlashcard(Flashcard f) {
		getChildren().add(f);
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
		
		//TODO: title
		
		for(FlashcardData fd : read.data) {
			addFlashcard(new Flashcard(fd.term, fd.definition));
		}
	}
	
}

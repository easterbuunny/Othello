package handlers;

import app.Othello;
import javafx.scene.input.MouseEvent;
import views.MySquarePane;

public class ClickHandlerPlayer extends Handler {
	private Othello game ;
	public ClickHandlerPlayer(Othello game) {
		this.game = game;
	}

	@Override
	public void handle(MouseEvent event) {
		MySquarePane tmp = (MySquarePane) event.getSource();
		game.play(tmp.getIndiceCase());
	}
	
}

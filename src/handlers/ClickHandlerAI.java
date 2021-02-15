package handlers;

import app.Othello;
import javafx.scene.input.MouseEvent;
import models.AIPlayer;
import models.HumanPlayer;
import views.MySquarePane;

public class ClickHandlerAI extends Handler {
	private HumanPlayer jH;
	private AIPlayer jIA;
	private Othello game;

	public ClickHandlerAI(Othello game, HumanPlayer jH, AIPlayer jIA) {
		this.game = game;
		this.jH = jH;
		this.jIA = jIA;
	}

	@Override
	public void handle(MouseEvent event) {
		MySquarePane square = (MySquarePane) event.getSource();
		if (jH.equals(game.getTurn())) {
			game.play(square.getIndiceCase());
		}
		while (jIA.equals(game.getTurn()) && !game.endGame())
			game.play(jIA.playMove(game));

		if (game.endGame()) {
			System.out.println("Partie terminee");
			System.exit(0);
		}

	}

}

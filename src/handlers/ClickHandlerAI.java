package handlers;

import app.Othello;
import javafx.scene.input.MouseEvent;
import models.AIPlayer;
import models.HumanPlayer;
import views.MySquarePane;

public class ClickHandlerAI extends Handler {
	private HumanPlayer humanPlayer;
	private AIPlayer AIPlayer;
	private Othello game;

	public ClickHandlerAI(Othello game, HumanPlayer humanPlayer, AIPlayer AIPlayer) {
		this.game = game;
		this.humanPlayer = humanPlayer;
		this.AIPlayer = AIPlayer;
	}

	@Override
	public void handle(MouseEvent event) {
		MySquarePane square = (MySquarePane) event.getSource();
		if (humanPlayer.equals(game.getTurn())) {
			game.play(square.getIndiceCase());
		}
		while (AIPlayer.equals(game.getTurn()) && !game.endGame())
			game.play(AIPlayer.playMove(game));

		if (game.endGame()) {
			System.out.println("Partie terminee");
			System.exit(0);
		}

	}

}

package handlers;

import app.Othello;
import ia.AIPlayer;
import javafx.scene.input.MouseEvent;

public class AIvsAIhandler extends Handler {
	private Othello game;
	private AIPlayer p1;
	private AIPlayer p2;

	public AIvsAIhandler(Othello game) {
		this.game = game;
		p1 = (AIPlayer) game.getBlackPlayer();
		p2 = (AIPlayer) game.getWhitePlayer();
	}

	public void handle(MouseEvent event) {
		if (p1.equals(game.getTurn()))
			game.play(p1.playMove(game));
		if (p2.equals(game.getTurn()))
			game.play(p2.playMove(game));
	}

}

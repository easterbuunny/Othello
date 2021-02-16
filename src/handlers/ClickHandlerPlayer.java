package handlers;

import app.Othello;
import javafx.scene.input.MouseEvent;
import models.TypePiece;
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
		if(game.endGame()) {
			System.out.println("Partie terminee");
			System.out.println("Score J1 :" + game.getBoard().getNbPiece(TypePiece.BLACK));
			System.out.println("Score J2 :" + game.getBoard().getNbPiece(TypePiece.WHITE));
			System.exit(0);
		}
	}
	
}

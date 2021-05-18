package views;

import app.Othello;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class MyEndGamePane extends BorderPane {
	/**
	 * Methode affichant un message qui determine le gagnant de la partie.
	 * @param game le jeu
	 */
	public MyEndGamePane(Othello game) {
		Text winner = new Text("Le gagnant est : " + game.getWinner().toString() + " avec " + game.getBoard().getNbPiece(game.getWinner().getTypePiece()) + " pieces");
		this.setCenter(winner);
	}
}

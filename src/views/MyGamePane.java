package views;


import app.Main;
import app.Othello;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.TypePiece;

public class MyGamePane extends BorderPane {
	private Text tour;
	private Text score;
	private Othello game;

	public MyGamePane(Othello game) {
		this.game = game;
		tour = new Text(game.getBlackPlayer().toString());
		score = new Text("Noir : " + game.getBoard().getNbPiece(TypePiece.BLACK) + " Blanc : "
				+ game.getBoard().getNbPiece(TypePiece.WHITE));
		this.setCenter(game.getBoard().getBoardPane());
		this.setRight(tour);
		this.setTop(score);
		this.setOnMouseClicked(event -> {
			updateTurn();
			updateScore();
		});
		Button exitGame = new Button("Quitter");
		exitGame.setOnMouseClicked(event -> {
			Main.setPaneRoot(new MyStartingPane());
		});
		this.setBottom(exitGame);

	}

	public void updateScore() {
		score.setText("Noir : " + game.getBoard().getNbPiece(TypePiece.BLACK) + " Blanc : "
				+ game.getBoard().getNbPiece(TypePiece.WHITE));
	}

	public void updateTurn() {
		if(game.getBoard().endGame()) {
			Text winnerText = new Text();
			winnerText.setText("Le gagnant est : " + game.getWinner().toString() + "(" + game.getWinner().getTypePiece() + ")" + " avec " + game.getBoard().getNbPiece(game.getWinner().getTypePiece()) + " pieces");
			winnerText.setFont(new Font(30));
			winnerText.setX(150);
			winnerText.setY(150);
			winnerText.setStyle("-fx-text-fill: red");
			this.setTop(winnerText);
		}
		else
			tour.setText("Au tour de " + game.getTurn().toString());
	}

}

package views;


import app.Main;
import app.Othello;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.TypePiece;

public class MyGamePane extends BorderPane {
	//Attributs
	private Text tour;
	private Text score;
	private Othello game;

	public MyGamePane(Othello game) {
		this.game = game;
		tour = new Text(game.getBlackPlayer().toString());
		//affiche nombre de piece de chacun des joueurs
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
		//Si le joueur appuie sur le bouton quitter, il sera renvoye vers la page d'accueil
		exitGame.setOnMouseClicked(event -> {
			Main.setPaneRoot(new MyStartingPane());
		});
		//Affiche le bouton quitter en dessous
		this.setBottom(exitGame);
	}
	
	
	/**
	 * Mets a jour le nouveau score de la partie (Methode appelee lors d'un coup).
	 */
	public void updateScore() {
		//Editer le contenu du texte
		score.setText("Noir : " + game.getBoard().getNbPiece(TypePiece.BLACK) + " Blanc : "
				+ game.getBoard().getNbPiece(TypePiece.WHITE));
	}

	/**
	 * Si le jeu est terminer alors afficher le gagnant (celui qui a le score le plus eleve)
	 * Sinon affiche le joueur a qui c'est le tour de jouer
	 */
	public void updateTurn() {
		if(game.getBoard().endGame()) {
			Text winnerText = new Text();
			//Editer le contenu du texte
			winnerText.setText("Le gagnant est : " + game.getWinner().toString() + "(" + game.getWinner().getTypePiece() + ")" + " avec " + game.getBoard().getNbPiece(game.getWinner().getTypePiece()) + " pieces");
			//Taille de la police
			winnerText.setFont(new Font(30));
			//L'emplacement du texte
			winnerText.setX(150);
			winnerText.setY(150);
			//Style de la police et sa couleur
			winnerText.setStyle("-fx-text-fill: red");
			//Affiche le text au dessu
			this.setTop(winnerText);
		}
		else
			//Editer le contenu du texte
			tour.setText("Au tour de " + game.getTurn().toString());
	}

}

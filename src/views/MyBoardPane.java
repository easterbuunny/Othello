package views;

import java.util.List;

import app.Othello;
import handlers.Handler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import models.TypePiece;

public class MyBoardPane extends GridPane {
	//Attributs
	private final int gridSize = 8; //Taille de la grille
	private MySquarePane squarePane[]; // Tableau de case

	//Constructeur
	public MyBoardPane(Handler h) {
		//Nombre de case total gridSize x gridSize (8x8 = 64)
		squarePane = new MySquarePane[64];
		//Parcours les lignes
		for (int row = 0; row < gridSize; row++) {
			//Parcour les colonnes
			for (int column = 0; column < gridSize; column++) {
				//Indice de la case
				MySquarePane cp = new MySquarePane(gridSize * row + column);
				this.add(cp, row, column);
				squarePane[gridSize * row + column] = cp;
				cp.setOnMouseClicked(event -> h.handle(event));
			}
		}
		this.setAlignment(Pos.CENTER);
		this.setVgap(1);
		this.setHgap(1);
		init();
	}

	/**
	 * (Getter)
	 * La methode permet de recuperer une case par rapport a son indice de ligne et de colonne
	 * @param row, indice ligne
	 * @param column, indice colonne
	 * @return renvoie la case correspondant a ligne et la colonne indique
	 */
	public MySquarePane getCaseByRowColomnIndex(int row, int column) {
		return (MySquarePane) this.getChildren().get(gridSize * row + column);
	}
	
	/**
	 * La methode initialise le plateau en y mettant la configuration des pieces de debut de partie
	 */
	public void init() {
		this.getCaseByRowColomnIndex(3, 3).getChildren().add(new MyPiecePane(TypePiece.WHITE));
		this.getCaseByRowColomnIndex(4, 4).getChildren().add(new MyPiecePane(TypePiece.WHITE));
		this.getCaseByRowColomnIndex(4, 3).getChildren().add(new MyPiecePane(TypePiece.BLACK));
		this.getCaseByRowColomnIndex(3, 4).getChildren().add(new MyPiecePane(TypePiece.BLACK));
	}


	/**
	 * Permet d'inserer une piece dans une case.
	 * @param tp, type de piece a inserer
	 * @param indiceCase, indice de la case
	 */
	public void setCase(TypePiece tp, int indiceCase) {
		squarePane[indiceCase].setCasePane(tp);
	}

	/**
	 * Permet de mettre en couleur les coups valides pour un joueur.
	 * @param game
	 * @param emphasizeValidSquares : indique si la case doit etre mise en evidence via une couleur (true) ou revenir a sa couleur de base (false)
	 */
	public void setValidSquares(Othello game, boolean emphasizeValidSquares) {
		List<Integer> validMoves = game.getBoard().getValidMoves(game.getTurn().getTypePiece());
		for (Integer validMove : validMoves) {
			squarePane[validMove].setColorSquare(emphasizeValidSquares ? Color.rgb(250,128, 114) : Color.GREEN);
		}
	}

}

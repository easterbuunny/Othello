package models;

import java.util.ArrayList;
import java.util.List;
import views.MyBoardPane;

public class Board {
	private Square squares[];
	private MyBoardPane boardPane;

	public Board() {
		this.squares = new Square[64];
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new Square();
		}
		initBoard();
	}

	private void initBoard() {
		setSquare(TypePiece.BLACK, 4, 3);
		setSquare(TypePiece.BLACK, 3, 4);
		setSquare(TypePiece.WHITE, 3, 3);
		setSquare(TypePiece.WHITE, 4, 4);
	}

	/**
	 * Permet de recuperer l'ensemble des indices des cases contenants un type de
	 * piece passe en parametre
	 * 
	 * @param tp Type de Piece (BLACK ou WHITE)
	 * @return Retourne une liste d'indice de case contenant le type de piece
	 */
	public List<Integer> getPiecesIndex(TypePiece tp) {
		List<Integer> squaresIndex = new ArrayList<Integer>();
		for (int i = 0; i < squares.length; i++) {
			if (squares[i].getPiece().getTypePiece().equals(tp))
				squaresIndex.add(i);
		}
		return squaresIndex;
	}

	public MyBoardPane getBoardPane() {
		return boardPane;
	}

	public void setBoardPane(MyBoardPane p) {
		this.boardPane = p;
	}

	public Square getSquare(int row, int column) {
		return squares[row * 8 + column];
	}

	public Square getSquare(int squareIndex) {
		return squares[squareIndex];
	}

	public void setSquare(TypePiece tp, int row, int column) {
		getSquare(row, column).setPiece(tp);
	}

	public void setSquare(TypePiece tp, int squareIndex) {
		squares[squareIndex].setPiece(tp);
	}

	/**
	 * Methode permettant de verifier si un plateau est plein ou non.
	 * 
	 * @return True si le plateau est plein , false sinon
	 */
	public boolean isFull() {
		for (int i = 0; i < 64; i++)
			if (squares[i].isEmpty())
				return false;
		return true;
	}

	/**
	 * Methode qui permet de recuperer le nombre de piece d'un type particulier sur
	 * le plateau
	 * 
	 * @param tp : Type de piece
	 * @return Le nombre de piece du type passe en parametre.
	 */

	public int getNbPiece(TypePiece tp) {
		int compteur = 0;
		for (int i = 0; i < 64; i++)
			if (squares[i].getPiece().getTypePiece().equals(tp))
				compteur++;
		return compteur;
	}
}

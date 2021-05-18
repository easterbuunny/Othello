package models;

import java.util.ArrayList;
import java.util.List;
import views.MyBoardPane;

public class Board{

	private Square squares[];
	private MyBoardPane boardPane;

	public Board() {
		this.squares = new Square[64];
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new Square();
		}
		initBoard();
	}

	public Board(Square[] squares) {
		this.squares = squares;
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

	public Square[] getSquares() {
		return squares;
	}
	
	/**
	 * Ajoute le coup uniquement dans le model representant le plateau
	 * @param currentTypePiece Le type de piece placee
	 * @param indexMove L'indice du coup
	 */
	public void addMoveToBoard(TypePiece currentTypePiece, int indexMove) {
		List<Integer> capturedPiecesIndex = new ArrayList<Integer>();
		capturedPiecesIndex.addAll(CaptureEvaluator.capture(this, currentTypePiece, indexMove));
		setSquare(currentTypePiece, indexMove);
		for (Integer piece : capturedPiecesIndex)
			setSquare(currentTypePiece, piece);

	}
	/**
	 * Ajoute le coup uniquement sur l'othellier (et non pas sur le model representant le eplateau).
	 * @param currentTypePiece Le type de piece placee
	 * @param indexMove L'indice du coup
	 */
	public void addMoveToBoardPane(TypePiece currentTypePiece, int indexMove) {
		List<Integer> capturedPiecesIndex = new ArrayList<Integer>();
		capturedPiecesIndex.addAll(CaptureEvaluator.capture(this, currentTypePiece, indexMove));
		getBoardPane().setCase(currentTypePiece, indexMove);
		for (Integer piece : capturedPiecesIndex)
			getBoardPane().setCase(currentTypePiece, piece);

	}
	/**
	 * Permet de recuperer le nouveau plateau apres un coup.
	 * @param tp Type de piece jouant le coup
	 * @param indexMove l'indice du coup
	 * @return Un nouveau plateau apres le coup jouee
	 */
	public Board getBoardAfterMove(TypePiece tp, int indexMove) {
		Square[] newSquares = new Square[64];
		for (int i = 0; i < 64; i++) {
			newSquares[i] = new Square();
			if(squares[i].getPiece() != null)
				newSquares[i].setPiece(squares[i].getPiece().getTypePiece());
		}
		Board newBoard = new Board(newSquares);
		newBoard.addMoveToBoard(tp, indexMove);
		return newBoard;
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
	 * Determine si un coup est valide pour un joueur
	 * 
	 * @param player    le joueur effectuant le coup
	 * @param indexMove l'indice de la case sur laquelle la piece est jouee
	 * @return true si le coup est valide pour le joueur , false sinon
	 */
	public boolean validMove(Player player, int indexMove) {
		return getValidMoves(player.getTypePiece()).contains(indexMove);
	}

	/**
	 * Recupere l'ensemble des coups valides pour un Joueur
	 * 
	 * @param joueur
	 * @return Liste d'integer representant l'indice des cases des coups valides.
	 */
	public List<Integer> getValidMoves(TypePiece tp) {
		List<Integer> validMove = new ArrayList<Integer>();

		for (int i = 0; i < 64; i++) {
			if (getSquare(i).isEmpty() && CaptureEvaluator.capture(this, tp, i).size() > 0) {
				validMove.add(i);
			}
		}
		return validMove;
	}

	/**
	 * Determine si la partie est terminee
	 * 
	 * @return true si la partie est terminee false sinon
	 */
	public boolean endGame() {
		return getValidMoves(TypePiece.BLACK).size() == 0 && getValidMoves(TypePiece.WHITE).size() == 0;
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

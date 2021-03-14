package app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import handlers.ClickHandlerAI;
import handlers.ClickHandlerPlayer;
import models.AIPlayer;
import models.Board;
import models.CaptureEvaluator;
import models.HumanPlayer;
import models.Player;
import models.TypePiece;
import views.MyBoardPane;

public class Othello implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2695623659071153026L;
	/**
	 * 
	 */
	private Player blackPlayer;
	private Player whitePlayer;
	private Board board;
	// Determine a qui c'est le tour de jouer
	private boolean blacksPlay;

	public Othello(Player blackPlayer, Player whitePlayer) {
		this.blackPlayer = blackPlayer;
		this.whitePlayer = whitePlayer;
		blacksPlay = true;
		this.board = new Board();
		init();
	}

	private void init() {

		if (blackPlayer instanceof HumanPlayer && whitePlayer instanceof HumanPlayer)
			board.setBoardPane(new MyBoardPane(new ClickHandlerPlayer(this)));

		else if (blackPlayer instanceof AIPlayer && whitePlayer instanceof HumanPlayer) {
			board.setBoardPane(
					new MyBoardPane(new ClickHandlerAI(this, (HumanPlayer) whitePlayer, (AIPlayer) blackPlayer)));
			this.play(((AIPlayer) blackPlayer).playMove(this));
		} else if (blackPlayer instanceof HumanPlayer && whitePlayer instanceof AIPlayer) {
			board.setBoardPane(
					new MyBoardPane(new ClickHandlerAI(this, (HumanPlayer) blackPlayer, (AIPlayer) whitePlayer)));
		}
		this.getBoard().getBoardPane().setValidSquares(this, true);

	}

	/**
	 * This method makes a "deep clone" of any Java object it is given.
	 * 
	 * @author Alvin Alexander, https://alvinalexander.com
	 */
	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setBlacksPlay(boolean blacksPlay) {
		this.blacksPlay = blacksPlay;
	}

	public Player getWinner() {
		int nbBlackPieces = board.getNbPiece(blackPlayer.getTypePiece());
		int nbWhitePieces = board.getNbPiece(whitePlayer.getTypePiece());
		if (endGame()) {
			if (nbBlackPieces > nbWhitePieces)
				return blackPlayer;
			if (nbWhitePieces > nbBlackPieces)
				return whitePlayer;
		}
		return null;
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
			if (board.getSquare(i).isEmpty() && CaptureEvaluator.capture(board, tp, i).size() > 0) {
				validMove.add(i);
			}
		}
		return validMove;
	}

	/**
	 * Modifie la valeur de l'attribut blacksPlay permettant de determinee a qui
	 * c'est le tour de jouer
	 */
	public void setTurn() {
		// Si les noirs viennent de jouer et que les blancs ont un coup valide alors
		// c'est au blanc de jouer
		if (blacksPlay && getValidMoves(whitePlayer.getTypePiece()).size() > 0) {
			blacksPlay = false;
		} else if (!blacksPlay && getValidMoves(blackPlayer.getTypePiece()).size() > 0) {
			blacksPlay = true;
		}
	}

	/**
	 * Recupere le joueur a qui c'est le tour de jouer , fonctionne en tamdem avec
	 * setTurn
	 * 
	 * @return Le joueur a qui c'est le tour de jouer
	 */
	public Player getTurn() {
		return blacksPlay ? blackPlayer : whitePlayer;
	}

	/**
	 * Ajoute un coup et ses repercussions (les captures) sur le plateau graphique
	 * et le plateau logique
	 * 
	 * @param currentPlayer Le joeur qui joue le coup
	 * @param indexMove     L'indice de la case du coup jouee
	 */
	public void addMove(Player currentPlayer, int indexMove) {
		board.addMoveToBoardPane(currentPlayer, indexMove);
		board.addMoveToBoard(currentPlayer, indexMove);
	}

	/**
	 * Determine si la partie est terminee
	 * 
	 * @return true si la partie est terminee false sinon
	 */
	public boolean endGame() {
		return board.isFull() || board.getNbPiece(TypePiece.BLACK) == 0 || board.getNbPiece(TypePiece.WHITE) == 0
				|| (getValidMoves(whitePlayer.getTypePiece()).size() == 0
						&& getValidMoves(blackPlayer.getTypePiece()).size() == 0);
	}

	public void play(int indiceCoup) {
		if (!endGame()) {
			Player jCourant = getTurn();
			Integer coupJouee = indiceCoup;
			if (validMove(jCourant, coupJouee)) {
				this.getBoard().getBoardPane().setValidSquares(this, false);
				addMove(jCourant, coupJouee);
				setTurn();
				this.getBoard().getBoardPane().setValidSquares(this, true);
			}
		}
	}

	public void playAI(int indiceCoup) {
		if (!endGame()) {
			Player jCourant = getTurn();
			Integer coupJouee = indiceCoup;
			if (validMove(jCourant, coupJouee)) {
				board.addMoveToBoard(jCourant, indiceCoup);
				setTurn();
			}
		}
	}

}

package app;

import handlers.ClickHandlerAI;
import handlers.ClickHandlerPlayer;
import models.AIPlayer;
import models.Board;
import models.HumanPlayer;
import models.Player;
import views.MyBoardPane;

public class Othello {

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
		if (board.endGame()) {
			if (nbBlackPieces > nbWhitePieces)
				return blackPlayer;
			if (nbWhitePieces > nbBlackPieces)
				return whitePlayer;
		}
		return null;
	}

	/**
	 * Modifie la valeur de l'attribut blacksPlay permettant de determinee a qui
	 * c'est le tour de jouer
	 */
	public void setTurn() {
		// Si les noirs viennent de jouer et que les blancs ont un coup valide alors
		// c'est au blanc de jouer
		if (blacksPlay && board.getValidMoves(whitePlayer.getTypePiece()).size() > 0) {
			blacksPlay = false;
		} else if (!blacksPlay && board.getValidMoves(blackPlayer.getTypePiece()).size() > 0) {
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
		board.addMoveToBoardPane(currentPlayer.getTypePiece(), indexMove);
		board.addMoveToBoard(currentPlayer.getTypePiece(), indexMove);
	}

	public void play(int indiceCoup) {
		if (!board.endGame()) {
			Player jCourant = getTurn();
			Integer coupJouee = indiceCoup;
			if (board.validMove(jCourant, coupJouee)) {
				this.getBoard().getBoardPane().setValidSquares(this, false);
				addMove(jCourant, coupJouee);
				setTurn();
				this.getBoard().getBoardPane().setValidSquares(this, true);
			}
		}
	}

}

package app;

import java.util.ArrayList;
import java.util.List;

import handlers.ClickHandlerAI;
import handlers.ClickHandlerPlayer;
import models.CaptureEvaluator;
import models.Player;
import models.HumanPlayer;
import models.AIPlayer;
import models.Board;
import models.TypePiece;
import views.MyBoardPane;

public class Othello {
	private Player blackPlayer;
	private Player whitePlayer;
	private Board board;
	private boolean blacksPlay;

	public Othello(Player blackPlayer, Player whitePlayer) {
		this.blackPlayer = blackPlayer;
		this.whitePlayer = whitePlayer;
		blacksPlay = true;
		this.board = new Board();
		if(blackPlayer instanceof HumanPlayer && whitePlayer instanceof HumanPlayer)
			board.setBoardPane( new MyBoardPane(new ClickHandlerPlayer(this)));
		else if(blackPlayer instanceof AIPlayer && whitePlayer instanceof HumanPlayer) {
			((AIPlayer) blackPlayer).playMove(this);
			board.setBoardPane( new MyBoardPane(new ClickHandlerAI(this, (HumanPlayer) whitePlayer,(AIPlayer) blackPlayer)));
		}
		else if(blackPlayer instanceof HumanPlayer && whitePlayer instanceof AIPlayer)
			board.setBoardPane( new MyBoardPane(new ClickHandlerAI(this, (HumanPlayer) blackPlayer,(AIPlayer) whitePlayer)));
	}

	public boolean validMove(Player player, int indexMove) {
		return getValidMove(player).contains(indexMove);
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public Board getBoard() {
		return board;
	}

	/**
	 * Recupere l'ensemble des coups valides pour un Joueur
	 * 
	 * @param joueur 
	 * @return Liste d'integer representant l'indice des cases des coups valides.
	 */
	public List<Integer> getValidMove(Player player) {
		List<Integer> validMove = new ArrayList<Integer>();

		for (int i = 0; i < 64; i++) {
			if (board.getSquare(i).isEmpty()
					&& CaptureEvaluator.capture(board, player.getTypePiece(), i).size() > 0) {
				validMove.add(i);
			}
		}
		return validMove;
	}

	public void setTurn() {
		if(blacksPlay && getValidMove(whitePlayer).size() > 0) {
			blacksPlay  = false;
		}
		else if(!blacksPlay && getValidMove(blackPlayer).size() > 0) {
			blacksPlay = true;
		}
	}

	public Player getTurn() {
		return blacksPlay ? blackPlayer : whitePlayer;
	}
	public void addMove(Player currentPlayer, int indexMove) {
		List<Integer> pieceCapturee = new ArrayList<Integer>();
		pieceCapturee.addAll(CaptureEvaluator.capture(board, currentPlayer.getTypePiece(), indexMove));
		board.setSquare(currentPlayer.getTypePiece(), indexMove);
		board.getBoardPane().setCase(currentPlayer.getTypePiece(), indexMove);
		for (Integer piece : pieceCapturee) {
			board.setSquare(currentPlayer.getTypePiece(), piece);
			board.getBoardPane().setCase(currentPlayer.getTypePiece(), piece);
		}
	}

	public boolean endGame() {
		return board.isFull() || board.getNbPiece(TypePiece.BLACK) == 0
				|| board.getNbPiece(TypePiece.WHITE) == 0
				|| (getValidMove(whitePlayer).size() == 0 && getValidMove(blackPlayer).size() == 0);
	}

	public void play(int indiceCoup) {
		if (!endGame()) {
			Player jCourant = getTurn();
			Integer coupJouee = indiceCoup;
			if (validMove(jCourant, coupJouee)) {
				addMove(jCourant, coupJouee);
				setTurn();
			}
		}
	}

	/*
	public void start() {
		while (!partieTerminee()) {
			Joueur jCourant = getTour();
			Integer coupJouee = jCourant.jouerUnCoup(this);
			while (!coupValide(jCourant, coupJouee)) {
				coupJouee = jCourant.jouerUnCoup(this);
			}
			setTour();
			ajouterCoup(jCourant, coupJouee);
		}
	}
	*/

}

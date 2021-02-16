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
	// Determine a qui c'est le tour de jouer
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
	
	/**
	 * Determine si un coup est valide pour un joueur
	 * @param player le joueur effectuant le coup
	 * @param indexMove l'indice de la case sur laquelle la piece est jouee
	 * @return true si le coup est valide pour le joueur , false sinon
	 */
	public boolean validMove(Player player, int indexMove) {
		return getValidMoves(player).contains(indexMove);
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
	public List<Integer> getValidMoves(Player player) {
		List<Integer> validMove = new ArrayList<Integer>();

		for (int i = 0; i < 64; i++) {
			if (board.getSquare(i).isEmpty()
					&& CaptureEvaluator.capture(board, player.getTypePiece(), i).size() > 0) {
				validMove.add(i);
			}
		}
		return validMove;
	}
	
	/**
	 * Modifie la valeur de l'attribut blacksPlay permettant de determinee a qui c'est le tour de jouer
	 */
	public void setTurn() {
		// Si les noirs viennent de jouer et que les blancs ont un coup valide alors c'est au blanc de jouer
		if(blacksPlay && getValidMoves(whitePlayer).size() > 0) {
			blacksPlay  = false;
		}
		else if(!blacksPlay && getValidMoves(blackPlayer).size() > 0) {
			blacksPlay = true;
		}
	}
	
	/**
	 * Recupere le joueur a qui c'est le tour de jouer , fonctionne en tamdem avec setTurn
	 * @return Le joueur a qui c'est le tour de jouer
	 */
	public Player getTurn() {
		return blacksPlay ? blackPlayer : whitePlayer;
	}
	
	/**
	 * Ajoute un coup et ses repercussions (les captures) sur le plateau graphique et le plateau logique
	 * @param currentPlayer Le joeur qui joue le coup
	 * @param indexMove L'indice de la case du coup jouee
	 */
	public void addMove(Player currentPlayer, int indexMove) {
		List<Integer> capturedPiecesIndex = new ArrayList<Integer>();
		capturedPiecesIndex.addAll(CaptureEvaluator.capture(board, currentPlayer.getTypePiece(), indexMove));
		board.setSquare(currentPlayer.getTypePiece(), indexMove);
		board.getBoardPane().setCase(currentPlayer.getTypePiece(), indexMove);
		for (Integer piece : capturedPiecesIndex) {
			board.setSquare(currentPlayer.getTypePiece(), piece);
			board.getBoardPane().setCase(currentPlayer.getTypePiece(), piece);
		}
	}
	
	/**
	 * Determine si la partie est terminee
	 * @return true si la partie est terminee false sinon
	 */
	public boolean endGame() {
		return board.isFull() || board.getNbPiece(TypePiece.BLACK) == 0
				|| board.getNbPiece(TypePiece.WHITE) == 0
				|| (getValidMoves(whitePlayer).size() == 0 && getValidMoves(blackPlayer).size() == 0);
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

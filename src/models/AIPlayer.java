package models;

import java.util.List;

import app.Othello;

public class AIPlayer extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1559727994995938117L;
	private int level; // Niveau de l'IA
	private int depth;
	private int bestMove;
	public static int nbNoeud = 0;

	public AIPlayer(String name, TypePiece tp, int level) {
		super(name, tp);
		this.level = level;
		if (level == 1)
			this.depth = 1;
		if (level == 2)
			this.depth = 3;
	}
	
	public int mobility_evaluator(Othello game) {
		int mobilityAIPlayer = game.getValidMoves(this.tp).size();
		int mobilityOppositePlayer = game.getValidMoves(TypePiece.getOpposite(this.tp)).size();
		return mobilityAIPlayer - mobilityOppositePlayer;
	}
	
	public int material_evaluator(Othello game) {
		return game.getBoard().getNbPiece(this.getTypePiece())
		- game.getBoard().getNbPiece(TypePiece.getOpposite(this.getTypePiece()));
	}
	
	
	public int evaluate2(Othello game) {
		if(game.endGame() && game.getWinner().equals(this)) {
			return Integer.MAX_VALUE;
		}
		return 0;
	}
	public int evaluate(Othello game) {
		return material_evaluator(game);
	}

	public int getBestMove() {
		return bestMove;
	}

	private void setBestMove(int bestMove) {
		this.bestMove = bestMove;
	}

	public int playMove(Othello game) {
		Othello virtualGame = (Othello) Othello.deepClone(game);
		miniMaxAlphaBeta(virtualGame, depth, Double.MIN_VALUE, Double.MAX_VALUE, true);
		//miniMax(virtualGame,depth,true);
		return getBestMove();
	}

	public double miniMaxAlphaBeta(Othello virtualGame, int depth, double alpha, double beta,
			boolean maximizingPlayer) {
		nbNoeud++;
		if (depth == 0 || virtualGame.endGame()) {
			return evaluate(virtualGame);
		}
		if (maximizingPlayer) {
			Double maxEval = Double.MAX_VALUE * -1;
			List<Integer> validMoves = virtualGame.getValidMoves(this.getTypePiece());
			for (int move : validMoves) {
				Othello tempGame = (Othello) Othello.deepClone(virtualGame);
				tempGame.playAI(move);
				Double eval = miniMaxAlphaBeta(tempGame, depth - 1, alpha, beta, false);

				if (eval > maxEval) {
					if (this.depth == depth) {
						setBestMove(move);
					}
					maxEval = eval;
				}
				alpha = Math.max(alpha, eval);
				if (beta <= alpha)
					return beta;
			}

			return maxEval;
		} else {
			Double minEval = Double.MAX_VALUE;
			List<Integer> validMoves = virtualGame.getValidMoves(TypePiece.getOpposite(this.getTypePiece()));
			for (int move : validMoves) {
				Othello tempGame = (Othello) Othello.deepClone(virtualGame);
				tempGame.playAI(move);
				Double eval = miniMaxAlphaBeta(tempGame, depth - 1, alpha, beta, true);
				minEval = Math.min(minEval, eval);
				beta = Math.min(beta, eval);
				if (beta <= alpha)
					return alpha;
			}

			return minEval;
		}

	}

	public double miniMax(Othello virtualGame, int depth, boolean maximizingPlayer) {
		nbNoeud++;
		if (depth == 0 || virtualGame.endGame()) {
			return evaluate(virtualGame);
		}
		if (maximizingPlayer) {
			Double maxEval = Double.MAX_VALUE * -1;
			List<Integer> validMoves = virtualGame.getValidMoves(this.getTypePiece());
			for (int move : validMoves) {
				Othello tempGame = (Othello) Othello.deepClone(virtualGame);
				tempGame.playAI(move);
				Double eval = miniMax(tempGame, depth - 1, false);

				if (eval > maxEval) {
					if (this.depth == depth) {
						setBestMove(move);
					}
					maxEval = eval;
				}
			}
			return maxEval;
		} else {
			Double minEval = Double.MAX_VALUE;
			List<Integer> validMoves = virtualGame.getValidMoves(TypePiece.getOpposite(this.getTypePiece()));
			for (int move : validMoves) {
				Othello tempGame = (Othello) Othello.deepClone(virtualGame);
				tempGame.playAI(move);
				Double eval = miniMax(tempGame, depth - 1, true);
				minEval = Math.min(minEval, eval);
			}

			return minEval;
		}

	}
}

package models;

import java.util.List;

import app.Othello;

public class AIPlayer extends Player {
	/**
	 * 
	 */
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
			this.depth = 10;
	}
	
	public int mobility_evaluator(Board board) {
		int mobilityAIPlayer = board.getValidMoves(this.tp).size();
		int mobilityOppositePlayer = board.getValidMoves(TypePiece.getOpposite(this.tp)).size();
		return mobilityAIPlayer - mobilityOppositePlayer;
	}
	
	public int material_evaluator(Board board) {
		return board.getNbPiece(this.getTypePiece())
		- board.getNbPiece(TypePiece.getOpposite(this.getTypePiece()));
	}
	
	
	public int evaluate2(Board board) {
		return 0;
	}
	public int evaluate(Board board) {
		return material_evaluator(board);
	}

	public int getBestMove() {
		return bestMove;
	}

	private void setBestMove(int bestMove) {
		this.bestMove = bestMove;
	}

	public int playMove(Othello game) {
		miniMaxAlphaBeta(game.getBoard(), depth, Double.MIN_VALUE, Double.MAX_VALUE, true);
		//miniMax(virtualGame,depth,true);
		return getBestMove();
	}

	public double miniMaxAlphaBeta(Board position, int depth, double alpha, double beta,
			boolean maximizingPlayer) {
		nbNoeud++;
		if (depth == 0 || position.endGame()) {
			return evaluate(position);
		}
		if (maximizingPlayer) {
			Double maxEval = Double.MAX_VALUE * -1;
			List<Integer> validMoves = position.getValidMoves(this.getTypePiece());
			for (int move : validMoves) {
				Board newPosition = position.getBoardAfterMove(this.getTypePiece(), move);
				Double eval = miniMaxAlphaBeta(newPosition, depth - 1, alpha, beta, false);

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
			List<Integer> validMoves = position.getValidMoves(TypePiece.getOpposite(this.getTypePiece()));
			for (int move : validMoves) {
				Board newPosition = position.getBoardAfterMove(TypePiece.getOpposite(this.getTypePiece()), move);
				Double eval = miniMaxAlphaBeta(newPosition, depth - 1, alpha, beta, true);
				minEval = Math.min(minEval, eval);
				beta = Math.min(beta, eval);
				if (beta <= alpha)
					return alpha;
			}

			return minEval;
		}

	}

	public double miniMax(Board position, int depth, boolean maximizingPlayer) {
		nbNoeud++;
		if (depth == 0 || position.endGame()) {
			return evaluate(position);
		}
		if (maximizingPlayer) {
			Double maxEval = Double.MAX_VALUE * -1;
			List<Integer> validMoves = position.getValidMoves(this.getTypePiece());
			for (int move : validMoves) {
				Board newPosition = position.getBoardAfterMove(this.getTypePiece(), move);
				Double eval = miniMax(newPosition, depth - 1, false);

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
			List<Integer> validMoves = position.getValidMoves(TypePiece.getOpposite(this.getTypePiece()));
			for (int move : validMoves) {
				Board newPosition = position.getBoardAfterMove(TypePiece.getOpposite(this.getTypePiece()), move);
				Double eval = miniMax(newPosition, depth - 1, true);
				minEval = Math.min(minEval, eval);
			}

			return minEval;
		}

	}
}

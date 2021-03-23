package ia;

import java.util.List;

import app.Othello;
import models.Board;
import models.Player;
import models.TypePiece;

public class AIPlayer extends Player {

	private int level; // Niveau de l'IA
	private int bestMove;
	private int depth;
	public static int nbNoeud = 0;

	public AIPlayer(String name, TypePiece tp, int level) {
		super(name, tp);
		this.level = level;
		initDepth();
	}

	private void initDepth() {
		if (level % 2 == 1) {
			depth = 3;
		} else {
			depth = 5;
		}
	}

	public int evaluate(Board board) {
		if (board.endGame() && board.getNbPiece(this.getTypePiece()) > board
				.getNbPiece(TypePiece.getOpposite(this.getTypePiece()))) {
			return Integer.MAX_VALUE;
		}
		if (level <= 2) {
			return AIEvaluator.material_evaluator(board, this.getTypePiece());
		} else if (level <= 4) {
			return AIEvaluator.material_evaluator(board, this.getTypePiece())
					+ AIEvaluator.mobility_evaluator(board, this.getTypePiece()) * 3;
		} else if (level <= 6) {
			return AIEvaluator.material_evaluator(board, this.getTypePiece()) * 2
					+ AIEvaluator.mobility_evaluator(board, this.getTypePiece()) * 3
					+ AIEvaluator.position_evaluator(board, this.getTypePiece()) ;
		}
		
		return -1;

	}

	public int getBestMove() {
		return bestMove;
	}

	private void setBestMove(int bestMove) {
		this.bestMove = bestMove;
	}

	public int playMove(Othello game) {
		miniMaxAlphaBeta(game.getBoard(), depth, Double.MIN_VALUE, Double.MAX_VALUE, true);
		// miniMax(virtualGame,depth,true);
		return getBestMove();
	}

	public double miniMaxAlphaBeta(Board position, int depth, double alpha, double beta, boolean maximizingPlayer) {
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

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

	/**
	 * Defini la profondeur de l'algorithme utilisee. Profondeur 3 pour un niveau
	 * impair. Profondeur 5 pour un niveau pair.
	 */
	private void initDepth() {
		if (level % 2 == 1) {
			depth = 3;
		} else {
			depth = 5;
		}
	}

	/**
	 * 
	 * @param board Le plateau a evaluer
	 * @return Un score exploitable par MiniMax indiquant la valeur de la position.
	 */
	public double evaluate(Board board) {
		if (level <= 2) {
			return level1AIevaluator(board);
		} else if (level <= 4) {
			return level2AIevaluator(board);
		} else if (level <= 6) {
			return level3AIevaluator(board);
		}

		return -1;

	}

	public double level1AIevaluator(Board board) {
		return AIEvaluator.material_evaluator(board, this.getTypePiece());
	}

	public double level2AIevaluator(Board board) {
		return AIEvaluator.material_evaluator(board, this.getTypePiece()) + AIEvaluator.mobility_evaluator(board, this.getTypePiece());
	}

	public double level3AIevaluator(Board board) {
		int nbPieces = board.getPiecesIndex(TypePiece.BLACK).size() + board.getPiecesIndex(TypePiece.WHITE).size();
		double coeffMobility, coeffPosition, coeffMaterial;
		// A L'OUVERTURE, on cherche a reduire la mobilite de l'adversaire.
		if (nbPieces < 20) {
			coeffMaterial = 1; 
			coeffMobility = 1;
			coeffPosition = 10;
		}
		// EN MIDGAME, ON CHERCHE A OBTENIR UNE BONNE POSITION
		else if (nbPieces < 50) {
			coeffMaterial = 5;
			coeffPosition = 10;
			coeffMobility = 2;
		}
		// EN FINALE, SEUL LE NOMBRE DE PIECE COMPTE.
		else {
			coeffMaterial = 10;
			coeffMobility = 1;
			coeffPosition = 5;
		}
		return coeffMobility * AIEvaluator.mobility_evaluator(board, this.getTypePiece())
				+ coeffPosition * AIEvaluator.position_evaluator(board, this.getTypePiece())
				+ coeffMaterial * AIEvaluator.material_evaluator(board, this.getTypePiece());
	}

	public int getBestMove() {
		return bestMove;
	}

	private void setBestMove(int bestMove) {
		this.bestMove = bestMove;
	}

	public int playMove(Othello game) {
		miniMaxAlphaBeta(game.getBoard(), depth, - Double.MAX_VALUE, Double.MAX_VALUE, true);
		//miniMax(game.getBoard(),depth,true);
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
				maxEval = Math.max(maxEval, eval);
				alpha = Math.max(alpha, eval);
				if (eval >= maxEval ) {
					if (this.depth == depth) {
						setBestMove(move);
					}
				}
				
				if (beta <= alpha)
					break;
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
					break;
				
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
				maxEval = Math.max(maxEval, eval);
				if (eval >= maxEval) {
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
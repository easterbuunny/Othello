package ia;

import java.util.List;

import models.Board;
import models.TypePiece;

public class AIEvaluator {

	public static int weight[] = { 100, -20, 10, 5, 5, 10, -20, 100, -20, -50, -2, -2, -2, -2, -50, -20, 10, -2, -1, -1,
			-1, -1, -2, 10, 5, -2, -1, -1, -1, -1, -2, -5, 5, -2, -1, -1, -1, -1, -2, -5, 10, -2, -1, -1, -1, -1, -2,
			10, -20, -50, -2, -2, -2, -2, -50, -20, 100, -20, 10, 5, 5, 10, -20, 100 };

	/**
	 * Evalue une position en prenant comme critere la mobilite.
	 * 
	 * @param board Le plateau a evaluer.
	 * @param tp    Type de piece du joueur appelant la methode.
	 * @return La difference de nombre de coup disponible entre le joueur appelant
	 *         et l'adversaire.
	 */
	public static double mobility_evaluator(Board board, TypePiece tp) {
		int mobilityAIPlayer = board.getValidMoves(tp).size();
		int mobilityOppositePlayer = board.getValidMoves(TypePiece.getOpposite(tp)).size();
		return (mobilityAIPlayer - mobilityOppositePlayer);
	}

	/**
	 * Evalue une position en prenant comme critere la difference de materiel.
	 * 
	 * @param board Le plateau a evaluer.
	 * @param tp    Type de piece du joueur appelant la methode.
	 * @return La difference de piece entre le joueur appelant et l'adversaire dans
	 *         la position.
	 */
	public static int material_evaluator(Board board, TypePiece tp) {
		if (board.endGame()
				&& board.getNbPiece(tp) > board.getNbPiece(TypePiece.getOpposite(tp))) {
			return 10000;
		}
		if (board.endGame()
				&& board.getNbPiece(tp) < board.getNbPiece(TypePiece.getOpposite(tp))) {
			return -10000;
		}
		return board.getNbPiece(tp) - board.getNbPiece(TypePiece.getOpposite(tp));
	}

	/**
	 * Evalue une position en compte la position des pieces sur l'othellier.
	 * 
	 * @param board Le plateau a evaluer.
	 * @param tp    Type de piece du joueur appelant la methode.
	 * @return Un score de la position.
	 */
	public static int position_evaluator(Board board, TypePiece tp) {
		List<Integer> playerPieces = board.getPiecesIndex(tp);
		List<Integer> opponentPieces = board.getPiecesIndex(TypePiece.getOpposite(tp));

		return getScorePosition(playerPieces) - getScorePosition(opponentPieces);
	}

	private static int getScorePosition(List<Integer> indexPieces) {
		int scorePlayer = 0;
		for (int indexPiece : indexPieces) {
			scorePlayer += getValueSquare(indexPiece);
		}
		return scorePlayer;
	}

	/**
	 * Renvoie une valeur pour chacune des cases de l'othellier (pour le millieu de
	 * jeu)
	 * 
	 * @param indexSquare Indice de la case a evaluer.
	 * @return Le score arbitraire de la case
	 */
	public static int getValueSquare(int indexSquare) {
		return weight[indexSquare];
	}

}
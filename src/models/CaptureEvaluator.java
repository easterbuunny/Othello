package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CaptureEvaluator {
	/**
	 * Permet de lancer une recherche des pieces capturee dans l'ensemble des directions.
	 * @param board Le plateau sur lequel le coup est jouee
	 * @param playingPiece Le type de piece qui joue le coup
	 * @param squareIndex L'indice de la case ou la piece est placee
	 * @return L'ensemble des pieces capturee.
	 */
	public static HashSet<Integer> capture(Board board, TypePiece playingPiece, int squareIndex) {
		HashSet<Integer> capturedPieces = new HashSet<Integer>();
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.NORTH));
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.SOUTH));
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.WEST));
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.EAST));
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.NORTHWEST));
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.NORTHEAST));
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.SOUTHWEST));
		capturedPieces.addAll(captureEvaluator(board, playingPiece, squareIndex, CaptureEnum.SOUTHEAST));
		return capturedPieces;
	}
	/**
	 * 
	 * @param board Le plateau sur lequel le coup est jouee.
	 * @param playingPiece Le type de piece qui joue le coup.
	 * @param squareIndex L'indice de la case ou la piece est placee.
	 * @param ce Enumeration indiquant la direction dans laquel chercher des pieces a capturee.
	 * @return Les pieces capturee suite au coup dans la direction passee en parametre.
	 */
	public static List<Integer> captureEvaluator(Board board, TypePiece playingPiece, int squareIndex,
			CaptureEnum ce) {
		
		int i = squareIndex;
		TypePiece opposingPiece = TypePiece.getOpposite(playingPiece);
		List<Integer> capturedPieces = new ArrayList<Integer>();
		// Si la piece ne peut effectue de capture dans la direction passee en parametre
		// Ou que La piece suivante dans la direction est la meme que la piece qui joue 
		// Alors pas de capture possible : on renvoie une liste vide de piece capturee.
		if (ce.cannotCaptureInThisDirection(squareIndex)
				|| board.getSquare(squareIndex + ce.getNextSquare()).getPiece().getTypePiece().equals(playingPiece))
			return capturedPieces;
		
		// Tant que la capture dans la direction est possible et que la piece suivante dans la direction
		// Est une piece adverse alors on les ajoute aux pieces capturee.
		while (!ce.cannotCaptureInThisDirection(i)
				&& board.getSquare(i + ce.getNextSquare()).getPiece().getTypePiece().equals(opposingPiece)) {
			capturedPieces.add(i + ce.getNextSquare());
			i += ce.getNextSquare();
		}
		
		// On sort de la boucle soit car on a atteint une case limite pour la direction soit car on est tombe
		// Sur une piece appartenant au joueur qui joue le coup.
		
		// Si on n'a pas atteint la limite dans la direction et que la case suivante dans la direction est une piece
		// du meme type que le joueur qui joue le coup, alors une capture est possible. 
		// On renvoie donc les pieces capturees
		if (!ce.cannotCaptureInThisDirection(i)
				&& board.getSquare(i + ce.getNextSquare()).getPiece().getTypePiece().equals(playingPiece))
			return capturedPieces;
		
		// Si on a atteint la limite dans la direction mais que la case finale est une piece du meme type que le joueur
		// qui joue le coup alors la capture est possible.
		if (ce.cannotCaptureInThisDirection(i) && board.getSquare(i).getPiece().getTypePiece().equals(playingPiece))
			return capturedPieces;
		
		// Dans tout les autres cas la capturee n'est pas possible
		return new ArrayList<Integer>();
	}
}

package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CaptureEvaluator {
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

	public static List<Integer> captureEvaluator(Board board, TypePiece playingPiece, int squareIndex,
			CaptureEnum ce) {
		int i = squareIndex;
		TypePiece opposingPiece = playingPiece.equals(TypePiece.BLACK) ? TypePiece.WHITE : TypePiece.BLACK;
		List<Integer> capturedPieces = new ArrayList<Integer>();
		// Si la piece ne peut effectue de capture en hauteur retourner list vide.
		if (ce.evaluateCondition(i)
				|| board.getSquare(i + ce.getNextSquare()).getPiece().getTypePiece().equals(playingPiece))
			return capturedPieces;

		while (!ce.evaluateCondition(i)
				&& board.getSquare(i + ce.getNextSquare()).getPiece().getTypePiece().equals(opposingPiece)) {
			capturedPieces.add(i + ce.getNextSquare());
			i += ce.getNextSquare();
		}

		if (!ce.evaluateCondition(i)
				&& board.getSquare(i + ce.getNextSquare()).getPiece().getTypePiece().equals(playingPiece))
			return capturedPieces;

		if (ce.evaluateCondition(i) && board.getSquare(i).getPiece().getTypePiece().equals(playingPiece))
			return capturedPieces;

		return new ArrayList<Integer>();
	}
}

package models;

public enum TypePiece {
	BLACK, WHITE, NONE;

	public static TypePiece getOpposite(TypePiece tp) {
		return tp.equals(BLACK) ? WHITE : BLACK;
	}
}

package models;

public enum TypePiece {
	// Valeur possible prise par un type de piece
	BLACK, WHITE, NONE;

	/**
	 * La methode permet de recuperer le type de piece "oppose" a la piece appelant la methode.
	 * @param tp, type de piece du joueur actuelle;
	 * @return tp, le type de piece 
	 * Renvoie  une piece de type "BLACK" si la methode est appele par une piece de type "WHITE" et inversement
	 */
	public static TypePiece getOpposite(TypePiece tp) {
		return tp.equals(BLACK) ? WHITE : BLACK;
	}
}

package models;

public class Square {
	private Piece piece;

	public Square() {
		piece = new Piece(TypePiece.NONE);
	}

	/**
	 * Methode qui permet de determiner si une case contient une piece.
	 * 
	 * @return true si la case contient une piece , false sinon
	 */
	public boolean isEmpty() {
		return piece.getTypePiece().equals(TypePiece.NONE);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(TypePiece tp) {
		piece.setPiece(tp);
	}
}

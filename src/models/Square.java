package models;

public class Square {
	//Attributs
	private Piece piece;

	//Constructeur
	public Square() {
		//Creer une piece ni blanche ni noir, elle est a definir
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

	//Getter & Setter
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(TypePiece tp) {
		piece.setPiece(tp);
	}
}

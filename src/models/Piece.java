package models;

public class Piece {
	//Attributs
	private TypePiece typePiece;

	//Constructeur
	public Piece(TypePiece tp) {
		typePiece = tp;
	}

	//Getter & Setter
	public TypePiece getTypePiece() {
		return typePiece;
	}

	public void setPiece(TypePiece tp) {
		this.typePiece = tp;
	}

}

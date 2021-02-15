package models;

public class Piece {
	private TypePiece typePiece;
	
	public Piece(TypePiece tp) {
		typePiece = tp;
	}
	public TypePiece getTypePiece(){
		return typePiece;
	}
	public void setPiece(TypePiece tp) {
		this.typePiece = tp;
	}
	
}

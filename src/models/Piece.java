package models;

import java.io.Serializable;

public class Piece implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8921688650873618060L;
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

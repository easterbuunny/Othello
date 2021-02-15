package models;

public abstract class Player {
	private String name;
	TypePiece tp;
	
	public Player(String name, TypePiece tp) {
		this.name = name;
		this.tp = tp;
	}
	
	public TypePiece getTypePiece() {
		return tp;
	}
	
	public String toString() {
		return name;
	}
}

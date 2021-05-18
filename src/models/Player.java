package models;

public abstract class Player {

	private String name; // Nom du joueur
	TypePiece tp; // Type de piece avec lequel le joueur jouera

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

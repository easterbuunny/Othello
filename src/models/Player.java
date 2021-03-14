package models;

import java.io.Serializable;

public abstract class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1696436491628251357L;
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

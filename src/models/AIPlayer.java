package models;

import app.Othello;

public class AIPlayer extends Player {
	private int level;

	public AIPlayer(String nom, TypePiece tp, int niveau) {
		super(nom, tp);
		this.level = niveau;
	}

	public int playMove(Othello game) {
		if (game.getValidMove(this).size() > 0)
			return game.getValidMove(this).get(0);
		return -1;
	}

}

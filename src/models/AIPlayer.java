package models;

import app.Othello;

public class AIPlayer extends Player {
	private int level; // Niveau de l'IA

	public AIPlayer(String name, TypePiece tp, int level) {
		super(name, tp);
		this.level = level;
	}

	public int playMove(Othello game) {
		if (game.getValidMoves(this).size() > 0)
			return game.getValidMoves(this).get(0);
		return -1;
	}

}

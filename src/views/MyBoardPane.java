package views;

import java.io.Serializable;
import java.util.List;

import app.Othello;
import handlers.Handler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import models.TypePiece;

public class MyBoardPane extends GridPane implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1749033816973926566L;
	private final int gridSize = 8;
	private MySquarePane squarePane[];

	public MyBoardPane(Handler h) {
		squarePane = new MySquarePane[64];
		for (int row = 0; row < gridSize; row++) {
			for (int column = 0; column < gridSize; column++) {
				MySquarePane cp = new MySquarePane(gridSize * row + column);
				this.add(cp, row, column);
				squarePane[gridSize * row + column] = cp;
				cp.setOnMouseClicked(event -> h.handle(event));
			}
		}
		this.setAlignment(Pos.CENTER);
		this.setVgap(1);
		this.setHgap(1);
		init();
	}

	public MySquarePane getCaseByRowColomnIndex(int row, int column) {
		return (MySquarePane) this.getChildren().get(gridSize * row + column);
	}

	public void init() {
		this.getCaseByRowColomnIndex(3, 3).getChildren().add(new MyPiecePane(TypePiece.WHITE));
		this.getCaseByRowColomnIndex(4, 4).getChildren().add(new MyPiecePane(TypePiece.WHITE));
		this.getCaseByRowColomnIndex(4, 3).getChildren().add(new MyPiecePane(TypePiece.BLACK));
		this.getCaseByRowColomnIndex(3, 4).getChildren().add(new MyPiecePane(TypePiece.BLACK));
	}

	public void setCase(TypePiece tp, int indiceCase) {
		squarePane[indiceCase].setCasePane(tp);
	}

	public void setValidSquares(Othello game, boolean emphasizeValidSquares) {
		List<Integer> validMoves = game.getValidMoves(game.getTurn().getTypePiece());
		for (Integer validMove : validMoves) {
			squarePane[validMove].setColorSquare(emphasizeValidSquares ? Color.rgb(250,128, 114) : Color.GREEN);
		}
	}

}

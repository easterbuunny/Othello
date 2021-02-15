package views;

import handlers.Handler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import models.TypePiece;

public class MyBoardPane extends GridPane {
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
}

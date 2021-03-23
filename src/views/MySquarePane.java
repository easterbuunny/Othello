package views;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.TypePiece;

public class MySquarePane extends StackPane {
	private static final double squareSize = 50;
	private static final Color squareColor = Color.GREEN;
	private int indiceCase;

	public MySquarePane(int indiceCase) {
		super();
		this.getChildren().add(new Rectangle(squareSize, squareSize, squareColor));
		this.indiceCase = indiceCase;

	}

	public void setCasePane(TypePiece tp) {
		this.getChildren().add(new MyPiecePane(tp));
	}

	public void setColorSquare(Color color) {
		this.getChildren().add(new Rectangle(squareSize, squareSize, color));
	}

	public int getIndiceCase() {
		return indiceCase;
	}

}

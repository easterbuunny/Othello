package views;

import java.io.Serializable;
import java.util.List;

import app.Othello;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.TypePiece;

public class MySquarePane extends StackPane implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1218674929425360748L;
	private static final double caseSize = 50;
	private static final Color colorCase = Color.GREEN;
	private int indiceCase;

	public MySquarePane(int indiceCase) {
		super();
		this.getChildren().add(new Rectangle(caseSize, caseSize, colorCase));
		this.indiceCase = indiceCase;

	}

	public void setCasePane(TypePiece tp) {
		this.getChildren().add(new MyPiecePane(tp));
	}
	
	public void setColorSquare(Color color) {
		this.getChildren().add(new Rectangle(caseSize, caseSize, color));
	}

	public int getIndiceCase() {
		return indiceCase;
	}

}

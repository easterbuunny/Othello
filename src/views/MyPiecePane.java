package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.TypePiece;

public class MyPiecePane extends Circle {
	private static final double pieceSize = 20;
	private static final double pieceRadius = 20;
	private static final Color colorWhite = Color.WHITE;
	private static final Color colorBlack = Color.BLACK;
	
	
	public MyPiecePane(TypePiece tp) {
		super(pieceSize,pieceSize,pieceRadius);
		setTypePiece(tp);
	}
	
	public void setTypePiece(TypePiece tp) {
		if(tp.equals(TypePiece.BLACK))
			this.setFill(colorBlack);
		if(tp.equals(TypePiece.WHITE))
			this.setFill(colorWhite);
	}
}

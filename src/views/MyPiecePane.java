package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.TypePiece;

public class MyPiecePane extends Circle {
	//Attributs
	private static final double pieceSize = 20; //T ille de la piece
	private static final double pieceRadius = 20; // Taille du rayon
	private static final Color colorWhite = Color.WHITE; // Couleur de la piece de type "Blanc"
	private static final Color colorBlack = Color.BLACK; // Couleur de la piece de type "Noir"
	
	//Constructeur
	public MyPiecePane(TypePiece tp) {
		//Dessine une piece ronde
		super(pieceSize,pieceSize,pieceRadius);
		setTypePiece(tp);
	}
	
	//Setter
	public void setTypePiece(TypePiece tp) {
		//Recupere le type de piece la colorie avec la couleur qui correspond
		//Piece noir
		if(tp.equals(TypePiece.BLACK))
			this.setFill(colorBlack);
		//Piece blanche
		if(tp.equals(TypePiece.WHITE))
			this.setFill(colorWhite);
	}
}

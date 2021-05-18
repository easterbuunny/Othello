package views;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.TypePiece;

public class MySquarePane extends StackPane {
	//Attributs
	private static final double squareSize = 50; // taille de la case
	private static final Color squareColor = Color.GREEN; // Couleur de la case
	private int indiceCase; // Indice de la case

	//Constructeur
	public MySquarePane(int indiceCase) {
		//Redefinir le constructeur du javaFX
		super();
		//Ajoute une case Rectangle 50 de longeur x 50 de largeur (carre) de couleur vert
		this.getChildren().add(new Rectangle(squareSize, squareSize, squareColor));
		this.indiceCase = indiceCase;

	}

	//Setter && Getter
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

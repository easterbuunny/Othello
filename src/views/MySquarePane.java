package views;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import models.TypePiece;

public class MySquarePane extends StackPane{
	private static final double caseSize = 50;
	private static final Color colorCase = Color.GREEN;
	private int indiceCase;
	
	public MySquarePane(int indiceCase) {
		super();
		this.getChildren().add(new Rectangle(caseSize,caseSize,colorCase));
		this.indiceCase = indiceCase;
		
	}
	public void setCasePane(TypePiece tp) {
		this.getChildren().add(new MyPiecePane(tp));
	}
	
	public int getIndiceCase() {
		return indiceCase;
	}
	
}

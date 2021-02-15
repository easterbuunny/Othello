package views;

import app.Main;
import app.Othello;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Player;
import models.HumanPlayer;
import models.AIPlayer;
import models.TypePiece;

public class MyStartingPane extends BorderPane {
	public MyStartingPane() {
		VBox vbox = new VBox();
		Button playerModeButton = new Button("Jouer contre un joueur");
		Button IAmodeButton = new Button("Jouer contre une IA");
		Button IAvsIAmodeButton = new Button("IA vs IA");

		vbox.getChildren().addAll(playerModeButton, IAmodeButton, IAvsIAmodeButton);
		vbox.setAlignment(Pos.CENTER);

		playerModeButton.setOnMouseClicked((event) -> {
			Othello game = new Othello(new HumanPlayer("J1", TypePiece.BLACK), new HumanPlayer("J2", TypePiece.WHITE));
			Main.setPaneRoot(new MyGamePane(game));
		});
		IAmodeButton.setOnMouseClicked((event) -> {
			// TODO : Determiner le niveau + qui joue les noirs
			Player p1 = new HumanPlayer("J1", TypePiece.BLACK);
			Player p2 = new AIPlayer("IA", TypePiece.WHITE, 1);
			Othello game = new Othello(p1, p2);
			Main.setPaneRoot(new MyGamePane(game));
		});
		IAvsIAmodeButton.setOnMouseClicked((event) -> {
		});

		this.setCenter(vbox);
	}
}
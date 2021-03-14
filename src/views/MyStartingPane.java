package views;

import java.util.Optional;

import app.Main;
import app.Othello;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;

import models.HumanPlayer;
import models.AIPlayer;
import models.TypePiece;

public class MyStartingPane extends BorderPane {
	private int levelAI;
	private TypePiece humanPlayerPieces;

	public MyStartingPane() {
		VBox vbox = new VBox();
		Button playerModeButton = new Button("Jouer contre un joueur");
		Button IAmodeButton = new Button("Jouer contre une IA");
		Button IAvsIAmodeButton = new Button("IA vs IA");

		vbox.getChildren().addAll(playerModeButton, IAmodeButton, IAvsIAmodeButton);
		vbox.setAlignment(Pos.CENTER);

		playerModeButton.setOnMouseClicked(event -> handlePlayerMode());
		IAmodeButton.setOnMouseClicked(event -> showChoices());

		IAvsIAmodeButton.setOnMouseClicked((event) -> {
		});

		this.setCenter(vbox);
	}

	private void showChoices() {
		ChoiceDialog<TypePiece> dialog = new ChoiceDialog<TypePiece>(TypePiece.BLACK, TypePiece.WHITE);
		dialog.setTitle("Choix de la couleur");
		dialog.setHeaderText("Choisir votre couleur");
		dialog.setContentText("Couleur:");
		Optional<TypePiece> result = dialog.showAndWait();
		result.ifPresent((choice) -> {
			this.humanPlayerPieces = choice;
			showChoiceLevelDialog();
		});
		if (result.isEmpty())
			Main.setPaneRoot(new MyStartingPane());
	}

	private void showChoiceLevelDialog() {
		ChoiceDialog<Integer> dialog = new ChoiceDialog<Integer>(1, 2);
		dialog.setTitle("Choix du Niveau de l'IA");
		dialog.setHeaderText("Choisir le niveau de l'IA");
		dialog.setContentText("Niveau :");
		Optional<Integer> result = dialog.showAndWait();
		result.ifPresent((choice) -> {
			this.levelAI = choice;
			HumanPlayer humanPlayer = new HumanPlayer("J1", humanPlayerPieces);
			AIPlayer aiPlayer = new AIPlayer("IA", TypePiece.getOpposite(humanPlayerPieces), levelAI);
			Othello game = humanPlayerPieces.equals(TypePiece.WHITE) ? new Othello(aiPlayer, humanPlayer) : new Othello(humanPlayer, aiPlayer);
			Main.setPaneRoot(new MyGamePane(game));

		});
		if (result.isEmpty())
			Main.setPaneRoot(new MyStartingPane());
	}

	public void handlePlayerMode() {
		Othello game = new Othello(new HumanPlayer("J1", TypePiece.BLACK), new HumanPlayer("J2", TypePiece.WHITE));
		Main.setPaneRoot(new MyGamePane(game));
	}
}
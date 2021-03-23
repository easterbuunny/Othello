package views;

import java.util.Optional;

import app.Main;
import app.Othello;
import ia.AIPlayer;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;

import models.HumanPlayer;
import models.TypePiece;

public class MyStartingPane extends BorderPane {
	private int levelAI;
	private TypePiece humanPlayerPieces;
	private final int LEVELS = 6;

	public MyStartingPane() {
		VBox vbox = new VBox();
		Button playerModeButton = new Button("Jouer contre un joueur");
		Button IAmodeButton = new Button("Jouer contre une IA");
		Button IAvsIAmodeButton = new Button("IA vs IA");

		vbox.getChildren().addAll(playerModeButton, IAmodeButton, IAvsIAmodeButton);
		vbox.setAlignment(Pos.CENTER);

		playerModeButton.setOnMouseClicked(event -> handlePlayerMode());
		IAmodeButton.setOnMouseClicked(event -> {
			showChoices();
			handleAIMode();
		});

		IAvsIAmodeButton.setOnMouseClicked((event) -> {
			// Recupere le niveau pour l'IA 1
			showChoiceLevelDialog();
			AIPlayer aiPlayer1 = new AIPlayer("IA1 (Black) ",TypePiece.BLACK,this.levelAI);
			// Recupere le niveau pour l'IA 2
			showChoiceLevelDialog();
			AIPlayer aiPlayer2 = new AIPlayer("IA2 (White) ",TypePiece.WHITE,this.levelAI);
			
			Othello game = new Othello(aiPlayer1,aiPlayer2);
			Main.setPaneRoot(new MyGamePane(game));
		});

		this.setCenter(vbox);
	}

	private void showChoices() {
		ChoiceDialog<TypePiece> dialog = new ChoiceDialog<TypePiece>();
		ObservableList<TypePiece> list = dialog.getItems();
		dialog.setTitle("Choix de la couleur");
		dialog.setHeaderText("Choisir votre couleur");
		dialog.setContentText("Couleur:");
		list.add(TypePiece.BLACK);
		list.add(TypePiece.WHITE);
		Optional<TypePiece> result = dialog.showAndWait();
		result.ifPresent((choice) -> {
			this.humanPlayerPieces = choice;
			showChoiceLevelDialog();
		});
		if (result.isEmpty())
			Main.setPaneRoot(new MyStartingPane());
	}

	private void showChoiceLevelDialog() {
		ChoiceDialog<Integer> dialog = new ChoiceDialog<Integer>();
		ObservableList<Integer> list = dialog.getItems();
		int i = 0;
		while(i++ < LEVELS) list.add(i);
		dialog.setTitle("Choix du Niveau de l'IA");
		dialog.setHeaderText("Choisir le niveau de l'IA");
		dialog.setContentText("Niveau :");
		Optional<Integer> result = dialog.showAndWait();
		result.ifPresent((choice) -> {
			this.levelAI = choice;
		});
		if (result.isEmpty())
			Main.setPaneRoot(new MyStartingPane());
	}
	
	private void handleAIMode() {
		HumanPlayer humanPlayer = new HumanPlayer("J1", humanPlayerPieces);
		AIPlayer aiPlayer = new AIPlayer("IA", TypePiece.getOpposite(humanPlayerPieces), levelAI);
		Othello game = humanPlayerPieces.equals(TypePiece.WHITE) ? new Othello(aiPlayer, humanPlayer) : new Othello(humanPlayer, aiPlayer);
		Main.setPaneRoot(new MyGamePane(game));
	}

	public void handlePlayerMode() {
		Othello game = new Othello(new HumanPlayer("J1", TypePiece.BLACK), new HumanPlayer("J2", TypePiece.WHITE));
		Main.setPaneRoot(new MyGamePane(game));
	}
}
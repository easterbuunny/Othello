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
    //Attributs
    private int levelAI; //niveau IA 
    private TypePiece humanPlayerPieces; // Choix de la piece du joueur
    private final int LEVELS = 6; // niveau maximum

    //Constructeur
    public MyStartingPane() {
        VBox vbox = new VBox();
        // Les bouttons afin d'acceder aux differents mode de jeu
        Button playerModeButton = new Button("Jouer contre un joueur");
        Button IAmodeButton = new Button("Jouer contre une IA");
        Button IAvsIAmodeButton = new Button("IA vs IA");


        vbox.getChildren().addAll(playerModeButton, IAmodeButton, IAvsIAmodeButton);
        vbox.setAlignment(Pos.CENTER); //Aligner au centre les boutons


        playerModeButton.setOnMouseClicked(event -> handlePlayerMode());
        IAmodeButton.setOnMouseClicked(event -> handleAIMode());
        IAvsIAmodeButton.setOnMouseClicked((event) -> handleAIvsAIMode());

        this.setCenter(vbox);
    }


    /**
     * Le choix pour le mode de jeu joueur contre IA
     * @return  true si le joueur clique sur ok pour enrengistrer le choix, 
     * false sinon.
     */
    private boolean getChoices() {
        //Creation des boite de dialogue
        ChoiceDialog < TypePiece > dialog = new ChoiceDialog < TypePiece > ();
        ObservableList < TypePiece > list = dialog.getItems();
        dialog.setTitle("Choix de la couleur"); // Titre de la fenetre
        dialog.setHeaderText("Choisir votre couleur"); // en-tete de la fenetre
        dialog.setContentText("Couleur:"); // contenu de la fenetre
        //Liste de choix de couleur
        list.add(TypePiece.BLACK);
        list.add(TypePiece.WHITE);
        Optional < TypePiece > result = dialog.showAndWait(); //Bouton "Ok" et "Cancel"
        //Attente de la reponse du joueur 
        result.ifPresent((choice) -> {
            this.humanPlayerPieces = choice;
        });
        return !result.isEmpty(); // Si "ok" true, sinon "cancel" false
    }

    /**
     * La methode propose le niveau de difficulte de l'IA dans le mode jeu joueur contre IA et IA contre IA
     * @return  true si le joueur clique sur ok pour enrengistrer le choix, 
     * false sinon.
     */
    private boolean choiceLevelMade() {
        //Creation des boites de dialogue
        ChoiceDialog < Integer > dialog = new ChoiceDialog < Integer > ();
        ObservableList < Integer > list = dialog.getItems();
        int i = 0;

        while (i++ < LEVELS)
            list.add(i); //Ajouter le niveau dans la liste deroulante de choix
        dialog.setTitle("Choix du Niveau de l'IA"); // Titre de la fenetre
        dialog.setHeaderText("Choisir le niveau de l'IA"); // En tete de la fenetre
        dialog.setContentText("Niveau :"); // Le contenu de la fenetre 
        Optional < Integer > result = dialog.showAndWait(); // Bouton "Ok" et "Cancel"
        // Attente de la reponse du joueur
        result.ifPresent((choice) -> {
            this.levelAI = choice;
        });
        return !result.isEmpty(); //Si "ok" true, sinon "cancel" false
    }

    /**
     * Creer les deux joueurs IA, demande le niveau de chacune des IA et lance la partie en mode IA vs IA
     */
    private void handleAIvsAIMode() {
        // Demande le niveau pour l'IA 1
        if (choiceLevelMade()) {
            // La Premiere IA possede le type de piece noir
            AIPlayer aiPlayer1 = new AIPlayer("IA1", TypePiece.BLACK, this.levelAI);
            // Demande le niveau pour l'IA 2
            if (choiceLevelMade()) {
                //Deuxieme IA possede le type de piece blanche
                AIPlayer aiPlayer2 = new AIPlayer("IA2", TypePiece.WHITE, this.levelAI);
                //Lance le jeu IA contre IA
                Othello game = new Othello(aiPlayer1, aiPlayer2);
                Main.setPaneRoot(new MyGamePane(game));
            }
        }

    }

    /**
     * Creer les deux joueurs HumanPlayer et IA, demande le niveau de l'IA et lance la partie en mode IA vs Joueur
     */
    private void handleAIMode() {
        //Applique la methode demandant le choix du type de piece du joueur
        if (getChoices()) {
            //Applique la methode pour obtenir le choix du niveau de l'IA
            if (choiceLevelMade()) {
                //Type de piece du joueur
                HumanPlayer humanPlayer = new HumanPlayer("J1", humanPlayerPieces);

                AIPlayer aiPlayer = new AIPlayer("IA", TypePiece.getOpposite(humanPlayerPieces), levelAI);
                //Lance le jeu joueur contre IA
                Othello game = humanPlayerPieces.equals(TypePiece.WHITE) ? new Othello(aiPlayer, humanPlayer) :
                    new Othello(humanPlayer, aiPlayer);
                Main.setPaneRoot(new MyGamePane(game));
            }
        }

    }

    /**
     * Creer les deux joueurs Humain et lance la partie.
     */
    private void handlePlayerMode() {
        //Lance le jeu joueur contre joueur
        Othello game = new Othello(new HumanPlayer("N", TypePiece.BLACK), new HumanPlayer("B", TypePiece.WHITE));
        Main.setPaneRoot(new MyGamePane(game));
    }
}
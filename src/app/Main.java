package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.MyStartingPane;

public class Main extends Application {
	
	private static Stage primaryStage; // Fenetre de l'application
	public static void main(String args[]) {
		launch(args);
	}    
 
	@Override
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		Pane startingPane = new MyStartingPane();
		
		primaryStage.setScene(new Scene(startingPane));
		primaryStage.setTitle("Othello");
		primaryStage.setHeight(600);
		primaryStage.setWidth(600);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * Permet de modifier la scene qui se deroule sur la fenetre
	 * @param myPane Le panneau que l'on souhaite afficher
	 */
	public static void setPaneRoot(Pane myPane) {
		Main.primaryStage.getScene().setRoot(myPane);
	}
}

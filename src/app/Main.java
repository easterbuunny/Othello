package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.MyStartingPane;

public class Main extends Application {
	
	private static Stage prymaryStage; // Fenetre de l'application
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage prymaryStage) throws Exception {
		Main.prymaryStage = prymaryStage;
		Pane startingPane = new MyStartingPane();
		
		prymaryStage.setScene(new Scene(startingPane));
		prymaryStage.setHeight(600);
		prymaryStage.setWidth(600);
		prymaryStage.centerOnScreen();
		prymaryStage.setResizable(true);
		prymaryStage.show();
	}
	
	/**
	 * Permet de modifier la scene qui se deroule sur la fenetre
	 * @param myPane Le panneau que l'on souhaite afficher
	 */
	public static void setPaneRoot(Pane myPane) {
		Main.prymaryStage.getScene().setRoot(myPane);
	}
}

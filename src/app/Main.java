package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.MyStartingPane;

public class Main extends Application {
	
	private static Stage prymaryStage;
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage prymaryStage) throws Exception {
		Main.prymaryStage = prymaryStage;
		Pane startingPane = new MyStartingPane();

		prymaryStage.setScene(new Scene(startingPane));
		prymaryStage.setHeight(500);
		prymaryStage.setWidth(500);
		prymaryStage.centerOnScreen();
		prymaryStage.setResizable(false);
		prymaryStage.show();
	}
	
	public static void setPaneRoot(Pane myPane) {
		Main.prymaryStage.getScene().setRoot(myPane);
	}
}

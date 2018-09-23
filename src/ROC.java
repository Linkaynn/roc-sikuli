import javafx.application.Application;
import javafx.stage.Stage;
import settings.Settings;

import static actions.util.PlacesMovements.initializeMovements;

public class ROC extends Application{
	public static void main(String[] args) {
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Settings.initialize();
		initializeMovements();
	}
}

import javafx.application.Application;
import javafx.stage.Stage;
import settings.Settings;

import java.io.IOException;

public class ROC extends Application{
	public static void main(String[] args) {
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Settings.initialize();
	}
}

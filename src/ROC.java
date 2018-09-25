import actions.ExploreAction;
import javafx.application.Application;
import javafx.stage.Stage;
import settings.KeysHandler;
import settings.Settings;

import static status.Status.EXPLORING;
import static status.Status.RUNNING;

public class ROC extends Application {
	private ExploreAction exploreAction = ExploreAction.instance();

	public static void main(String[] args) {
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Settings.initialize();
		KeysHandler.init();

		while (RUNNING) {
			if (EXPLORING) {
				exploreAction.start();
			}
		}
	}
}

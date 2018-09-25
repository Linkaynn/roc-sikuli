import actions.ExploreAction;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.Server;
import settings.KeysHandler;
import settings.Settings;

import static status.Status.EXPLORING;
import static status.Status.RUNNING;

public class ROC extends Application {
	private static Logger log = LogManager.getLogger(ROC.class);

	private ExploreAction exploreAction = ExploreAction.instance();

	public static void main(String[] args) {
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Settings.initialize();
		Server.init();
		KeysHandler.init();

		while (RUNNING) {
			if (EXPLORING) {
				exploreAction.start();
			}
		}

		log.log(Level.INFO, "Process ends here");
	}
}

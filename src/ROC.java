import actions.ExploreAction;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.Server;
import settings.KeysHandler;
import settings.Settings;
import status.Status;

import static status.State.EXPLORING;
import static status.State.RUNNING;

public class ROC extends Application {
	private static Logger log = LogManager.getLogger(ROC.class);

	private Status currentStatus = Status.IDLE;
	private ExploreAction exploreAction = ExploreAction.instance();

	public static void main(String[] args) {
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		Settings.initialize();
		Server.init();
		KeysHandler.init();

		while (RUNNING) {
			checkIfIAmIdle();
			if (EXPLORING) {
				changeStatus(Status.EXPLORING);
				exploreAction.start();
			}

			if (currentStatus == Status.IDLE) Thread.sleep(1000);
		}

		log.log(Level.INFO, "Process ends here");
	}

	private void changeStatus(Status status) {
		currentStatus = status;
		log.log(Level.INFO, String.format("Change status to %s", status));
	}

	private void checkIfIAmIdle() {
		if (currentStatus != Status.IDLE && !EXPLORING) {
			changeStatus(Status.IDLE);
		}
	}
}

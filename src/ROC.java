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
import util.BotChecker;
import util.SessionChecker;

import static status.State.*;

public class ROC extends Application {
	private static Logger log = LogManager.getLogger(ROC.class);

	private BotChecker botChecker = new BotChecker();
	private SessionChecker sessionChecker = new SessionChecker();

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
			if (checkersStatusOK()) {
				checkIfIAmIdle();
				if (EXPLORING) {
					changeStatus(Status.EXPLORING);
					exploreAction.start();
				}

				if (CURRENT_STATUS == Status.IDLE) Thread.sleep(1000);
			}
		}

		log.log(Level.INFO, "Process ends here");

		changeStatus(Status.OFF);
	}

	private boolean checkersStatusOK() {
		if (botChecker.checking()) {
			if (CURRENT_STATUS != Status.ANTIBOT_PRESENT) {
				changeStatus(Status.ANTIBOT_PRESENT);
				log.log(Level.WARN, "Bot checker activated");
			}

			return false;
		}

		if (sessionChecker.checking()) {
			if (CURRENT_STATUS != Status.NO_SESSION) {
				changeStatus(Status.NO_SESSION);
				log.log(Level.WARN, "Other devise take the control.");
			}

			return false;
		}

		return true;
	}

	private void changeStatus(Status status) {
		if (CURRENT_STATUS != status) {
			CURRENT_STATUS = status;
			log.log(Level.INFO, String.format("Change status to %s", status));
		}
	}

	private void checkIfIAmIdle() {
		if (CURRENT_STATUS != Status.IDLE && !EXPLORING) {
			changeStatus(Status.IDLE);
		}
	}
}

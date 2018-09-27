import actions.ExploreAction;
import actions.util.UtilActions;
import images.Patterns;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.basics.HotkeyEvent;
import org.sikuli.basics.HotkeyListener;
import org.sikuli.script.*;
import server.Server;
import settings.KeysHandler;
import settings.Settings;
import status.Status;

import java.awt.*;

import static status.State.*;

public class ROC extends Application {
	private static Logger log = LogManager.getLogger(ROC.class);

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

			if (CURRENT_STATUS == Status.IDLE) Thread.sleep(1000);
		}

		log.log(Level.INFO, "Process ends here");

		changeStatus(Status.OFF);
	}

	private void changeStatus(Status status) {
		CURRENT_STATUS = status;
		log.log(Level.INFO, String.format("Change status to %s", status));
	}

	private void checkIfIAmIdle() {
		if (CURRENT_STATUS != Status.IDLE && !EXPLORING) {
			changeStatus(Status.IDLE);
		}
	}
}

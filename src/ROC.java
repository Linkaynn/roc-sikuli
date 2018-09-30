import actions.ExploreAction;
import com.github.sheigutn.pushbullet.Pushbullet;
import com.github.sheigutn.pushbullet.items.push.sendable.defaults.SendableLinkPush;
import com.github.sheigutn.pushbullet.items.push.sendable.defaults.SendableNotePush;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.Server;
import settings.KeysHandler;
import settings.Settings;
import status.ROCState;
import status.Status;
import util.BotChecker;
import util.SessionChecker;

import java.util.Map;

import static status.ROCState.*;

public class ROC extends Application {
	private static Logger log = LogManager.getLogger(ROC.class);

	private BotChecker botChecker = new BotChecker();
	private SessionChecker sessionChecker = new SessionChecker();

	private ExploreAction exploreAction = ExploreAction.instance();
	private Pushbullet pushbullet = new Pushbullet("<apikey>");

	public static void main(String[] args) {
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) throws InterruptedException {
		Settings.initialize();
		Server.init();
		KeysHandler.init();
		ROCState.init();

		while (doing(Status.RUNNING)) {
			if (checkersStatusOK()) {
				checkIfIAmIdle();
				if (doing(Status.EXPLORING)) {
					changeStatus(Status.EXPLORING);
					exploreAction.start();
				}

				if (CURRENT_STATUS == Status.IDLE) Thread.sleep(1000);
			}
		}

		log.log(Level.INFO, "Process ends here");

		changeStatus(Status.OFF);
	}

	private boolean doing(Status status) {
		Boolean doingIt = ROCState.CURRENT_DOING.get(status);
		return doingIt != null && doingIt;
	}

	private boolean checkersStatusOK() {
		if (botChecker.checking()) {
			if (CURRENT_STATUS != Status.ANTIBOT_PRESENT) {
				changeStatus(Status.ANTIBOT_PRESENT);
				pushbullet.sendPush(pushbullet, new SendableLinkPush("ROC Sikuli Control", "Antibot detected", "http://roc.servegame.com:4200"));
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
		boolean doingSomeThing = CURRENT_DOING.entrySet().stream().filter(statusBooleanEntry -> statusBooleanEntry.getKey() != Status.RUNNING).anyMatch(Map.Entry::getValue);

		if (CURRENT_STATUS != Status.IDLE && !doingSomeThing) {
			changeStatus(Status.IDLE);
		}
	}
}

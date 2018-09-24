package actions;

import actions.util.Action;
import images.Images;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Pattern;

public class ExploreAction extends Action {
	private static Logger log = LogManager.getLogger(ExploreAction.class);

	public static boolean EXPLORING = true;

	private int explorersAvailable = 0;

	@Override
	public boolean start() {
		if (!EXPLORING) return false;

		log.log(Level.INFO, "Starting exploring...");

		if (!centerInHome()) {
			log.log(Level.WARN, "Can not go home");
			return false;
		}

		if (!openCamp()) {
			log.log(Level.WARN, "Can not open the camp");
			return false;
		} else {
			log.log(Level.INFO, "Camp opened");
		}

		explorersAvailable = getExplorerAvailableCount();

		if (explorersAvailable == 0) {
			log.log(Level.INFO, "No explorers available");
			closeAllWindows();
			return false;
		} else {
			log.log(Level.INFO, String.format("%s explorers available", explorersAvailable));
		}

		if (!selectExplorer()) {
			log.log(Level.WARN, "Can not select the explorer");
			closeAllWindows();
			return false;
		}

		if (!sendExplorer()) {
			log.log(Level.WARN, "Can not send the explorer");
			closeAllWindows();
			return false;
		}

		explorersAvailable--;

		if (explorersAvailable > 0) {
			return start();
		}

		return true;
	}

	private boolean openCamp() {
		return click(Images.Explorer.CAMP, Images.Explorer.SPYGLASS);
	}

	private int getExplorerAvailableCount() {
		return screen.findAllList(Images.Explorer.EXPLORE_BUTTON).size();
	}

	private boolean selectExplorer() {
		boolean result = click(Images.Explorer.EXPLORE_BUTTON);
		sleep(0.5f);
		return result && click(Images.Explorer.EXPLORE_BUTTON);
	}

	private boolean sendExplorer() {
		return click(Images.Common.SEND_TROOPS);
	}

	@Override
	public boolean click(Pattern... images) {
		for (Pattern image : images) {
			if (!EXPLORING || !super.click(image)) return false;
		}

		return true;
	}

	private void closeAllWindows() {
		while (click(Images.Common.CLOSE_BUTTON)) {}
	}
}

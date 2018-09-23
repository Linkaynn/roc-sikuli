package actions.util;

import controls.Controller;
import images.Images;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Pattern;

public class CommonActions extends Controller {
	private static Logger log = LogManager.getLogger(CommonActions.class);

	public boolean goHome() {
		Pattern HOME = Images.Common.HOME;

		log.log(Level.INFO, "Going home");
		return !exists(HOME) || click(HOME);
	}

	public boolean goMap() {
		Pattern MAP = Images.Common.MAP;

		log.log(Level.INFO, "Going map");
		return !exists(MAP) || click(MAP);
	}

	public boolean centerInHome() {
		return goMap() && goHome();
	}
}

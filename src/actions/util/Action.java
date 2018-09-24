package actions.util;

import controls.Controller;
import images.Images;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;

public class CommonActions extends Controller {
	private static Logger log = LogManager.getLogger(CommonActions.class);

	public boolean goHome() {
		Pattern HOME = Images.Common.HOME;

		log.log(Level.INFO, "Going home");
		boolean success = !exists(HOME) || click(HOME);

		if (success) {
			zoomOut(5);
		}

		return success;
	}

	public void zoomOut(int levels) {
		Mouse.move(new Location(screen.w / 2, screen.h / 2));
		screen.keyDown(Key.CTRL);
		sleep(0.5f);
		Mouse.wheel(Mouse.WHEEL_DOWN, levels);
		screen.keyUp();
	}

	public boolean goMap() {
		Pattern MAP = Images.Common.MAP;

		log.log(Level.INFO, "Going map");
		return !exists(MAP) || click(MAP);
	}

	public boolean centerInHome() {
		return goMap() && goHome();
	}

	public void sleep(float secs) {
		try {
			Thread.sleep((long) (secs * 1000));
		} catch (InterruptedException e) {
			log.log(Level.ERROR, String.format("Error sleeping %s seconds", secs));
		}
	}
}

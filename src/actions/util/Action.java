package actions.util;

import controls.Controller;
import images.Patterns;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;

public abstract class Action extends Controller {
	private static Logger log = LogManager.getLogger(Action.class);

	public abstract boolean start();

	public boolean goHome() {
		Pattern HOME = Patterns.Common.HOME;

		log.log(Level.INFO, "Going home");
		boolean success = click(HOME);

		if (success) {
			zoomOut(5);
		}

		return !exists(HOME) || success;
	}

	public void zoomOut() {
		zoomOut(9);
		zoomOut(7);
	}

	public void zoomOut(int levels) {
		Mouse.move(new Location(screen.w / 2, screen.h / 2));
		screen.keyDown(Key.CTRL);
		sleep(0.5f);
		Mouse.wheel(Mouse.WHEEL_DOWN, levels);
		screen.keyUp();
	}

	public boolean goMap() {
		Pattern MAP = Patterns.Common.MAP;

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

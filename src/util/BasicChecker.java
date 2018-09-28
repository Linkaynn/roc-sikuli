package util;

import org.sikuli.script.Screen;

public class BasicChecker {
	protected Screen screen = Screen.getPrimaryScreen();

	protected boolean exists(Object object) {
		return screen.exists(object) != null;
	}
}

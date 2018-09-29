package util;

import images.Patterns;
import org.sikuli.script.Screen;

public class BotChecker {

	public boolean checking() {
		return Screen.getPrimaryScreen().exists(Patterns.Common.VERIFY_BUTTON) != null || Screen.getPrimaryScreen().exists(Patterns.Common.PUZZLE_SLIDER) != null;
	}
}

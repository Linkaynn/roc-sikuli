package controls;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class Controller {

	private Screen screen = Screen.getPrimaryScreen();

	public boolean click(String image) {
		try {
			screen.click(image);
		} catch (FindFailed findFailed) {
			findFailed.printStackTrace();
			return false;
		}
		return true;
	}

}

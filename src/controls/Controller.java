package controls;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class Controller {
	private static Logger log = LogManager.getLogger(Controller.class);

	private Screen screen = Screen.getPrimaryScreen();

	public boolean click(String image) {
		try {
			screen.click(image);
			log.log(Level.INFO, image + " was clicked.");
		} catch (Exception e) {
			log.log(Level.ERROR, "Click error");
			if (e.getMessage().contains("ImageMissing")) {
				log.log(Level.ERROR, "Image missing: " + image);
			}
			return false;
		}
		return true;
	}

}

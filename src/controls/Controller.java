package controls;

import controls.exceptions.ClickException;
import controls.exceptions.TextNotFoundException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class Controller {
	private static Logger log = LogManager.getLogger(Controller.class);

	protected Screen screen = Screen.getPrimaryScreen();

	public boolean click(Pattern image) {
		try {
			return click(image, false);
		} catch (ClickException ignored) {} // Never will be pass here

		return false;
	}

	public boolean click(Pattern image, boolean throwException) throws ClickException {
		try {
			screen.click(image);
			log.log(Level.INFO, image + " was clicked.");
		} catch (Exception e) {
			log.log(Level.ERROR, "Click error");
			if (e.getMessage().contains("ImageMissing")) {
				log.log(Level.ERROR, "Image missing: " + image);
			}
			if (throwException) {
				throw new ClickException();
			}
			return false;
		}
		return true;
	}

	public String read(Region region, String needle, float timeout) {
		try {
			return read(region, needle, timeout, false);
		} catch (TextNotFoundException ignored) {} // Never will be pass here

		return "";
	}

	public String read(Region region, String needle, float timeout, boolean throwException) throws TextNotFoundException {
		region.highlight(2);
		while (timeout > 0) {
			long start = System.currentTimeMillis();
			String text = region.text();
			if (text.contains(needle)) {
				return text;
			}
			timeout -= System.currentTimeMillis() - start;
		}

		if (throwException) {
			throw new TextNotFoundException();
		} else {
			log.log(Level.INFO, needle + " not found.");
			return "";
		}
	}

	protected boolean exists(Pattern image) {
		return screen.exists(image) != null;
	}
}

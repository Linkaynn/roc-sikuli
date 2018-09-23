package settings;

import images.Images;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.sikuli.script.ImagePath;

public class Settings {
	private static Logger log = LogManager.getLogger(Settings.class);

	public static void initialize() {
		PropertyConfigurator.configure("log4j.properties");
		Images.init();
		ImagePath.add("images/");

		log.log(Level.INFO, "Settings initialized");
	}
}

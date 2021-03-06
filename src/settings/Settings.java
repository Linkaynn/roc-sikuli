package settings;

import images.Patterns;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.sikuli.script.ImagePath;

public class Settings {
	private static Logger log = LogManager.getLogger(Settings.class);

	public static void initialize() {
		org.sikuli.basics.Settings.DebugLogs = false;
		PropertyConfigurator.configure("log4j.properties");
		Patterns.init();
		ImagePath.add("images/");

		log.log(Level.INFO, "Settings initialized");
	}
}

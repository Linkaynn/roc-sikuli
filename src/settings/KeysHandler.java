package settings;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.basics.HotkeyEvent;
import org.sikuli.basics.HotkeyListener;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;

import java.awt.*;

import static status.Status.EXPLORING;
import static status.Status.RUNNING;

public class KeysHandler {
	private static Logger log = LogManager.getLogger(KeysHandler.class);

	public static void init() {
		addExitHandler();
		addRunningHandler();
		addExploringToggleHandler();
		log.log(Level.INFO, "Added the handlers");
	}

	private static void addExploringToggleHandler() {
		Key.addHotkey(Key.F2, KeyModifier.CTRL, new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Toolkit.getDefaultToolkit().beep();
				EXPLORING = !EXPLORING;

				String message = EXPLORING ? "Exploring was activated" : "Exploring was deactivated";
				log.log(Level.INFO, message);
			}
		});
	}

	private static void addRunningHandler() {
		Key.addHotkey(Key.F1, KeyModifier.CTRL, new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Toolkit.getDefaultToolkit().beep();
				EXPLORING = false;
				RUNNING = false;

				log.log(Level.INFO, "The app was deactivated");
			}
		});
	}

	private static void addExitHandler() {
		Key.addHotkey("q", KeyModifier.CTRL, new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Toolkit.getDefaultToolkit().beep();
				log.log(Level.WARN, "The app was force quit");
				System.exit(0);
			}
		});
	}
}

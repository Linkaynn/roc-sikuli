package settings;

import actions.util.UtilActions;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.basics.HotkeyEvent;
import org.sikuli.basics.HotkeyListener;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import status.ROCState;
import status.Status;

import java.awt.*;

import static images.Patterns.*;
import static status.ROCState.*;

public class KeysHandler {
	private static final Screen primaryScreen = Screen.getPrimaryScreen();
	private static Logger log = LogManager.getLogger(KeysHandler.class);

	public static void init() {
		addBasicsHandler();
		addExitHandler();
		addExploringToggleHandler();
		addMapElementsSearchToggleHandler();
		log.log(Level.INFO, "Added the handlers");
	}

	private static void addBasicsHandler() {
		Key.addHotkey("z", KeyModifier.CTRL + KeyModifier.CMD, new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Toolkit.getDefaultToolkit().beep();
				new UtilActions().zoomOut();

				log.log(Level.INFO, "Zooming out...");
			}
		});
	}

	private static void addMapElementsSearchToggleHandler() {
		HotkeyListener hotkeyListener = new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Pattern pattern = null;
				switch (hotkeyEvent.keyCode) {
					case (int) 'G':
						pattern = Map.GOLD_SYMBOL;
						break;
					case (int) 'W':
						pattern = Map.WOOD_SYMBOL;
						break;
					case (int) 'R':
						pattern = Map.ROCK_SYMBOL;
						break;
					case (int) 'F':
						pattern = Map.FOOD_SYMBOL;
						break;
					case (int) 'D':
						pattern = Map.GEM_SYMBOL;
						break;
				}

				if (pattern != null) {
					Toolkit.getDefaultToolkit().beep();

					primaryScreen.findAllList(pattern).forEach(match -> match.highlight(1));

					log.log(Level.INFO, "Highlighting in map...");
				}
			}
		};

		Key.addHotkey("g", KeyModifier.ALT, hotkeyListener);
		Key.addHotkey("w", KeyModifier.ALT, hotkeyListener);
		Key.addHotkey("r", KeyModifier.ALT, hotkeyListener);
		Key.addHotkey("f", KeyModifier.ALT, hotkeyListener);
		Key.addHotkey("d", KeyModifier.ALT, hotkeyListener);
	}

	private static void addExploringToggleHandler() {
		Key.addHotkey(Key.F2, KeyModifier.CTRL, new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Toolkit.getDefaultToolkit().beep();
				boolean newValue = !CURRENT_DOING.get(Status.EXPLORING);

				CURRENT_DOING.put(Status.EXPLORING, newValue);

				String message = newValue ? "Exploring was activated" : "Exploring was deactivated";
				log.log(Level.INFO, message);
			}
		});
	}

	private static void addExitHandler() {
		Key.addHotkey("q", KeyModifier.CTRL + KeyModifier.ALT, new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Toolkit.getDefaultToolkit().beep();
				log.log(Level.WARN, "The app was force quit");
				try {
					Thread.sleep(1000);
					System.exit(0);
				} catch (InterruptedException ignored) {}
			}
		});
	}
}

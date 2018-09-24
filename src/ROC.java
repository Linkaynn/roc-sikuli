import actions.ExploreAction;
import javafx.application.Application;
import javafx.stage.Stage;
import org.sikuli.basics.HotkeyEvent;
import org.sikuli.basics.HotkeyListener;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import settings.Settings;

import java.awt.*;

import static actions.util.PlacesMovements.initializeMovements;

public class ROC extends Application{
	public static void main(String[] args)
	{
		Key.addHotkey("q", KeyModifier.CTRL, new HotkeyListener() {
			@Override
			public void hotkeyPressed(HotkeyEvent hotkeyEvent) {
				Toolkit.getDefaultToolkit().beep();
				System.exit(0);
			}
		});
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Settings.initialize();
//		initializeMovements();

		new ExploreAction().start();

		Toolkit.getDefaultToolkit().beep();
		Toolkit.getDefaultToolkit().beep();
	}
}

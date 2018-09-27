import images.ImageManager;
import images.Patterns;
import javafx.application.Application;
import javafx.stage.Stage;
import org.sikuli.script.Region;
import settings.KeysHandler;
import settings.Settings;

import java.awt.*;

public class Tester extends Application{
	public static void main(String[] args)
	{
		KeysHandler.init();
		Application.launch(Tester.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Settings.initialize();
		Patterns.init();

		while (true) {
			Region region = ImageManager.getRegion();
			if (region != null) {
				System.out.println(region.getRect());
				System.out.println(region.text());
			}
			System.out.println(Patterns.Common.TIMING_REGION.text());
		}
	}
}

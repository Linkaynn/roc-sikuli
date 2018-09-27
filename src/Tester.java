import images.Patterns;
import javafx.application.Application;
import javafx.stage.Stage;
import settings.KeysHandler;
import settings.Settings;

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
			System.out.println(Patterns.Common.TIMING_REGION.text());
		}
	}
}

import images.Patterns;
import javafx.application.Application;
import javafx.stage.Stage;
import org.sikuli.script.Screen;
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

		Screen.getPrimaryScreen().exists(Patterns.Explorer.SPYGLASS).highlight(3);
	}
}

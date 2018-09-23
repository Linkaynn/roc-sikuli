import images.Images;
import javafx.application.Application;
import javafx.stage.Stage;
import org.sikuli.script.Commands;
import settings.Settings;

public class ROC extends Application{
	public static void main(String[] args) {
		Settings.initialize();
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Commands.click(Images.Explorer.CAMPAMENT);
	}
}

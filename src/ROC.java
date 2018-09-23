import controls.Controller;
import images.Images;
import javafx.application.Application;
import javafx.stage.Stage;
import settings.Settings;

public class ROC extends Application{
	public static void main(String[] args) {
		Settings.initialize();
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) {
		new Controller().click(Images.Explorer.CAMPAMENT);
	}
}

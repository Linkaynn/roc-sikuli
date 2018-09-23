import javafx.application.Application;
import javafx.stage.Stage;
import movement.Move;
import org.sikuli.script.FindFailed;

public class ROC extends Application{
	public static void main(String[] args) {
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) throws FindFailed {
		Move.up();
		Move.down();
		Move.left();
		Move.right();
	}
}

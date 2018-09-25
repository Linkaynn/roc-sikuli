package main;

import actions.ExploreAction;
import javafx.application.Application;
import javafx.stage.Stage;
import settings.KeysHandler;
import settings.Settings;

public class ROC extends Application {
	public static boolean RUNNING = true;

	private ExploreAction exploreAction = ExploreAction.instance();

	public static void main(String[] args) {
		KeysHandler.init();
		Application.launch(ROC.class);
	}

	@Override
	public void start(Stage primaryStage) {
		Settings.initialize();

		while (RUNNING) {
			if (ExploreAction.EXPLORING) {
				exploreAction.start();
			}
		}
	}
}

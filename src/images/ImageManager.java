package images;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType;

public class ImageManager {
	private static Logger log = LogManager.getLogger(ImageManager.class);

	/**
	 * Make a screenshot if the not exist
	 */
	public static Pattern getImage(String filename) {
		String pathname = "images/" + filename + ".png";
		if (!new File(pathname).exists()) {
			log.log(Level.INFO, pathname + " not exist.");

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Take new picture");
			alert.setContentText(pathname + " not exist. Do you want a new picture?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					log.log(Level.INFO, "Getting image");


					Region region = null;
					while (region == null) {
						region = Screen.getPrimaryScreen().selectRegion("Select Area to capture as Image");
						new Alert(AlertType.WARNING, "Try again when you want").showAndWait();
					}
					ImageIO.write(region.getLastScreenImage().getImage(), "PNG", new File(pathname));
				} catch (IOException e) {
					log.log(Level.ERROR, "An error happened retrieving the image...");
					System.exit(1);
				}
			} else {
				log.log(Level.WARN, "User do not want to select anything, using " + filename + " will be crush");
			}
		}
		return new Pattern(pathname);
	}
}

package actions.util;

import controls.Move;
import controls.Movements;
import images.Images;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static controls.Movements.*;

public class PlacesMovements {
	private static Logger log = LogManager.getLogger(PlacesMovements.class);
	private static Movements[] DEFAULT_MOVEMENTS = new Movements[]{UP, DOWN, LEFT, RIGHT};

	public static ArrayList<Movements> CAMP_MOVEMENTS;

	public static void initializeMovements() {
		new CommonActions().centerInHome();

		if (CAMP_MOVEMENTS == null) {
			CAMP_MOVEMENTS = searchFor(new Pattern[] {Images.Explorer.CAMP});
		}
	}

	private static ArrayList<Movements> searchFor(Pattern[] images) {
		String imagesByName = Arrays.stream(images).map((Pattern::getFilename)).collect(Collectors.joining(","));
		log.log(Level.INFO, String.format("Looking for %s images: %s", images.length, imagesByName));

		ArrayList<Movements> movements = new ArrayList<>();
		Screen screen = Screen.getPrimaryScreen();
		Predicate<Pattern> imageExist = (image) -> screen.exists(image) != null;

		if (Arrays.stream(images).anyMatch(imageExist)) {
			return movements;
		}

		for (Movements movement : DEFAULT_MOVEMENTS) {
			Move.move(movement);
			if (Arrays.stream(images).anyMatch(imageExist)) {
				movements.add(movement);

				log.log(Level.INFO, String.format("Found! - %s", imagesByName));
				break;
			} else {
				Move.move(movement.reverse());
			}
		}

		return movements;
	}

}

package controls;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;

import java.awt.*;

public class Move {
	private static Logger log = LogManager.getLogger(Move.class);

	private static Screen PRIMARY_SCREEN = Screen.getPrimaryScreen();
	private static Location MIDDLE = calculateMiddle();

	private static Location calculateMiddle() {
		Rectangle bounds = Screen.getBounds(0);
		return new Location(bounds.width / 2, bounds.height / 2);
	}

	public static void up() throws FindFailed {
		Location up = new Location(MIDDLE.x, MIDDLE.y / 2);
		PRIMARY_SCREEN.dragDrop(up, MIDDLE);
		log.log(Level.INFO, String.format("Moving up from (%s, %s) to (%s, %s)", up.x, up.y, MIDDLE.x, MIDDLE.y));
	}

	public static void down() throws FindFailed {
		Location down = new Location(MIDDLE.x, MIDDLE.y + MIDDLE.y / 2);
		PRIMARY_SCREEN.dragDrop(down, MIDDLE);
		log.log(Level.INFO, String.format("Moving down from (%s, %s) to (%s, %s)", down.x, down.y, MIDDLE.x, MIDDLE.y));
	}

	public static void left() throws FindFailed {
		Location left = new Location(MIDDLE.x / 2, MIDDLE.y);
		PRIMARY_SCREEN.dragDrop(left, MIDDLE);
		log.log(Level.INFO, String.format("Moving left from (%s, %s) to (%s, %s)", left.x, left.y, MIDDLE.x, MIDDLE.y));
	}

	public static void right() throws FindFailed {
		Location right = new Location(MIDDLE.x + MIDDLE.x / 2, MIDDLE.y);
		PRIMARY_SCREEN.dragDrop(right, MIDDLE);
		log.log(Level.INFO, String.format("Moving right from (%s, %s) to (%s, %s)", MIDDLE.x +  MIDDLE.x / 2, MIDDLE.y, MIDDLE.x, MIDDLE.y));
	}
}

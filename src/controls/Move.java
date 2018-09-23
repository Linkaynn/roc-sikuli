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
	private static float OFFSET = 100f;

	private static Location calculateMiddle() {
		Rectangle bounds = Screen.getBounds(0);
		return new Location(bounds.width / 2, bounds.height / 2);
	}

	public static void up() {
		try {
			Location up = new Location(MIDDLE.x, MIDDLE.y - (MIDDLE.y / 2f) - OFFSET);
			PRIMARY_SCREEN.dragDrop(up, MIDDLE);
			log.log(Level.INFO, String.format("Moving up from (%s, %s) to (%s, %s)", up.x, up.y, MIDDLE.x, MIDDLE.y));
		} catch (FindFailed findFailed) {
			log.log(Level.ERROR, "Up movement could not be performed");
		}
	}

	public static void down() {
		try {
			Location down = new Location(MIDDLE.x, MIDDLE.y + (MIDDLE.y / 2f) + OFFSET);
			PRIMARY_SCREEN.dragDrop(down, MIDDLE);
			log.log(Level.INFO, String.format("Moving down from (%s, %s) to (%s, %s)", down.x, down.y, MIDDLE.x, MIDDLE.y));
		} catch (FindFailed findFailed) {
			log.log(Level.ERROR, "Down movement could not be performed");
		}
	}

	public static void left() {
		try {
			Location left = new Location(MIDDLE.x - (MIDDLE.x / 2f) - OFFSET, MIDDLE.y);
			PRIMARY_SCREEN.dragDrop(left, MIDDLE);
			log.log(Level.INFO, String.format("Moving left from (%s, %s) to (%s, %s)", left.x, left.y, MIDDLE.x, MIDDLE.y));
		} catch (FindFailed findFailed) {
			log.log(Level.ERROR, "Left movement could not be performed");
		}
	}

	public static void right() {
		try {
			Location right = new Location(MIDDLE.x + (MIDDLE.x / 2f) + OFFSET, MIDDLE.y);
			PRIMARY_SCREEN.dragDrop(right, MIDDLE);
			log.log(Level.INFO, String.format("Moving right from (%s, %s) to (%s, %s)", MIDDLE.x + MIDDLE.x / 2, MIDDLE.y, MIDDLE.x, MIDDLE.y));
		} catch (FindFailed findFailed) {
			log.log(Level.ERROR, "Right movement could not be performed");
		}
	}

	public static void move(Movements[] movements) {
		for (Movements movement : movements) {
			move(movement);
		}
	}

	public static void move(Movements movement) {
		switch (movement) {
			case UP:
				up();
				break;
			case DOWN:
				down();
				break;
			case LEFT:
				left();
				break;
			case RIGHT:
				right();
				break;
		}
	}
}

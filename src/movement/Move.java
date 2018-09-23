package movement;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;

import java.awt.*;

public class Move {
	private static Screen PRIMARY_SCREEN = Screen.getPrimaryScreen();

	private static Location calculateMiddle() {
		Rectangle bounds = Screen.getBounds(0);
		return new Location(bounds.width / 2, bounds.height / 2);
	}

	public static void up() throws FindFailed {
		Location middle = calculateMiddle();
		PRIMARY_SCREEN.dragDrop(new Location(middle.x, middle.y / 2), middle);
	}

	public static void down() throws FindFailed {
		Location middle = calculateMiddle();
		PRIMARY_SCREEN.dragDrop(new Location(middle.x, middle.y + middle.y / 2), middle);
	}

	public static void left() throws FindFailed {
		Location middle = calculateMiddle();
		PRIMARY_SCREEN.dragDrop(new Location(middle.x / 2, middle.y), middle);
	}

	public static void right() throws FindFailed {
		Location middle = calculateMiddle();
		PRIMARY_SCREEN.dragDrop(new Location(middle.x +  middle.x / 2, middle.y), middle);
	}
}

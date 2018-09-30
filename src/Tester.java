import com.github.sheigutn.pushbullet.Pushbullet;
import com.github.sheigutn.pushbullet.items.device.Device;
import com.github.sheigutn.pushbullet.items.push.sendable.defaults.SendableLinkPush;
import com.github.sheigutn.pushbullet.items.push.sendable.defaults.SendableNotePush;
import images.ImageManager;
import images.Patterns;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.*;
import server.Server;
import settings.KeysHandler;
import settings.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class Tester extends Application {
	private static Logger log = LogManager.getLogger(Tester.class);

	private static ArrayList<Point> points = new ArrayList<>();
	private static Region region;
	private static BufferedImage bufferedImage;
	private static Point min;
	private static Point max;

	public static void main(String[] args) {
		Application.launch(Tester.class);
	}

	@Override
	public void start(Stage primaryStage) throws AWTException, InterruptedException {
		Settings.initialize();
		Server.init();
		KeysHandler.init();

		Pushbullet pushbullet = new Pushbullet("o.thcFZ9AN5s80EnbmhUHhgcqnQPLpFYJZ");

		pushbullet.sendPush(pushbullet, new SendableLinkPush("ROC Sikuli Control", "Antibot detected", "http://roc.servegame.com:4200"));
//		Robot robot = new Robot();
//
//		region = ImageManager.getRegion();
//
//		if (region != null) {
//			bufferedImage = Screen.getPrimaryScreen().capture(region).getImage();
//
//			points = new ArrayList<>();
//			for (int i = 0; i < region.w; i++) {
//				for (int j = 0; j < region.h; j++) {
//					int[] color = getPixelARGB(bufferedImage, i, j);
//
//					if (Math.abs((getHue(color[0], color[1], color[2]) - 60)) < 10) {
//						points.add(new Point(i, j));
//					}
//				}
//			}
//		}
//
//		Toolkit.getDefaultToolkit().beep();
//
//		Point topLeft = new Point(0, 0);
//
//		min = new Point();
//		points.stream().min(Comparator.comparingDouble(o2 -> distance(topLeft, o2))).ifPresent(point -> min = point);
//
//		max = new Point();
//		points.stream().max(Comparator.comparingDouble(o2 -> distance(topLeft, o2))).ifPresent(point -> max = point);
//
//		saveImage();

//			Patterns.Regions.FIRST_PART_PUZZLE.getCol(1, 2).highlight(1);
//			Patterns.Regions.FIRST_PART_PUZZLE.findAllList(Patterns.Verifier.YELLOW_ZONE).stream().max(Comparator.comparing(Match::getScore)).ifPresent(match -> match.highlight(1));
//			Patterns.Regions.SECOND_PART_PUZZLE.findAllList(Patterns.Verifier.TEST).forEach(match -> match.highlight(1));
//			Region region = ImageManager.getRegion();
//			if (region != null) {
//				System.out.println(region.getRect());
//				System.out.println(region.text());
//			}
////			System.out.println(Patterns.Common.TIMING_REGION.text());
	}

	public static int[] getPixelARGB(BufferedImage image, int i, int j) {
		int pixel = 0;
		try {
			pixel = image.getRGB(i, j);
		} catch (Exception e) {
			System.out.println(bufferedImage.getWidth() + " " + bufferedImage.getHeight());
			System.out.println(i + " " + j);
		}
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
//		log.log(Level.DEBUG, "argb: " + alpha + ", " + red + ", " + green + ", " + blue);

		return new int[]{red, green, blue};
	}

	public static int getHue(int red, int green, int blue) {

		float min = Math.min(Math.min(red, green), blue);
		float max = Math.max(Math.max(red, green), blue);

		if (min == max) {
			return 0;
		}

		float hue = 0f;
		if (max == red) {
			hue = (green - blue) / (max - min);

		} else if (max == green) {
			hue = 2f + (blue - red) / (max - min);

		} else {
			hue = 4f + (red - green) / (max - min);
		}

		hue = hue * 60;
		if (hue < 0) hue = hue + 360;

		return Math.round(hue);
	}

	public static void saveImage() {
		int width = max.x - min.x;
		int height = max.y - min.y;

		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D graphics2D = image.createGraphics();
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, width, height);

		for (int i = 0; i < max.x; i++) {
			for (int j = 0; j < max.y; j++) {
				int[] color = getPixelARGB(bufferedImage, i + min.x, j + min.y);
				graphics2D.setPaint(new Color(color[0], color[1], color[2]));
				graphics2D.drawRect(i, j, 1, 1);
			}
		}

		graphics2D.dispose();

		try {
			ImageIO.write(image, "png", new File("images/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static double distance(Point first, Point other) {
		Point result = new Point();
		result.y = Math.abs(first.y - other.y);
		result.x = Math.abs(first.x - other.x);
		return Math.sqrt((result.y) * (result.y) + (result.x) * (result.x));
	}

}


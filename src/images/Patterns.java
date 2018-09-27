package images;

import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

public class Patterns {
	public static void init() {
		Explorer.init();
		Common.init();
		Map.init();
		Test.init();
	}

	public static class Explorer  {
		public static Pattern CAMP;
		public static Pattern SPYGLASS;
		public static Pattern EXPLORE_BUTTON;

		static void init() {
			CAMP = ImageManager.getImage("camp").similar(0.5);
			SPYGLASS = ImageManager.getImage("spyglass");
			EXPLORE_BUTTON = ImageManager.getImage("explore_button");
		}
	}

	public static class Test {
		public static Pattern WINDOWS;


		static void init() {
			WINDOWS = ImageManager.getImage("windows");
		}
	}

	public static class Map {
		public static Pattern GOLD_SYMBOL;
		public static Pattern WOOD_SYMBOL;
		public static Pattern ROCK_SYMBOL;
		public static Pattern FOOD_SYMBOL;
		public static Pattern GEM_SYMBOL;

		public static void init() {
			GOLD_SYMBOL = ImageManager.getImage("gold_symbol").similar(0.8);
			WOOD_SYMBOL = ImageManager.getImage("wood_symbol").similar(0.8);
			ROCK_SYMBOL = ImageManager.getImage("rock_symbol").similar(0.8);
			FOOD_SYMBOL = ImageManager.getImage("food_symbol").similar(0.8);
			GEM_SYMBOL = ImageManager.getImage("gem_symbol").similar(0.8);
		}
	}

	public static class Common {
		public static Pattern CLOSE_BUTTON;
		public static Pattern HOME;
		public static Pattern MAP;
		public static Pattern SEND_TROOPS;
		public static Pattern VERIFY_BUTTON;
		public static Region TIMING_REGION;

		public static void init() {
			HOME = ImageManager.getImage("home");
			MAP = ImageManager.getImage("map");
			CLOSE_BUTTON = ImageManager.getImage("close_button");
			SEND_TROOPS = ImageManager.getImage("send_troops");
			VERIFY_BUTTON = ImageManager.getImage("verify_button");
			TIMING_REGION = new Region(1476, 206, 70, 27);
		}
	}
}

package images;

import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

public class Patterns {
	public static void init() {
		Explorer.init();
		Common.init();
		Map.init();
		Verifier.init();
		Regions.init();
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

	public static class Regions {
		public static Region TIMING_REGION;
		public static Region FIRST_PART_PUZZLE;
		public static Region SECOND_PART_PUZZLE;
		public static Region OTHER_DEVISE_SESSION_REGION;

		public static void init() {
			FIRST_PART_PUZZLE = new Region(754, 347, 96, 219);
			SECOND_PART_PUZZLE = new Region(838, 332, 319, 226);
			TIMING_REGION = new Region(1262, 443, 81, 30);
			OTHER_DEVISE_SESSION_REGION = new Region(631, 478, 620, 34);
		}
	}

	public static class Verifier {
		public static Pattern YELLOW_ZONE;
		public static Pattern TEST;
		public static Pattern DARK_ZONE;

		public static void init() {
			YELLOW_ZONE = ImageManager.getImage("yellow_zone").similar(0.45);
			DARK_ZONE = ImageManager.getImage("dark_zone").similar(0.6);
			TEST = ImageManager.getImage("test").similar(0.3);
		}
	}

	public static class Common {
		public static Pattern CLOSE_BUTTON;
		public static Pattern HOME;
		public static Pattern MAP;
		public static Pattern SEND_TROOPS;
		public static Pattern VERIFY_BUTTON;
		public static Pattern CONFIRM_BUTTON;
		public static Pattern ROC_ICON;

		public static void init() {
			HOME = ImageManager.getImage("home");
			MAP = ImageManager.getImage("map");
			CLOSE_BUTTON = ImageManager.getImage("close_button");
			SEND_TROOPS = ImageManager.getImage("send_troops");
			VERIFY_BUTTON = ImageManager.getImage("verify_button");
			CONFIRM_BUTTON = ImageManager.getImage("confirm_button");
			ROC_ICON = ImageManager.getImage("roc_icon");
		}
	}
}

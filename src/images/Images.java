package images;

import org.sikuli.script.Pattern;

public class Images{
	public static void init() {
		Explorer.init();
		Common.init();
		Test.init();
	}

	public static class Explorer  {
		public static Pattern CAMP;
		public static Pattern SPYGLASS;
		public static Pattern EXPLORE_BUTTON;

		static void init() {
			CAMP = ImageManager.getImage("camp").similar(0.6);
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

	public static class Common {
		public static Pattern CLOSE_BUTTON;
		public static Pattern HOME;
		public static Pattern MAP;
		public static Pattern SEND_TROOPS;

		public static void init() {
			HOME = ImageManager.getImage("home");
			MAP = ImageManager.getImage("map");
			CLOSE_BUTTON = ImageManager.getImage("close_button");
			SEND_TROOPS = ImageManager.getImage("send_troops");
		}
	}
}

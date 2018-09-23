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
		public static Pattern EXPLORE_BUTTON;

		static void init() {
			CAMP = ImageManager.getImage("camp").similar(0.6d);
			EXPLORE_BUTTON = ImageManager.getImage("exploreButton");
		}
	}

	public static class Test {
		public static Pattern WINDOWS;


		static void init() {
			WINDOWS = ImageManager.getImage("windows");
		}
	}

	public static class Common {
		public static Pattern HOME;
		public static Pattern MAP;

		public static void init() {
			HOME = ImageManager.getImage("home");
			MAP = ImageManager.getImage("map");
		}
	}
}

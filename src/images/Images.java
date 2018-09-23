package images;

public class Images{
	public static void init() {
		Explorer.init();
		Test.init();
	}

	public static class Explorer  {
		public static String CAMP;
		public static String EXPLORE_BUTTON;

		static void init() {
			CAMP = ImageManager.getImage("camp");
			EXPLORE_BUTTON = ImageManager.getImage("exploreButton");
		}
	}

	public static class Test {
		public static String WINDOWS;


		static void init() {
			WINDOWS = ImageManager.getImage("windows");
		}
	}
}

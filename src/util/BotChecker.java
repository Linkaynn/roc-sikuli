package util;

import images.Patterns;

public class BotChecker {

	private String text = "";

	public boolean checking() {
		String text = Patterns.Common.TIMING_REGION.text().replace("\n", "");

		boolean checked = text.contains(":") && text.split(":").length == 2 && areNumbers(text.split(":"));

		if (checked) this.text = text;

		return checked;
	}

	private boolean areNumbers(String[] split) {
		try {
			Integer.parseInt(split[0]);
			Integer.parseInt(split[1]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String getText() {
		return text;
	}
}

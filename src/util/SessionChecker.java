package util;

import images.Patterns;

public class SessionChecker extends BasicChecker{

	public boolean checking() {
		String text = Patterns.Regions.OTHER_DEVISE_SESSION_REGION.text().replace("\n", "");

		return (text.contains("Ya has iniciado") && text.contains("dispositivo.")) || exists(Patterns.Common.CONFIRM_BUTTON);
	}
}

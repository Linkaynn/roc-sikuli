package server.endpoints;

import actions.util.UtilActions;
import images.Patterns;
import io.javalin.Context;
import io.javalin.Handler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class InputHandler extends BaseHandler implements Handler {
	private static Logger log = LogManager.getLogger(InputHandler.class);

	@Override
	public void handle(Context context) throws Exception {
		this.context = context;
		String action = context.queryParam("action");

		if (action != null) {
			UtilActions utilActions = new UtilActions();

			if (action.equals("new_session")) {
				utilActions.click(false, Patterns.Common.CONFIRM_BUTTON);
				Thread.sleep(1000);
				utilActions.click(false, Patterns.Common.ROC_ICON);
			}
		}

		ok("");
	}
}

package server.endpoints;

import actions.util.UtilActions;
import images.Patterns;
import io.javalin.Context;
import io.javalin.Handler;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

enum Actions {
	CLICK_CONFIRM, MOVE_PIECE
}

public class InputHandler extends BaseHandler implements Handler {
	private static Logger log = LogManager.getLogger(InputHandler.class);

	@Override
	public void handle(Context context) throws Exception {
		this.context = context;
		String actionRaw = context.queryParam("action");

		if (actionRaw != null) {
			Actions action = Actions.valueOf(actionRaw.toUpperCase());

			UtilActions utilActions = new UtilActions();

			switch (action) {
				case MOVE_PIECE:
					String posRaw = context.queryParam("pos");

					try {
						Match puzzleMatch = Screen.getPrimaryScreen().find(Patterns.Common.PUZZLE_SLIDER);

						if (posRaw != null && puzzleMatch != null) {
							utilActions.dragAndDrop(puzzleMatch, new Location(Integer.parseInt(posRaw), puzzleMatch.y));
						}
					} catch (FindFailed e) {
						log.log(Level.ERROR, "Cannot move the piece");
						log.log(Level.ERROR, e);
					}
					break;
				case CLICK_CONFIRM:
					utilActions.click(false, Patterns.Common.CONFIRM_BUTTON);
					Thread.sleep(1000);
					utilActions.click(false, Patterns.Common.ROC_ICON);
					break;
				default:
					log.log(Level.WARN, String.format("%s action do not exist", action));
			}

			ok("");
		}

		error(404);
	}

}

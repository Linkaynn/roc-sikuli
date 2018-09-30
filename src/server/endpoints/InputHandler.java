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
import status.ROCState;
import status.Status;

enum Actions {
	CLICK_CONFIRM, MOVE_PIECE, CHANGE_STATE, CLICK_VERIFY
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
				case CHANGE_STATE:
					String statusRaw = context.queryParam("status");
					String checkedRaw = context.queryParam("checked");

					if (statusRaw != null && checkedRaw != null) {
						Status status = Status.valueOf(statusRaw.toUpperCase());
						boolean checked = Boolean.valueOf(checkedRaw);

						log.log(Level.INFO, String.format("Now you %s %s", checked ? "are": "are NOT", status.toString().toLowerCase()));
						ROCState.CURRENT_DOING.put(status, checked);
					} else {
						error(405);
					}
					break;
				case MOVE_PIECE:
					movePiece(context, utilActions);
					break;
				case CLICK_VERIFY:
					utilActions.click(Patterns.Common.VERIFY_BUTTON);
					break;
				case CLICK_CONFIRM:
					utilActions.click(Patterns.Common.CONFIRM_BUTTON);
					Thread.sleep(1000);
					utilActions.click(Patterns.Common.ROC_ICON);
					break;
				default:
					log.log(Level.WARN, String.format("%s action do not exist", action));
			}

			ok("");

			return;
		}

		error(404);
	}

	private void movePiece(Context context, UtilActions utilActions) {
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
	}

}

package status;

import java.util.Date;
import java.util.HashMap;

import static status.Status.EXPLORING;
import static status.Status.RUNNING;

public class ROCState {
	public static final long START_PROGRAM_TIME = new Date().getTime() - 5000;

	public static Status CURRENT_STATUS = Status.IDLE;
	public static HashMap<Status, Boolean> CURRENT_DOING = new HashMap<>();

	public static void init() {
		CURRENT_DOING.put(RUNNING, true);
		CURRENT_DOING.put(EXPLORING, false);
	}
}

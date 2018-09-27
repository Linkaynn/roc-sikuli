package status;

import java.util.Date;

public class State {
	public static final long START_PROGRAM_TIME = new Date().getTime() - 5000;

	public static Status CURRENT_STATUS = Status.IDLE;
	public static boolean RUNNING = true;
	public static boolean EXPLORING = false;
}

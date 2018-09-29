package server.endpoints.lightweight;

import status.Status;

import java.util.HashMap;
import java.util.List;

public class ROCStateLight {
	private Status currentStatus;
	private List<String> logLines;
	private StatisticsLight statistics;
	private EnvironmentInfoLight environmentInfo;
	private HashMap<Status, Boolean> currentDoing;

	public void setCurrentStatus(Status currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setLogLines(List<String> logLines) {
		this.logLines = logLines;
	}

	public void setStatistics(StatisticsLight statistics) {
		this.statistics = statistics;
	}

	public void setEnvironmentInfo(EnvironmentInfoLight environmentInfo) {
		this.environmentInfo = environmentInfo;
	}

	public void setCurrentDoing(HashMap<Status, Boolean> currentDoing) {
		this.currentDoing = currentDoing;
	}
}

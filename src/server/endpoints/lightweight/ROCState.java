package server.endpoints.lightweight;

import status.Status;

import java.util.List;

public class ROCState {
	private Status currentStatus;
	private List<String> logLines;
	private StatisticsLight statistics;
	private EnvironmentInfoLight environmentInfo;

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
}

package server.endpoints;

import io.javalin.Context;
import io.javalin.Handler;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Screen;
import server.endpoints.lightweight.EnvironmentInfoLight;
import server.endpoints.lightweight.ROCStateLight;
import server.endpoints.lightweight.StatisticsLight;
import status.ROCState;
import status.Statistics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StatusHandler extends BaseHandler implements Handler {
	private static Logger log = LogManager.getLogger(StatusHandler.class);

	@Override
	public void handle(Context context) {
		this.context = context;

		ROCStateLight state = new ROCStateLight();

		state.setCurrentStatus(ROCState.CURRENT_STATUS);
		setStatistics(state);
		setEnvironmentInfo(state);
		state.setCurrentDoing(ROCState.CURRENT_DOING);
		retrieveLogLines(state);

		ok(new OkMessage(state));
	}

	private void setEnvironmentInfo(ROCStateLight state) {
		Rectangle bounds = Screen.getPrimaryScreen().getBounds();

		EnvironmentInfoLight environmentInfoLight = new EnvironmentInfoLight();
		environmentInfoLight.setScreenWidth(bounds.width);
		environmentInfoLight.setScreenHeight(bounds.height);
		state.setEnvironmentInfo(environmentInfoLight);
	}

	private void setStatistics(ROCStateLight state) {
		StatisticsLight statistics = new StatisticsLight();
		statistics.setExploredTimes(Statistics.EXPLORED_TIMES);
		state.setStatistics(statistics);
	}

	private void retrieveLogLines(ROCStateLight state) {
		try {
			List<String> logLines = Files.lines(Paths.get("ROC.log"))
					.filter(line -> {
						Matcher matcher = Pattern.compile("\\[(.*)]").matcher(line);

						if (matcher.find()) {
							String match = matcher.group(0);
							Date date;

							try {
								date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(match.substring(1, match.length() - 5));
							} catch (ParseException ignored) {
								return false;
							}

							return date.getTime() > ROCState.START_PROGRAM_TIME;
						}

						return false;
					}).collect(Collectors.toList());


			state.setLogLines(logLines);
		} catch (IOException e) {
			log.log(Level.ERROR, "Error recovering the log.");
			log.log(Level.ERROR, "", e);
		}
	}
}

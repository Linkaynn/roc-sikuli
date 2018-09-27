package server.endpoints;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import server.endpoints.lightweight.ROCState;
import status.State;
import status.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StatusHandler extends BaseHandler implements HttpHandler {
	private static Logger log = LogManager.getLogger(StatusHandler.class);

	class OkMessage {
		Object data;

		public OkMessage(Object object) {
			data = object;
		}
	}

	@Override
	public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		this.response = response;

		ROCState state = new ROCState();

		state.setCurrentStatus(State.CURRENT_STATUS);
		retrieveLogLines(state);

		ok(new OkMessage(state));
	}

	private void retrieveLogLines(ROCState state) {
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

							return date.getTime() > State.START_PROGRAM_TIME;
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

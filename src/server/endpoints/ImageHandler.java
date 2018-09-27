 package server.endpoints;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.Screen;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import server.endpoints.lightweight.ROCState;
import server.endpoints.lightweight.StatisticsLight;
import status.State;
import status.Statistics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

public class ImageHandler extends BaseHandler implements HttpHandler {
	private static Logger log = LogManager.getLogger(ImageHandler.class);

	class OkMessage {
		Object data;

		public OkMessage(Object object) {
			data = object;
		}
	}

	@Override
	public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		this.response = response;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(Screen.getPrimaryScreen().capture().getImage(), "png", baos);
			baos.flush();
			sendFile(baos.toByteArray());
			baos.close();
		} catch (IOException ignored) {}
	}
}

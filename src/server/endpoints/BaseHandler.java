package server.endpoints;

import com.google.gson.Gson;
import io.javalin.Context;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

class ContentTypes {
	protected static final String JSON = "text/json";
	protected static final String JPEG = "image/jpeg";
}

public class BaseHandler {

	Gson gson = new Gson();
	Context context;

	protected void error(int errorCode) {
		addHeaders(ContentTypes.JSON).status(errorCode).result("");
	}

	class OkMessage {
		Object data;

		public OkMessage(Object object) {
			data = object;
		}
	}

	private Context addHeaders(String contentType) {
		context.header("Access-Control-Allow-Origin", "*");
		context.header("Content-Type", contentType);

		return context;
	}

	protected void ok(Object object) {
		addHeaders("text/json").status(200).result(gson.toJson(object));
	}

	protected void sendFile(BufferedImage image) {
		addHeaders(ContentTypes.JPEG);

		try {
			ServletOutputStream baos = context.res.getOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			baos.close();
		} catch (IOException ignored) {}
	}


	private String toJson(Object object) {
		return gson.toJson(object);
	}
}

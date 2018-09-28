package server.endpoints;

import com.google.gson.Gson;
import io.javalin.Context;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BaseHandler {

	Gson gson = new Gson();
	Context context;

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
		addHeaders("text/json").result(gson.toJson(object));
	}

	protected void sendFile(BufferedImage image) {
		addHeaders("image/jpeg");

		try {
			ServletOutputStream baos = context.res.getOutputStream();
			ImageIO.write(image, "jpg", baos);
			baos.flush();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private String toJson(Object object) {
		return gson.toJson(object);
	}
}

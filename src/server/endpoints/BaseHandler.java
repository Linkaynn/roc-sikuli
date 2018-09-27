package server.endpoints;

import com.google.gson.Gson;
import org.webbitserver.HttpResponse;

public class BaseHandler {

	Gson gson = new Gson();
	HttpResponse response;

	private HttpResponse addHeaders(String contentType) {
		return response
				.header("Access-Control-Allow-Origin", "*")
				.header("Content-Type", contentType);
	}

	protected void ok(Object object) {
		addHeaders("text/json").content(toJson(object)).end();
	}

	protected void sendFile(byte[] image) {
		addHeaders("image/png").content(image).end();
	}


	private String toJson(Object object) {
		return gson.toJson(object);
	}
}

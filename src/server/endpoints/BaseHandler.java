package server.endpoints;

import com.google.gson.Gson;
import org.webbitserver.HttpResponse;

public class BaseHandler {

	Gson gson = new Gson();
	HttpResponse response;

	private HttpResponse addHeaders() {
		return response
				.header("Access-Control-Allow-Origin", "*")
				.header("Content-Type", "text/json");
	}

	protected void ok(Object object) {
		addHeaders().content(toJson(object)).end();
	}

	private String toJson(Object object) {
		return gson.toJson(object);
	}
}

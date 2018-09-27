package server.endpoints;

import com.google.gson.Gson;
import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;
import status.State;
import status.Status;

public class StatusHandler extends BaseHandler implements HttpHandler {
	class OkMessage {
		Object data;

		public OkMessage(Object object) {
			data = object;
		}
	}

	@Override
	public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) {
		this.response = response;

		ok(new OkMessage(State.CURRENT_STATUS));
	}
}

package endpoint.encoders;

import com.google.gson.Gson;
import endpoint.model.Message;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

	private static Gson gson = new Gson();

	public String encode(Message message) {
		return gson.toJson(message);
	}

	public void init(EndpointConfig endpointConfig) {
		// Custom initialization logic
	}

	public void destroy() {
		// Close resources
	}
}

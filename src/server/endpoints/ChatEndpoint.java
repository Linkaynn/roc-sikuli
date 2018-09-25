package server.endpoints;

import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;
import status.Status;

public class ChatEndpoint extends BaseWebSocketHandler {
	private int connectionCount;

	public void onOpen(WebSocketConnection connection) {
		connection.send("Hello! There are " + connectionCount + " other connections active");
		connectionCount++;
	}

	public void onClose(WebSocketConnection connection) {
		connectionCount--;
	}

	public void onMessage(WebSocketConnection connection, String message) {
		connection.send(message.toUpperCase()); // echo back message in upper case
	}
}
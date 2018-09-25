package server;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import org.webbitserver.handler.StaticFileHandler;
import server.endpoints.ChatEndpoint;

import java.util.concurrent.ExecutionException;

public class Server {
	private static Logger log = LogManager.getLogger(Server.class);

	public static void init() {
		try {
			WebServer webServer = WebServers.createWebServer(8080)
					.add("/hellowebsocket", new ChatEndpoint())
					.add(new StaticFileHandler("front-end")) // path to web content
					.start().get();
			log.log(Level.INFO, String.format("Server started at %s", webServer.getUri()));
		} catch (InterruptedException | ExecutionException e) {
			log.log(Level.ERROR, "Error starting server", e);
		}
	}
}

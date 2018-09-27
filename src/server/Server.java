package server;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import server.endpoints.StatusHandler;

import java.util.concurrent.ExecutionException;

public class Server {
	private static Logger log = LogManager.getLogger(Server.class);

	public static void init() {
		try {
			WebServer webServer = WebServers.createWebServer(8080)
					.add("/api/status", new StatusHandler())
					.start().get();
			log.log(Level.INFO, String.format("Server started at %s", webServer.getUri()));
		} catch (InterruptedException | ExecutionException e) {
			log.log(Level.ERROR, "Error starting server", e);
		}
	}
}

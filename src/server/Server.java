package server;

import io.javalin.Javalin;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import server.endpoints.ImageHandler;
import server.endpoints.InputHandler;
import server.endpoints.StatusHandler;

public class Server {
	public static final int PORT = 8080;
	public static final Javalin server = Javalin.create().start(PORT);
	private static Logger log = LogManager.getLogger(Server.class);

	public static void init() {
		server
				.get("/api/status", new StatusHandler())
				.get("/api/last-image", new ImageHandler())
				.get("/api/input", new InputHandler());

		log.log(Level.INFO, String.format("Server started at %s", 8080));
	}
}

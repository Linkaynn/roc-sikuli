 package server.endpoints;

 import io.javalin.Context;
 import io.javalin.Handler;
 import org.apache.log4j.LogManager;
 import org.apache.log4j.Logger;
 import org.sikuli.script.Screen;

 public class ImageHandler extends BaseHandler implements Handler {
	private static Logger log = LogManager.getLogger(ImageHandler.class);

	 @Override
	 public void handle(Context context) {
		 this.context = context;
		 sendFile(Screen.getPrimaryScreen().capture().getImage());
	 }
}

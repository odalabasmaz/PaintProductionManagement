package tr.com.ppm.desktop;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import tr.com.ppm.desktop.view.LoginView;

/**
 * @author Orhun Dalabasmaz
 */
@Lazy
@SpringBootApplication
public class App extends AbstractJavaFxApplicationSupport {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOGGER.info("Application started.");
		launchApp(App.class, LoginView.class, args);
		LOGGER.info("Application terminated.");
	}

}

package tr.com.ppm.desktop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Orhun Dalabasmaz
 */
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	private Application() {
	}

	/**
	 * $ java -jar -Dtarget.env=dev ppm.desktop-1.0-SNAPSHOT.jar
	 */
	public static void main(String... args) {
		// start
		LOGGER.info("Application started.");
	}
}

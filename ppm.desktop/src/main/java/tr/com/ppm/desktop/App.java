package tr.com.ppm.desktop;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import tr.com.ppm.desktop.view.LoginView;
import tr.com.ppm.desktop.view.ViewManager;

/**
 * @author Orhun Dalabasmaz
 */
@Lazy
@SpringBootApplication
public class App extends Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		LOGGER.info("Application started.");
		applicationContext = SpringApplication.run(App.class, args);
		Application.launch(App.class, args);
		LOGGER.info("Application terminated.");
	}

	@Override
	public void start(Stage stage) throws Exception {
		ViewManager.init(stage, applicationContext);
		ViewManager.openPage(LoginView.class);
	}
}

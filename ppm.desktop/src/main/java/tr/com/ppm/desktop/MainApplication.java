package tr.com.ppm.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tr.com.ppm.desktop.service.HelloWorldService;

import java.io.IOException;

/**
 * @author Orhun Dalabasmaz
 */
@SpringBootApplication
public class MainApplication extends Application implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

	@Autowired
	private HelloWorldService service;

	/**
	 * $ java -jar -Dtarget.env=dev ppm.desktop-1.0-SNAPSHOT.jar
	 */
	public static void main(String... args) {
		LOGGER.info("Application started.");
		SpringApplication.run(MainApplication.class, args);
		LOGGER.info("Application terminated.");
	}

	@Override
	public void run(String... args) {
		LOGGER.info(this.service.getHelloMessage());
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
		primaryStage.setTitle("Boya Üretim Yönetimi");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}

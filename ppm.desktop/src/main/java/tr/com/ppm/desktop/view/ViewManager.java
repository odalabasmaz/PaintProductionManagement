package tr.com.ppm.desktop.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * @author Orhun Dalabasmaz
 */
public class ViewManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewManager.class);
	private static Stage stage;
	private static Scene scene;
	private static ApplicationContext applicationContext;

	private ViewManager() {
	}

	public static void init(Stage stage, ApplicationContext applicationContext) {
		ViewManager.stage = stage;
		ViewManager.stage.initStyle(StageStyle.UNDECORATED);
		ViewManager.applicationContext = applicationContext;
	}

	public static void openPage(Class<? extends AbstractFxmlView> newView) {
		assert applicationContext != null : "application context must not be null!";
		assert stage != null : "stage must not be null!";

		LOGGER.info("Opening page: {}", newView.getCanonicalName());
		AbstractFxmlView view = applicationContext.getBean(newView);
		stage.titleProperty().bind(view.titleProperty());
		Parent parentView = null;
		try {
			parentView = view.getView(applicationContext);
		} catch (IOException e) {
			//todo: ex handling for unfound page
			LOGGER.error("Page cannot be loaded.", e);
		}

		if (scene == null) {
			scene = new Scene(parentView, 1000, 600);
		} else {
			scene.setRoot(parentView);
		}

		stage.setScene(scene);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public static void openPopup(Class<? extends AbstractFxmlView> newView) {
		Stage stage = new Stage();
		AbstractFxmlView view = applicationContext.getBean(newView);
		stage.titleProperty().bind(view.titleProperty());
		Parent parentView = null;
		try {
			parentView = view.getView(applicationContext);
		} catch (IOException e) {
			//todo: ex handling for unfound page
			LOGGER.error("Page cannot be loaded.", e);
		}

		stage.setScene(new Scene(parentView, 300, 300));
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

}

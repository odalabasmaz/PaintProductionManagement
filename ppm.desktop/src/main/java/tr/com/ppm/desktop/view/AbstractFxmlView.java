package tr.com.ppm.desktop.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import tr.com.ppm.desktop.controller.EditViewController;
import tr.com.ppm.desktop.model.common.AuditableEntity;

import java.io.IOException;
import java.net.URL;

/**
 * @author Orhun Dalabasmaz
 */
public abstract class AbstractFxmlView {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFxmlView.class);
	private StringProperty title = new SimpleStringProperty();
	private ApplicationContext applicationContext;

	public Parent getView(ApplicationContext applicationContext) throws IOException {
		this.applicationContext = applicationContext;
		FXMLLoader fxmlLoader = new FXMLLoader(getResource());
		fxmlLoader.setControllerFactory(this::createControllerForType);
		return fxmlLoader.load();
	}

	public Parent getView(ApplicationContext applicationContext, AuditableEntity entity) throws IOException {
		this.applicationContext = applicationContext;
		FXMLLoader fxmlLoader = new FXMLLoader(getResource());
		fxmlLoader.setControllerFactory(this::createControllerForType);
		Parent load = fxmlLoader.load();
		EditViewController controller = fxmlLoader.getController();
		controller.updateEditView(entity);
		return load;
	}

	private Object createControllerForType(Class<?> type) {
		return this.applicationContext.getBean(type);
	}

	private URL getResource() {
		FXMLView fxmlAnnotation = this.getClass().getAnnotation(FXMLView.class);
		return getClass().getResource(fxmlAnnotation.value());
	}

	protected void setTitle(String title) {
		this.title.setValue(title);
	}

	public StringProperty titleProperty() {
		return title;
	}
}

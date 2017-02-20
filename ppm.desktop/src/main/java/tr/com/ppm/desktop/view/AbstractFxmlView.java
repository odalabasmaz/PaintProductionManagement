package tr.com.ppm.desktop.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * @author Orhun Dalabasmaz
 */
public abstract class AbstractFxmlView {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFxmlView.class);
	private StringProperty title = new SimpleStringProperty();

	public Parent getView() throws IOException {
		return FXMLLoader.load(getResource());
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

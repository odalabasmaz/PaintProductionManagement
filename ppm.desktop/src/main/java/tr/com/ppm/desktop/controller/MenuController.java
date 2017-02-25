package tr.com.ppm.desktop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.view.PaintTypeView;
import tr.com.ppm.desktop.view.RawMaterialView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class MenuController implements Initializable {

	@FXML
	void openRawMaterial(ActionEvent event) {
		ViewManager.openPage(RawMaterialView.class);
	}

	@FXML
	void openProduct(ActionEvent event) {
		ViewManager.openPage(RawMaterialView.class);
	}

	@FXML
	void openPaintType(ActionEvent event) {
		ViewManager.openPage(PaintTypeView.class);
	}

	@FXML
	void openPaintSubType(ActionEvent event) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void exit(ActionEvent actionEvent) {
		Platform.exit();
	}
}

package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.view.RawMaterialView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class MainController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void openRawMaterial(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
		ViewManager.openPage(RawMaterialView.class);
	}
}

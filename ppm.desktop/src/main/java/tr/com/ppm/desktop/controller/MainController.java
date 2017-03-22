package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class MainController implements Initializable {

	@FXML
	private MenuItem menuItemProduct;

	@FXML
	private MenuItem menuItemRawMaterial;

	@FXML
	private MenuItem menuItemPaintType;

	@FXML
	private MenuItem menuItemPaintSubtype;

	@FXML
	private Button btnClose;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void btnCloseOnClick(ActionEvent event) {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}
}

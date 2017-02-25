package tr.com.ppm.desktop.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

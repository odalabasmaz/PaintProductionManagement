package tr.com.ppm.desktop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.view.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class MenuController implements Initializable {

	private double xOffset = 0;
	private double yOffset = 0;

	@FXML
	void openRawMaterial(ActionEvent event) {
		ViewManager.openPage(RawMaterialView.class);
	}

	@FXML
	void openProduct(ActionEvent event) {
		ViewManager.openPage(ProductView.class);
	}

	@FXML
	void openPaintType(ActionEvent event) {
		ViewManager.openPage(PaintTypeView.class);
	}

	@FXML
	void openCustomer(ActionEvent event) {
		ViewManager.openPage(CustomerView.class);
	}

	@FXML
	void openPaintSubType(ActionEvent event) {
		ViewManager.openPage(PaintSubtypeView.class);
	}

	@FXML
	void openOrder(ActionEvent event) {
		ViewManager.openPage(OrderView.class);
	}

	public void exit(ActionEvent actionEvent) {
		Platform.exit();
	}

	@FXML
	void onMouseDragged(MouseEvent event) {
		Window window = ((Node) (event.getSource())).getScene().getWindow();
		window.setX(event.getScreenX() - xOffset);
		window.setY(event.getScreenY() - yOffset);
	}

	@FXML
	void onMousePressed(MouseEvent event) {
		xOffset = event.getSceneX();
		yOffset = event.getSceneY();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

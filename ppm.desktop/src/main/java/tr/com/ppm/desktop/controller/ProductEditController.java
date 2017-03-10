package tr.com.ppm.desktop.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class ProductEditController implements Initializable {

	@Autowired
	private PaintTypeService paintTypeService;

	@Autowired
	private PaintSubTypeService paintSubTypeService;

	@FXML
	private ComboBox<PaintType> cbPaintType;

	@FXML
	private ComboBox<PaintSubType> cbPaintSubtype;

	@FXML
	void add(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbPaintType.setItems(FXCollections.observableArrayList(paintTypeService.list()));
		cbPaintSubtype.setItems(FXCollections.observableArrayList(paintSubTypeService.list()));
	}

}

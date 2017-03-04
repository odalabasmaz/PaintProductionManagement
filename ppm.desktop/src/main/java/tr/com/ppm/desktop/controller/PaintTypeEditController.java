package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintTypeService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class PaintTypeEditController implements Initializable {

	@Autowired
	private PaintTypeService service;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField tfPaintType;

	@FXML
	void add(ActionEvent event) {
		service.save(new PaintType(tfPaintType.getText()));
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

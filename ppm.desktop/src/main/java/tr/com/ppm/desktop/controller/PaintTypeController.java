package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.service.PaintTypeService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class PaintTypeController implements Initializable {

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnEdit;
	@FXML
	private Button btnQuery;

	@Autowired
	private PaintTypeService service;

	@FXML
	void add(ActionEvent event) {
	}

	@FXML
	void delete(ActionEvent event) {
		System.out.println("Delete");
	}

	@FXML
	void edit(ActionEvent event) {
//		ViewManager.openPage(PaintTypeEditView.class);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

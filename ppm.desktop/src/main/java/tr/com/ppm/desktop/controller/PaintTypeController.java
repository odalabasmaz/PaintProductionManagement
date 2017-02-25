package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class PaintTypeController implements Initializable {

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	void add(ActionEvent event) {
		System.out.println("Add");
	}

	@FXML
	void edit(ActionEvent event) {
		System.out.println("Edit");
	}

	@FXML
	void delete(ActionEvent event) {
		System.out.println("Delete");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

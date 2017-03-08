package tr.com.ppm.desktop.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.State;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class RawMaterialEditController implements Initializable {
	@FXML
	private TextField tfCode;

	@FXML
	private Button btnClose;

	@FXML
	private ComboBox<State> cbState;

	@FXML
	private Button btnAdd;

	@FXML
	private TextArea taDiscription;

	@FXML
	private TextField tfName;

	@FXML
	private Label lblHeader;

	@FXML
	void add(ActionEvent event) {
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbState.setItems(FXCollections.observableArrayList(State.values()));
	}
}

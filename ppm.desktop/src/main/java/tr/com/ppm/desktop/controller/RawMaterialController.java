package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.State;
import tr.com.ppm.desktop.model.material.RawMaterial;
import tr.com.ppm.desktop.view.RawMaterialEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class RawMaterialController implements Initializable {
	@FXML
	private TextField tfCode;

	@FXML
	private Button btnClean;

	@FXML
	private Button btnDelete;

	@FXML
	private ComboBox<State> cbState;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private TextField tfName;

	@FXML
	private Button btnQuery;

	@FXML
	private TableView<RawMaterial> tvRawMaterial;

	@FXML
	private TableColumn<RawMaterial, String> tcName;

	@FXML
	private TableColumn<RawMaterial, String> tcCode;

	@FXML
	private TableColumn<RawMaterial, String> tcState;

	@FXML
	private TableColumn<RawMaterial, String> tcDescription;

	@FXML
	void query(ActionEvent event) {

	}

	private void query() {

	}

	@FXML
	void clean(ActionEvent event) {

	}

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(RawMaterialEditView.class, this::query);
	}

	@FXML
	void edit(ActionEvent event) {

	}

	@FXML
	void delete(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbState.setItems(FXCollections.observableArrayList(State.values()));
		tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		tcCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
		tcState.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState()));
		tcDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
	}
}

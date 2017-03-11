package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.State;
import tr.com.ppm.desktop.model.material.RawMaterial;
import tr.com.ppm.desktop.service.RawMaterialService;
import tr.com.ppm.desktop.view.RawMaterialEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class RawMaterialController implements Initializable {

	@Autowired
	RawMaterialService service;

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
	private TableColumn<RawMaterial, State> tcState;

	@FXML
	private TableColumn<RawMaterial, Long> tcStock;

	@FXML
	private TableColumn<RawMaterial, String> tcDescription;

	@FXML
	void query(ActionEvent event) {
		query();
	}

	private void query() {
		String code = tfCode.getText();
		String name = tfName.getText();
		State state = cbState.getSelectionModel().getSelectedItem();
		List<RawMaterial> rawMaterials = service.queryWithParameter(code, name, state);
		tvRawMaterial.setItems(FXCollections.observableArrayList(rawMaterials));
	}

	@FXML
	void clean(ActionEvent event) {
		cbState.getSelectionModel().select(null);
		tfCode.setText(null);
		tfName.setText(null);
	}

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(RawMaterialEditView.class, this::query);
	}

	@FXML
	public void delete(ActionEvent event) {
		RawMaterial rawMaterial = tvRawMaterial.getSelectionModel().getSelectedItem();
		service.remove(rawMaterial);
		query();
	}

	@FXML
	public void edit(ActionEvent event) {
		RawMaterial selectedItem = tvRawMaterial.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ViewManager.openPopup(RawMaterialEditView.class, this::query, selectedItem);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbState.setItems(FXCollections.observableArrayList(State.values()));
		tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		tcCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
		tcState.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getState()));
		tcStock.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStock()));
		tcDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
	}
}

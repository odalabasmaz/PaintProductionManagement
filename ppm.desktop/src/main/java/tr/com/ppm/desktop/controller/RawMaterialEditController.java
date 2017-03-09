package tr.com.ppm.desktop.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.State;
import tr.com.ppm.desktop.model.material.RawMaterial;
import tr.com.ppm.desktop.service.RawMaterialService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class RawMaterialEditController implements Initializable, EditViewController<RawMaterial> {

	@Autowired
	RawMaterialService rawMaterialService;

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
	private TextField tfStock;

	@FXML
	private Label lblHeader;

	private RawMaterial rawMaterial;
	private ViewMode viewMode = ViewMode.NEW;

	@FXML
	void add(ActionEvent event) {
		if (viewMode == ViewMode.NEW) {
			RawMaterial rawMaterial = new RawMaterial(tfCode.getText(), tfName.getText(), taDiscription.getText(), cbState.getSelectionModel().getSelectedItem(), tfStock.getText());
			rawMaterialService.save(rawMaterial);
		} else if (viewMode == ViewMode.EDIT) {
			this.rawMaterial.setName(tfName.getText());
			this.rawMaterial.setCode(tfCode.getText());
			this.rawMaterial.setStock(Long.parseLong(tfStock.getText()));
			this.rawMaterial.setDescription(taDiscription.getText());
			this.rawMaterial.setState(cbState.getSelectionModel().getSelectedItem());
			rawMaterialService.update(this.rawMaterial);
		}
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbState.setItems(FXCollections.observableArrayList(State.values()));
		viewMode = ViewMode.NEW;
	}

	@Override
	public void updateEditView(RawMaterial rawMaterial) {
		if (rawMaterial != null) {
			this.rawMaterial = rawMaterial;
			this.viewMode = ViewMode.EDIT;
			cbState.getSelectionModel().select(rawMaterial.getState());
			tfCode.setText(rawMaterial.getCode());
			tfName.setText(rawMaterial.getName());
			taDiscription.setText(rawMaterial.getDescription());
		}
	}

}

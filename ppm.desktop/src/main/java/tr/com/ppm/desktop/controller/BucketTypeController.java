package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.Container;
import tr.com.ppm.desktop.model.material.BucketType;
import tr.com.ppm.desktop.service.BucketTypeService;
import tr.com.ppm.desktop.view.BucketTypeEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author uadak.
 */
@Component
public class BucketTypeController implements Initializable {

	@Autowired
	private BucketTypeService service;

	@FXML
	private ComboBox<Container> cbBucketUnit;

	@FXML
	private Button btnQuery;

	@FXML
	private Button btnClean;

	@FXML
	private TextField tfBucketSize;

	@FXML
	private TableView<BucketType> tvBucketType;

	@FXML
	private TableColumn<BucketType, String> tcBucketSize;

	@FXML
	private TableColumn<BucketType, String> tcBucketUnit;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(BucketTypeEditView.class, this::query);
	}

	@FXML
	void clean(ActionEvent event) {
		tfBucketSize.clear();
		cbBucketUnit.getSelectionModel().clearSelection();
	}

	@FXML
	void delete(ActionEvent event) {
		BucketType paintType = tvBucketType.getSelectionModel().getSelectedItem();
		service.remove(paintType);
		listAll();
	}

	@FXML
	void edit(ActionEvent event) {
		BucketType selectedItem = tvBucketType.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ViewManager.openPopup(BucketTypeEditView.class, this::query, selectedItem);
		}
	}

	@FXML
	void query(ActionEvent event) {
		query();
	}

	private void query() {
		String paintType = tfBucketSize.getText();
		List<BucketType> bucketTypes = service.listByPaintType(paintType);
		tvBucketType.setItems(FXCollections.observableArrayList(bucketTypes));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcBucketSize.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBucket_size()));
	//TODO: YK yapacak
		tcBucketUnit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBucket_size()));
		listAll();
	}

	private void listAll() {
		tvBucketType.setItems(FXCollections.observableArrayList(service.list()));
	}

}

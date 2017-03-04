package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.view.PaintSubtypeEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class PaintSubtypeController implements Initializable {

	@Autowired
	private PaintSubTypeService paintSubTypeService;

	@Autowired
	private PaintTypeService paintTypeService;

	@FXML
	private Button btnClean;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnEdit;

	@FXML
	private ComboBox<PaintType> cbPaintType;

	@FXML
	private ComboBox<PaintSubType> cbPaintSubtype;

	@FXML
	private TableView<PaintSubType> tvPaintSubtype;

	@FXML
	private TableColumn<PaintSubType, String> tcPaintType;

	@FXML
	private TableColumn<PaintSubType, String> tcPaintSubtype;

	@FXML
	private Button btnQuery;

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(PaintSubtypeEditView.class, this::query);
	}

	@FXML
	public void delete(ActionEvent event) {
		PaintSubType paintSubType = tvPaintSubtype.getSelectionModel().getSelectedItem();
		paintSubTypeService.remove(paintSubType);
		listAll();
	}

	@FXML
	public void edit(ActionEvent event) {
//		ViewManager.openPage(PaintSubtypeEditView.class);
	}

	@FXML
	public void query(ActionEvent event) {
		query();
	}

	private void query() {
		//todo query with conditions
		List<PaintSubType> paintSubTypes = paintSubTypeService.list();
		tvPaintSubtype.setItems(FXCollections.observableArrayList(paintSubTypes));
	}

	@FXML
	public void clean(ActionEvent event) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbPaintType.setItems(FXCollections.observableArrayList(paintTypeService.list()));
		cbPaintSubtype.setItems(FXCollections.observableArrayList(paintSubTypeService.list()));
		tcPaintType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaintType().getName()));
		tcPaintSubtype.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		listAll();
	}

	private void listAll() {
		tvPaintSubtype.setItems(FXCollections.observableArrayList(paintSubTypeService.list()));
	}
}
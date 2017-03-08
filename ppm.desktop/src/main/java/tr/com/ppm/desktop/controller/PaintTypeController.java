package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.view.PaintTypeEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.List;
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
	@FXML
	private Button btnClean;
	@FXML
	private TextField tfPaintType;
	@FXML
	private TableView<PaintType> tblViewPaintType;
	@FXML
	private TableColumn<PaintType, String> tblColumnPaintType;

	@Autowired
	private PaintTypeService service;

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(PaintTypeEditView.class, this::query);
	}

	@FXML
	public void delete(ActionEvent event) {
		PaintType paintType = tblViewPaintType.getSelectionModel().getSelectedItem();
		service.remove(paintType);
		listAll();
	}

	@FXML
	public void edit(ActionEvent event) {
		PaintType selectedItem = tblViewPaintType.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ViewManager.openPopup(PaintTypeEditView.class, this::query, selectedItem);
		}
	}

	@FXML
	public void query(ActionEvent event) {
		query();
	}

	private void query() {
		String paintType = tfPaintType.getText();
		List<PaintType> paintTypes = service.listByPaintType(paintType);
		tblViewPaintType.setItems(FXCollections.observableArrayList(paintTypes));
	}

	@FXML
	public void clean(ActionEvent event) {
		tfPaintType.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblColumnPaintType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		listAll();
	}

	private void listAll() {
		tblViewPaintType.setItems(FXCollections.observableArrayList(service.list()));
	}
}

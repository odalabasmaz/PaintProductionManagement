package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.view.PaintTypeEditView;
import tr.com.ppm.desktop.view.ViewManager;

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
	private TableView<PaintType> tblViewPaintType;

	@FXML
	private TableColumn<PaintType, String> tblColumnPaintType;

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(PaintTypeEditView.class);
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
		tblColumnPaintType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		tblViewPaintType.setItems(FXCollections.observableArrayList(service.list()));
	}
}

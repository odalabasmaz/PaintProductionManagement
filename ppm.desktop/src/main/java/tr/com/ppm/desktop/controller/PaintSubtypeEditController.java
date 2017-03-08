package tr.com.ppm.desktop.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class PaintSubtypeEditController implements Initializable, EditViewController<PaintSubType> {

	@Autowired
	private PaintTypeService paintTypeService;

	@Autowired
	private PaintSubTypeService paintSubTypeService;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField tfPaintSubType;

	@FXML
	private ComboBox<PaintType> cbPaintType;

	@FXML
	private Label lblHeader;

	private PaintSubType paintSubType;

	private ViewMode viewMode;

	@FXML
	void add(ActionEvent event) {
		if (this.viewMode == ViewMode.NEW) {
			paintSubTypeService.save(new PaintSubType(tfPaintSubType.getText(), cbPaintType.getValue()));
		} else if (this.viewMode == ViewMode.EDIT) {
			this.paintSubType.setName(tfPaintSubType.getText());
			this.paintSubType.setPaintType(cbPaintType.getValue());
			paintSubTypeService.update(this.paintSubType);
		}
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.viewMode = ViewMode.NEW;
		cbPaintType.setItems(FXCollections.observableArrayList(paintTypeService.list()));
	}

	@Override
	public void updateEditView(PaintSubType paintSubType) {
		if (paintSubType != null) {
			this.paintSubType = paintSubType;
			this.viewMode = ViewMode.EDIT;
			cbPaintType.getSelectionModel().select(paintSubType.getPaintType());
			tfPaintSubType.setText(paintSubType.getName());
		}
	}
}

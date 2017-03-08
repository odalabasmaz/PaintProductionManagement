package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.PaintTypeService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class PaintTypeEditController implements Initializable, EditViewController<PaintType> {

	@Autowired
	private PaintTypeService service;

	@FXML
	private Button btnClose;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField tfPaintType;

	private ViewMode viewMode;

	private PaintType paintType;

	@FXML
	void add(ActionEvent event) {
		if(this.viewMode==ViewMode.NEW){
			service.save(new PaintType(tfPaintType.getText()));
		}else if(this.viewMode==ViewMode.EDIT){
			this.paintType.setName(tfPaintType.getText());
			service.update(this.paintType);
		}
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewMode = ViewMode.NEW;
	}

	@Override
	public void updateEditView(PaintType paintType) {
		if (paintType != null) {
			this.paintType= paintType;
			tfPaintType.setText(paintType.getName());
			viewMode = ViewMode.EDIT;
		}
	}
}

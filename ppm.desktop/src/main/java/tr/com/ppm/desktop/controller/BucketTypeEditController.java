package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.BucketType;
import tr.com.ppm.desktop.service.BucketTypeService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author uadak.
 */
@Component
public class BucketTypeEditController implements Initializable, EditViewController<BucketType> {

	@Autowired
	private BucketTypeService service;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnClose;

	@FXML
	private TextField tfBucketSize;

	@FXML
	private ComboBox<BucketType> cbBucketUnit;

	private ViewMode viewMode;

	private BucketType bucketType;

	@FXML
	void add(ActionEvent event) {
		if (this.viewMode == ViewMode.NEW) {
			service.save(new BucketType(tfBucketSize.getText(), tfBucketSize.getText()));
		} else if (this.viewMode == ViewMode.EDIT) {
			this.bucketType.setBucket_size(tfBucketSize.getText());
			this.bucketType.setBucket_unit(tfBucketSize.getText());
			service.update(this.bucketType);
		}
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void updateEditView(BucketType entity) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

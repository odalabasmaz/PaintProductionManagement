package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.view.RawMaterialEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class ProductController implements Initializable {

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfCode;

	@FXML
	private TextField tfColorName;

	@FXML
	private TextField tfColorCode;

	@FXML
	private ComboBox<PaintType> cbPaintType;

	@FXML
	private ComboBox<PaintSubType> cbPaintSubtype;

	@FXML
	private TextField tfDensity;

	@FXML
	private TableView<Product> tvProduct;

	@FXML
	private TableColumn<Product, String> tcDensity;

	@FXML
	private TableColumn<Product, String> tcPaintType;

	@FXML
	private TableColumn<Product, String> tcColorCode;

	@FXML
	private TableColumn<Product, String> tcPaintSubtype;

	@FXML
	private TableColumn<Product, String> tcName;

	@FXML
	private TableColumn<Product, String> tcColor;

	@FXML
	private TableColumn<Product, String> tcCode;

	@FXML
	private TableColumn<Product, String> tcDescription;

	@FXML
	void query(ActionEvent event) {
		query();
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
	public void delete(ActionEvent event) {

	}

	@FXML
	public void edit(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

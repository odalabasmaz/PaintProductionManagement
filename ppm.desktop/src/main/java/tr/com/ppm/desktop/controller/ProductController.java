package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.service.ProductService;
import tr.com.ppm.desktop.view.ProductEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class ProductController implements Initializable {

	@Autowired
	private ProductService productService;
	@Autowired
	private PaintTypeService paintTypeService;
	@Autowired
	private PaintSubTypeService paintSubTypeService;

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
	private ComboBox<PaintSubType> cbPaintSubType;

	@FXML
	private TableView<Product> tvProduct;
	@FXML
	private TableColumn<Product, String> tcName;
	@FXML
	private TableColumn<Product, String> tcCode;
	@FXML
	private TableColumn<Product, String> tcColor;
	@FXML
	private TableColumn<Product, String> tcColorCode;
	@FXML
	private TableColumn<Product, String> tcPaintType;
	@FXML
	private TableColumn<Product, String> tcPaintSubtype;
	@FXML
	private TableColumn<Product, String> tcDensity;
	@FXML
	private TableColumn<Product, String> tcDescription;

	@FXML
	void query(ActionEvent event) {
		query();
	}

	private void query() {
		String name = tfName.getText();
		String code = tfCode.getText();
		String colorName = tfColorName.getText();
		String colorCode = tfColorCode.getText();
		PaintType paintType = cbPaintType.getSelectionModel().getSelectedItem();
		PaintSubType paintSubType = cbPaintSubType.getSelectionModel().getSelectedItem();

		List<Product> products = productService.list(name, code, colorName, colorCode, paintType, paintSubType);
		tvProduct.setItems(FXCollections.observableArrayList(products));
	}

	@FXML
	void clean(ActionEvent event) {
		tfCode.clear();
		tfName.clear();
		tfColorCode.clear();
		tfColorName.clear();
	}

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(ProductEditView.class, this::query);
	}

	@FXML
	public void delete(ActionEvent event) {

	}

	@FXML
	public void edit(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		tcCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCode()));
		tcColor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColorCode()));
		tcColorCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColorCode()));
		tcPaintType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaintSubType().getPaintType().toString()));
		tcPaintSubtype.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaintSubType().toString()));
		tcDensity.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDensity())));
		tcDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
		cbPaintType.setItems(FXCollections.observableArrayList(paintTypeService.list()));
		cbPaintSubType.setItems(FXCollections.observableArrayList(paintSubTypeService.list()));
	}
}

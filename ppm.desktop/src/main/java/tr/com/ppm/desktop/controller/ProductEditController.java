package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.Quantity;
import tr.com.ppm.desktop.model.material.Material;
import tr.com.ppm.desktop.model.material.PaintSubType;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.model.production.Ingredient;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.service.ProductService;
import tr.com.ppm.desktop.service.RawMaterialService;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author ykarabalkan
 */
@Component
public class ProductEditController implements Initializable {

	@Autowired
	private PaintTypeService paintTypeService;

	@Autowired
	private RawMaterialService rawMaterialService;

	@Autowired
	private PaintSubTypeService paintSubTypeService;

	@Autowired
	private ProductService productService;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfCode;

	@FXML
	private TextField tfColorName;

	@FXML
	private TextField tfColorCode;

	@FXML
	private TextField tfDensity;

	@FXML
	private TextArea taDescription;

	@FXML
	private ComboBox<PaintType> cbPaintType;

	@FXML
	private ComboBox<PaintSubType> cbPaintSubtype;

	@FXML
	private TableView<Ingredient> tvRawMaterial;

	@FXML
	private TableColumn<Ingredient, Material> tcRawMaterial;

	@FXML
	private TableColumn<Ingredient, String> tcRwAmount;

	@FXML
	private TableView<Ingredient> tvIntermediateProduct;

	@FXML
	private TableColumn<Ingredient, Material> tcIntermediateProduct;

	@FXML
	private TableColumn<Ingredient, Quantity> tcIProductAmount;

	@FXML
	private RadioButton rbAraUrun;

	@FXML
	private RadioButton rbAnaUrun;

	@FXML
	private HBox rawMaterialBox;

	@FXML
	private HBox intermediateProductBox;

	@FXML
	void add(ActionEvent event) {
		String name = tfName.getText();
		String code = tfCode.getText();
		String colorName = tfColorName.getText();
		String colorCode = tfColorCode.getText();
		String description = taDescription.getText();
		double density = Double.parseDouble(tfDensity.getText());
		boolean preIntermediateProduct = false;
		Set<Ingredient> ingredientSet = new HashSet<>();
		ingredientSet.addAll(tvRawMaterial.getItems());
		ingredientSet.addAll(tvIntermediateProduct.getItems());
		PaintSubType paintSubType = cbPaintSubtype.getSelectionModel().getSelectedItem();
		Product product = new Product(name, code, description, 0, colorName, colorCode, density, preIntermediateProduct, ingredientSet, paintSubType);
		productService.save(product);

		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void addRawMaterial(ActionEvent event) {
		ObservableList<Ingredient> items = tvRawMaterial.getItems();
		Ingredient ingredient = new Ingredient();
		ingredient.setQuantity(new Quantity(0.0));
		items.add(ingredient);
		tcRawMaterial.setEditable(true);
		tcRwAmount.setEditable(true);
	}

	@FXML
	void deleteRawMaterial(ActionEvent event) {
		int selectedIndex = tvRawMaterial.getSelectionModel().getSelectedIndex();

		if (selectedIndex > -1) {
			tvRawMaterial.getItems().remove(selectedIndex);
		}
	}

	@FXML
	void addIntermediateProduct(ActionEvent event) {
	}

	@FXML
	void deleteIntermediateProduct(ActionEvent event) {
	}

	@FXML
	void anaUrunSelected(ActionEvent event) {
		rawMaterialBox.setVisible(true);
	}

	@FXML
	void araUrunSelected(ActionEvent event) {
		rawMaterialBox.setVisible(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbPaintType.setItems(FXCollections.observableArrayList(paintTypeService.list()));
		cbPaintSubtype.setItems(FXCollections.observableArrayList(paintSubTypeService.list()));
		tcRwAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity().getAmount())));
		tcRwAmount.setCellFactory(TextFieldTableCell.forTableColumn());
		tcRawMaterial.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMaterial()));
		tvRawMaterial.setEditable(true);

		tcRawMaterial.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(rawMaterialService.list())));
		tcRawMaterial.setOnEditCommit((TableColumn.CellEditEvent<Ingredient, Material> e) ->
				{
					// new value coming from combobox
					Material newValue = e.getNewValue();

					// index of editing row in the tableview
					int index = e.getTablePosition().getRow();

					Ingredient ing = e.getTableView().getItems().get(index);

					ing.setMaterial(newValue);
				}
		);

		tcRwAmount.setOnEditCommit((TableColumn.CellEditEvent<Ingredient, String> e) ->
				{
					// new value coming from combobox
					String newValue = e.getNewValue();

					// index of editing row in the tableview
					int index = e.getTablePosition().getRow();

					Ingredient ing = e.getTableView().getItems().get(index);

					ing.getQuantity().setAmount(Double.parseDouble(newValue));
				}
		);
	}

}

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
import tr.com.ppm.desktop.model.material.*;
import tr.com.ppm.desktop.model.production.Ingredient;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.service.ProductService;
import tr.com.ppm.desktop.service.RawMaterialService;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ykarabalkan
 */
@Component
public class ProductEditController implements Initializable, EditViewController<Product> {

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
	private TableColumn<Ingredient, String> tcRawMaterialAmount;

	@FXML
	private TableView<Ingredient> tvIntermediateProduct;

	@FXML
	private TableColumn<Ingredient, Material> tcIntermediateProduct;

	@FXML
	private TableColumn<Ingredient, String> tcIProductAmount;

	@FXML
	private RadioButton rbAraUrun;

	@FXML
	private RadioButton rbAnaUrun;

	@FXML
	private HBox rawMaterialBox;

	@FXML
	private HBox intermediateProductBox;

	private Product product;
	private EditViewController.ViewMode viewMode = EditViewController.ViewMode.NEW;

	@FXML
	void add(ActionEvent event) {
		String name = tfName.getText();
		String code = tfCode.getText();
		String colorName = tfColorName.getText();
		String colorCode = tfColorCode.getText();
		String description = taDescription.getText();
		double density = Double.parseDouble(tfDensity.getText());
		boolean preIntermediateProduct = rbAraUrun.isSelected();
		Set<Ingredient> ingredientSet = new HashSet<>();
		ingredientSet.addAll(tvRawMaterial.getItems());
		ingredientSet.addAll(tvIntermediateProduct.getItems());
		PaintSubType paintSubType = cbPaintSubtype.getSelectionModel().getSelectedItem();
		if (this.viewMode == EditViewController.ViewMode.NEW) {
			Product product = new Product(name, code, description, 0, colorName, colorCode, density, preIntermediateProduct, ingredientSet, paintSubType);
			productService.save(product);
		} else {
			this.product.setName(name);
			this.product.setCode(code);
			this.product.setColorName(colorName);
			this.product.setColorCode(colorCode);
			this.product.setDescription(description);
			this.product.setDensity(density);
			this.product.setIntermediateProduct(preIntermediateProduct);
			this.product.setPaintSubType(paintSubType);
			this.product.setIngredientSet(ingredientSet);
			productService.update(product);
		}

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
		tcRawMaterialAmount.setEditable(true);
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
		ObservableList<Ingredient> items = tvIntermediateProduct.getItems();
		Ingredient ingredient = new Ingredient();
		ingredient.setQuantity(new Quantity(0.0));
		items.add(ingredient);
		tcIntermediateProduct.setEditable(true);
		tcIProductAmount.setEditable(true);
	}

	@FXML
	void deleteIntermediateProduct(ActionEvent event) {
		int selectedIndex = tvIntermediateProduct.getSelectionModel().getSelectedIndex();

		if (selectedIndex > -1) {
			tvIntermediateProduct.getItems().remove(selectedIndex);
		}
	}

	@FXML
	void anaUrunSelected(ActionEvent event) {
		intermediateProductBox.setVisible(true);
	}

	@FXML
	void araUrunSelected(ActionEvent event) {
		intermediateProductBox.setVisible(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbPaintType.setItems(FXCollections.observableArrayList(paintTypeService.list()));

		cbPaintType.setOnAction((event) -> {
			PaintType paintType = cbPaintType.getSelectionModel().getSelectedItem();
			cbPaintSubtype.getSelectionModel().select(null);
			cbPaintSubtype.setItems(FXCollections.observableArrayList(paintSubTypeService.list("", paintType)));
		});

		tcRawMaterialAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity().getAmount())));
		tcRawMaterialAmount.setCellFactory(TextFieldTableCell.forTableColumn());
		tcRawMaterial.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMaterial()));
		tcRawMaterial.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(rawMaterialService.list())));
		tvRawMaterial.setEditable(true);

		tcRawMaterial.setOnEditCommit((TableColumn.CellEditEvent<Ingredient, Material> e) -> {
					materialChanged(e);
				}
		);

		tcRawMaterialAmount.setOnEditCommit((TableColumn.CellEditEvent<Ingredient, String> e) -> {
					amountChanged(e);
				}
		);

		tcIProductAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity().getAmount())));
		tcIProductAmount.setCellFactory(TextFieldTableCell.forTableColumn());
		tcIntermediateProduct.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMaterial()));
		tcIntermediateProduct.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(productService.listIntermediateProducts())));
		tvIntermediateProduct.setEditable(true);


		tcIntermediateProduct.setOnEditCommit((TableColumn.CellEditEvent<Ingredient, Material> e) -> {
					materialChanged(e);
				}
		);

		tcIProductAmount.setOnEditCommit((TableColumn.CellEditEvent<Ingredient, String> e) -> {
					amountChanged(e);
				}
		);
	}

	private void materialChanged(TableColumn.CellEditEvent<Ingredient, Material> e) {
		// new value coming from combobox
		Material newValue = e.getNewValue();

		// index of editing row in the tableview
		int index = e.getTablePosition().getRow();

		Ingredient ing = e.getTableView().getItems().get(index);

		ing.setMaterial(newValue);
	}

	private void amountChanged(TableColumn.CellEditEvent<Ingredient, String> e) {
		// new value coming from combobox
		String newValue = e.getNewValue();

		// index of editing row in the tableview
		int index = e.getTablePosition().getRow();

		Ingredient ing = e.getTableView().getItems().get(index);

		ing.getQuantity().setAmount(Double.parseDouble(newValue));
	}

	@Override
	public void updateEditView(Product product) {
		if (product != null) {
			this.product = product;
			this.viewMode = EditViewController.ViewMode.EDIT;
			tfName.setText(product.getName());
			tfCode.setText(product.getCode());
			tfColorName.setText(product.getColorCode());
			tfColorCode.setText(product.getColorCode());
			taDescription.setText(product.getDescription());
			tfDensity.setText(String.valueOf(product.getDensity()));
			rbAraUrun.setSelected(product.isIntermediateProduct());
			cbPaintType.getSelectionModel().select(product.getPaintSubType().getPaintType());
			cbPaintSubtype.getSelectionModel().select(product.getPaintSubType());
			List<Ingredient> rawMaterialList = product.getIngredientSet().stream().filter(p -> p.getMaterial() instanceof RawMaterial).collect(Collectors.toList());
			tvRawMaterial.getItems().addAll(rawMaterialList);
			List<Ingredient> intermediateProductList = product.getIngredientSet().stream().filter(p -> p.getMaterial() instanceof Product).collect(Collectors.toList());
			tvIntermediateProduct.getItems().addAll(intermediateProductList);
		}
	}

}

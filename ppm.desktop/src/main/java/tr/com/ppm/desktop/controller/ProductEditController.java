package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.converter.DefaultStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.Quantity;
import tr.com.ppm.desktop.model.common.State;
import tr.com.ppm.desktop.model.material.*;
import tr.com.ppm.desktop.model.production.Ingredient;
import tr.com.ppm.desktop.service.PaintSubTypeService;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.service.RawMaterialService;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

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
	void add(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void addRow(ActionEvent event) {
		ObservableList<Ingredient> items = tvRawMaterial.getItems();
		Ingredient ingredient = new Ingredient();
		ingredient.setQuantity(new Quantity(0.0));
		items.add(ingredient);
		tcRawMaterial.setEditable(true);
		tcRwAmount.setEditable(true);

	}

	@FXML
	void saveRow(ActionEvent event) {
		ObservableList<Ingredient> items = tvRawMaterial.getItems();
		/*items.add(new Ingredient());*/
		TableView.TableViewSelectionModel<Ingredient> selectionModel = tvRawMaterial.getSelectionModel();
		Ingredient selectedItem = selectionModel.getSelectedItem();
		int selectedIndex = selectionModel.getSelectedIndex();
		selectedItem.setMaterial(tcRawMaterial.getCellData(selectedIndex));
		selectedItem.setQuantity(new Quantity(Double.parseDouble(tcRwAmount.getCellData(selectedIndex))));

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbPaintType.setItems(FXCollections.observableArrayList(paintTypeService.list()));
		cbPaintSubtype.setItems(FXCollections.observableArrayList(paintSubTypeService.list()));
		tcRwAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity().getAmount())));
		tcRawMaterial.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMaterial()));
//		tcIProductAmount.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toString()));
		tvRawMaterial.setEditable(true);
//		tvIntermediateProduct.setEditable(true);
//		tcRawMaterial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
//		tcRawMaterial.setCellValueFactory(cellData -> new SimpleComb(cellData.getValue().toString()));

		tcRawMaterial.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(rawMaterialService.list())));
		tcRawMaterial.setOnEditCommit((TableColumn.CellEditEvent<Ingredient, Material> e) ->
				{
					// new value coming from combobox
					Material newValue = e.getNewValue();

					// index of editing row in the tableview
					int index = e.getTablePosition().getRow();

					Ingredient ingredient = (Ingredient) e.getTableView().getItems().get(index);

					ingredient.setMaterial(newValue);
				}
		);
/*		tcRawMaterial.setEditable(true);
		ArrayList<Ingredient> rawMaterials = new ArrayList<>();
		Ingredient e = new Ingredient();
		*//*RawMaterial rawMaterial = new RawMaterial("12", "deneme", "345", State.SOLID, "12");
		e.setMaterial(rawMaterial);*//*
		*//*Quantity quantity = new Quantity();
		quantity.setAmount(123.4);
		e.setQuantity(quantity);*//*
//		quantity
		rawMaterials.add(e);
		tvRawMaterial.setItems(FXCollections.observableArrayList(rawMaterials));*/


	}

}

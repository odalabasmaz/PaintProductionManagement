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
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.model.material.PaintType;
import tr.com.ppm.desktop.service.CustomerService;
import tr.com.ppm.desktop.service.PaintTypeService;
import tr.com.ppm.desktop.view.CustomerEditView;
import tr.com.ppm.desktop.view.PaintTypeEditView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author uadak.
 */
@Component
public class CustomerController implements Initializable {

	@FXML
	private TextField tfCustomer;

	@FXML
	private Button btnClean;

	@FXML
	private Button btnQuery;

	@FXML
	private TableView<Customer> tblViewCustomer;

	@FXML
	private TableColumn<Customer, String> tblColumnAd;

	@FXML
	private Button btnEdit;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnAdd;

	@Autowired
	private CustomerService service;

	@FXML
	void add(ActionEvent event) {
		ViewManager.openPopup(CustomerEditView.class, this::query);
	}

	private void query() {
		String customer = tfCustomer.getText();
		List<Customer> customers = service.listByName(customer);
		tblViewCustomer.setItems(FXCollections.observableArrayList(customers));
	}


	@FXML
	void clean(ActionEvent event) {
		tfCustomer.clear();
	}

	@FXML
	void delete(ActionEvent event) {
		Customer paintType = tblViewCustomer.getSelectionModel().getSelectedItem();
		service.remove(paintType);
		listAll();
	}

	@FXML
	void edit(ActionEvent event) {
		Customer selectedItem = tblViewCustomer.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			ViewManager.openPopup(CustomerEditView.class, this::query, selectedItem);
		}
	}

	@FXML
	void query(ActionEvent event) {
		String customer = tfCustomer.getText();
		List<Customer> customers = service.listByName(customer);
		tblViewCustomer.setItems(FXCollections.observableArrayList(customers));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblColumnAd.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		listAll();
	}

	private void listAll() {
		tblViewCustomer.setItems(FXCollections.observableArrayList(service.list()));
	}
}

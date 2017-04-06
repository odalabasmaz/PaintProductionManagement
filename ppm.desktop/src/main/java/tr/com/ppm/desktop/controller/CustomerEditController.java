package tr.com.ppm.desktop.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.service.CustomerService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author uadak.
 */
@Component
public class CustomerEditController implements Initializable, EditViewController<Customer> {

	@Autowired
	private CustomerService service;

	@FXML
	private TextField tfCustomer;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnClose;

	private ViewMode viewMode;

	private  Customer customer;

	@FXML
	void add(ActionEvent event) {
		if (this.viewMode == EditViewController.ViewMode.NEW) {
			service.save(new Customer(tfCustomer.getText()));
		} else if (this.viewMode == EditViewController.ViewMode.EDIT) {
			this.customer.setName(tfCustomer.getText());
			service.update(this.customer);
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
	public void updateEditView(Customer entity) {
		if (entity != null) {
			this.customer = entity;
			tfCustomer.setText(customer.getName());
			viewMode = ViewMode.EDIT;
		}
	}
}

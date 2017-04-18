package tr.com.ppm.desktop.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.Container;
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.model.material.Product;
import tr.com.ppm.desktop.model.order.Order;
import tr.com.ppm.desktop.model.order.OrderItem;
import tr.com.ppm.desktop.model.order.Status;
import tr.com.ppm.desktop.service.CustomerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class OrderEditController implements Initializable, EditViewController<Order> {

	@FXML
	private TextField tfOrderCode;

	@FXML
	private ComboBox<Customer> cbCustomer;

	@FXML
	private ComboBox<Status> cbOrderStatus;

	@FXML
	private TableView<OrderItem> tvOrderItem;

	@FXML
	private TableColumn<OrderItem, Product> tcProduct;

	@FXML
	private TableColumn<OrderItem, Container> tcContainer;

	@FXML
	private TableColumn<OrderItem, Integer> tcQuantity;

	@Autowired
	private CustomerService customerService;

	private Order order;
	private EditViewController.ViewMode viewMode = EditViewController.ViewMode.NEW;

	@FXML
	void addOrderItem(ActionEvent event) {
	}

	@FXML
	void deleteOrderItem(ActionEvent event) {
	}

	@FXML
	void add(ActionEvent event) {

	}

	@FXML
	void close(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbCustomer.setItems(FXCollections.observableArrayList(customerService.findAllCustomers()));
		cbOrderStatus.setItems(FXCollections.observableArrayList(Status.values()));
	}

	@Override
	public void updateEditView(Order order) {
		if (order != null) {
			this.order = order;
			this.viewMode = EditViewController.ViewMode.EDIT;
			tfOrderCode.setText(order.getOrderId());
			cbCustomer.getSelectionModel().select(order.getCustomer());
			cbOrderStatus.getSelectionModel().select(order.getStatus());
		}
	}
}

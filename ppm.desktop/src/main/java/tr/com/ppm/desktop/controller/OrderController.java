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
import tr.com.ppm.desktop.model.customer.Customer;
import tr.com.ppm.desktop.model.order.Order;
import tr.com.ppm.desktop.model.order.Status;
import tr.com.ppm.desktop.service.CustomerService;
import tr.com.ppm.desktop.service.OrderService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan
 */
@Component
public class OrderController implements Initializable {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CustomerService customerService;

	@FXML
	private TextField tfOrderCode;
	@FXML
	private ComboBox<Customer> cbCustomer;
	@FXML
	private ComboBox<Status> cbOrderStatus;

	@FXML
	private TableView<Order> tvOrder;

	@FXML
	private TableColumn<Order, String> tcOrderCode;
	@FXML
	private TableColumn<Order, String> tcCustomer;
	@FXML
	private TableColumn<Order, String> tcOrderStatus;
	@FXML
	private TableColumn<Order, String> tcDate;

	@FXML
	void query(ActionEvent event) {
		query();
	}

	private void query() {
		List<Order> orders = orderService.list();
		tvOrder.setItems(FXCollections.observableArrayList(orders));
	}

	@FXML
	void clean(ActionEvent event) {
		tfOrderCode.clear();
		cbCustomer.getSelectionModel().clearSelection();
		cbOrderStatus.getSelectionModel().clearSelection();
	}

	@FXML
	void add(ActionEvent event) {
	}

	@FXML
	public void delete(ActionEvent event) {
	}

	@FXML
	public void edit(ActionEvent event) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcOrderCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderId()));
		tcCustomer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCustomer().getName()));
		tcOrderStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));
		tcDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
		cbCustomer.setItems(FXCollections.observableArrayList(customerService.list()));
		cbOrderStatus.setItems(FXCollections.observableArrayList(Status.values()));
	}
}

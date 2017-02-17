package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.model.common.Quantity;
import tr.com.ppm.desktop.service.QuantityService;
import tr.com.ppm.desktop.view.MainView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class LoginController implements Initializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private QuantityService service;
	private MainView mainView;

	@FXML
	private TextField txtUserName;
	@FXML
	private Button btnLogin;
	@FXML
	private GridPane gridLogin;
	@FXML
	private PasswordField txtPassword;

	@Autowired
	public LoginController(QuantityService service, MainView mainView) {
		this.service = service;
		this.mainView = mainView;
	}

	@FXML
	public void login(ActionEvent event) {
		doLogin(event);
	}

	@FXML
	public void loginWithKeyEvent(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			doLogin(event);
		}
	}

	private void doLogin(Event event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
		openMain();
		service.addQuantity(new Quantity(15));
		LOGGER.info("Quantity list size: {}", service.listQuantity().size());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.disableProperty().bind(txtUserName.textProperty().isEmpty()
				.or(txtPassword.textProperty().isEmpty()));
	}

	public void openMain() {

		//todo: open mainView without loading ??

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
			Stage stage = new Stage();
			stage.setTitle("My New Stage Title");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

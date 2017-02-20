package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tr.com.ppm.desktop.view.MainView;
import tr.com.ppm.desktop.view.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
public class LoginController implements Initializable {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@FXML
	private TextField txtUserName;
	@FXML
	private Button btnLogin;
	@FXML
	private PasswordField txtPassword;

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
		ViewManager.openPage(MainView.class);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.disableProperty().bind(txtUserName.textProperty().isEmpty()
				.or(txtPassword.textProperty().isEmpty()));
	}
}

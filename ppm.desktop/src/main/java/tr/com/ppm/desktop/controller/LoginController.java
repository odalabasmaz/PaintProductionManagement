package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
public class LoginController implements Initializable {

	@FXML
	private TextField txtUserName;

	@FXML
	private Button btnLogin;

	@FXML
	private GridPane gridLogin;

	@FXML
	private PasswordField txtPassword;

	@FXML
	void login(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void loginWithKeyEvent(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.disableProperty().bind(txtUserName.textProperty().isEmpty()
				.or(txtPassword.textProperty().isEmpty()));
	}
}

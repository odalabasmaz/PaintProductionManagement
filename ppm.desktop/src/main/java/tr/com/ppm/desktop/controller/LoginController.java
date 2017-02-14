package tr.com.ppm.desktop.controller;

import javafx.event.ActionEvent;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ykarabalkan.
 */
@Component
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
		openMain();
	}

	@FXML
	void loginWithKeyEvent(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			((Node) (event.getSource())).getScene().getWindow().hide();
			openMain();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.disableProperty().bind(txtUserName.textProperty().isEmpty()
				.or(txtPassword.textProperty().isEmpty()));
	}

	public void openMain() {
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

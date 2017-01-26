package tr.com.ppm.desktop.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Orhun Dalabasmaz
 */
//@Component
public class MainView {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainView.class);

//	@Autowired
//	private FormulaView formulaView;
	private Stage stage;

	public MainView(Stage stage) {
		this.stage = stage;
	}

	public static void show(Stage stage) {
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(event -> LOGGER.info("Hello World!"));

		Button btFormula = new Button();
		btFormula.setText("Formül ekle");
		btFormula.setOnAction(event -> new FormulaView(stage).show());

		StackPane root = new StackPane();
		root.getChildren().add(btn);
		root.getChildren().add(btFormula);

		Scene scene = new Scene(root, 300, 250);
		stage.setTitle("Hello World!");
		stage.setScene(scene);
		stage.show();
	}
}

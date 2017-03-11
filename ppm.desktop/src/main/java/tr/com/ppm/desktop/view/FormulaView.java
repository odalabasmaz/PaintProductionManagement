package tr.com.ppm.desktop.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.com.ppm.desktop.model.common.Unit;

/**
 * @author Orhun Dalabasmaz
 */
//@Component
public class FormulaView {
	private static final Logger LOGGER = LoggerFactory.getLogger(FormulaView.class);
	private Stage stage;

	public FormulaView(Stage stage) {
		this.stage = stage;
	}

	public void show() {
		StackPane root = new StackPane();
		ComboBox<Unit> cbUnit = new ComboBox<>();
		cbUnit.getItems().addAll(
				new Unit(Unit.TYPE.MASS, "g", "gram"),
				new Unit(Unit.TYPE.MASS, "kg", "kilogram"),
				new Unit(Unit.TYPE.MASS, "ml", "mililitre"),
				new Unit(Unit.TYPE.MASS, "l", "litre")
		);

		Button btSave = new Button();
		btSave.setText("Kaydet");
		btSave.setOnAction(event -> stage.close());

		root.getChildren().add(cbUnit);
//		root.getChildren().equals(btSave);

		Scene scene = new Scene(root, 300, 250);
		stage.setTitle("Formül Tanım");
		stage.setScene(scene);
		stage.show();
	}
}

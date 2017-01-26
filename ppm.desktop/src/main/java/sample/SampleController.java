package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

/**
 * @author Orhun Dalabasmaz
 */
public class SampleController {
	public Label helloWorld;

	public void sayHelloWorld(ActionEvent event) {
		helloWorld.setText("HELLO WORLD!");
	}
}

package tr.com.ppm.desktop.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Orhun Dalabasmaz
 */
@FXMLView("/view/main.fxml")
public class MainView extends AbstractFxmlView {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainView.class);

	public MainView() {
		setTitle("Ana sayfa");
	}

}

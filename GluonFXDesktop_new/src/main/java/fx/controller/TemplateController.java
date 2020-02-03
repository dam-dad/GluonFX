package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class TemplateController implements Initializable {
	
	@FXML
	private VBox root;
	
	public TemplateController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TemplateView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public VBox getRoot() {
		return root;
	}

}

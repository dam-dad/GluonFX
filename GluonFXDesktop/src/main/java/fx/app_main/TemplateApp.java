package fx.app_main;


import fx.controller.TemplateController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TemplateApp extends Application {
	
	private TemplateController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new TemplateController();
		
		Scene escena = new Scene(controller.getRoot());
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("JavaFX Template Project Demo");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

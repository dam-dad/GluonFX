package fx.app_main;


import fx.controller.InvoiceController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TemplateApp extends Application {
	
	//private TemplateController controller;
	private InvoiceController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//controller = new TemplateController();
		controller = new InvoiceController();
		
		Scene escena = new Scene(controller.getView());
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("JavaFX Template Project Demo");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

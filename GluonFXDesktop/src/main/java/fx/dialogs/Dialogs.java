package fx.dialogs;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Dialogs {
	@FXML
	void onCreateButtonAction(ActionEvent event) {
		// Create the custom dialog.
		//Poner tipo factura de tu clase Factura
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Factura");
		dialog.setHeaderText("Inserta una nueva factura");

		// PONER ICONO DEL PADRE
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		//Cambiar el nombre del app 
		//stage.getIcons().setAll(FacturaApp.getPrimaryStage().getIcons());
		
		// Set the icon (must be included in the project).
		dialog.setGraphic(new ImageView(this.getClass().getResource("/icons/invoice2.png").toString()));

		ButtonType buttonType = new ButtonType("Guardar y salir", ButtonData.OK_DONE);
		ButtonType buttonType2 = new ButtonType("Salir sin guardar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType, buttonType2);
		
		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		Label numeroDeFactura = new Label("Numero de factura: ");
		Label fechaFactura = new Label("Fecha de factura: ");
		Label company = new Label("Empresa: ");
		Label customer = new Label("Cliente: ");
		Label tipoFactura = new Label("Tipo de Factura: ");
		Label payMethod = new Label("Forma de pago: ");
		Label tax = new Label("Impuesto: ");
		Label concept = new Label("Concepto: ");
		
		TextField invoiceText = new TextField();
		invoiceText.setPromptText("Numero de factura:");
		
		DatePicker invoiceDate = new DatePicker();
		
		ComboBox<String> companyCombo = new ComboBox<String>();
		companyCombo.setPromptText("Nombre de la empresa");
		
		//TODO PROVISONAL(STRING) HASTA QUE SE CREE LA CLASE CLIENTE
		ComboBox<String> customerCombo = new ComboBox<String>();
		customerCombo.setPromptText("Nombre del cliente");
		
		ComboBox<String> statusCombo = new ComboBox<String>();
		statusCombo.setPromptText("Nombre del cliente");
		
		ComboBox<String> payMethodCombo = new ComboBox<>();
		payMethodCombo.setPromptText("Forma de pago");
		
		ComboBox<String> taxCombo = new ComboBox<>();
		taxCombo.setPromptText("Id impuesto");
		
		TextArea conceptArea = new TextArea();
		conceptArea.setPromptText("Introduzca aqui el concepto");
			
		
		HBox facturaBox = new HBox(numeroDeFactura, invoiceText,fechaFactura, invoiceDate);
		facturaBox.setSpacing(10);
		
		HBox companyBox = new HBox(company, companyCombo);
		companyBox.setSpacing(10);
		
		HBox customerBox = new HBox(customer, customerCombo);
		customerBox.setSpacing(10);
		
		HBox typeInvoiceBox = new HBox(tipoFactura, statusCombo,payMethod, payMethodCombo, tax, taxCombo);
		typeInvoiceBox.setSpacing(10);

		HBox conceptBox = new HBox(concept, conceptArea );
		typeInvoiceBox.setSpacing(20);
		

		grid.add(facturaBox, 0, 0);
		grid.add(companyBox, 0, 1);
		grid.add(customerBox, 0, 2);
		grid.add(typeInvoiceBox, 0, 3);
		grid.add(conceptBox, 0, 4);
		
		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
			
		    return null;
		});
		
		Optional<String> result = dialog.showAndWait();

		result.ifPresent(r -> {
		    System.out.println(r.toString());
		});
	}

	@FXML
	void onDeleteButtonAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Borrar");
		alert.setHeaderText("");
		alert.setContentText("Esta usted seguro de eliminar esta factura: ");

		// PONER ICONO DEL PADRE
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		//Poner Tu clase app
		//stage.getIcons().setAll(FacturaApp.getPrimaryStage().getIcons());
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		  
		} 
	}
}

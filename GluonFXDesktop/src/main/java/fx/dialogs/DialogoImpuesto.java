package fx.dialogs;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DialogoImpuesto {
	@FXML
	void onAddButtonAction(ActionEvent event) {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Impuesto");
		dialog.setHeaderText("Inserta un nuevo impuesto");

		// PONER ICONO DEL PADRE
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		//stage.getIcons().setAll(ImpuestoApp.getPrimaryStage().getIcons());

		// Set the icon (must be included in the project).
		//dialog.setGraphic(new ImageView(this.getClass().getResource("/icons/invoice2.png").toString()));

		ButtonType buttonType = new ButtonType("Guardar y salir", ButtonData.OK_DONE);
		ButtonType buttonType2 = new ButtonType("Salir sin guardar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType, buttonType2);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		Label tax = new Label("Id impuesto: ");
		Label percentage = new Label("Porcentaje: ");
		Label description = new Label("Dirección: ");
		

		ComboBox<String> taxCombo = new ComboBox<>();
		taxCombo.setPromptText("Impuesto");

		TextField percentageText = new TextField();
		percentageText.setPromptText("Intoduce el porcentaje:");

		TextArea descriptionText = new TextArea();
		descriptionText.setPromptText("Descripción:");

		

		HBox taxBox = new HBox(tax, taxCombo);
		taxBox.setSpacing(10);

		HBox descriptionBox = new HBox(description, descriptionText);
		descriptionBox.setSpacing(10);

		HBox percentageBox = new HBox(percentage,percentageText);
		percentageBox.setSpacing(10);


		grid.add(taxBox, 0, 0);
		grid.add(percentageBox, 0, 1);
		grid.add(descriptionBox, 0, 2);

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
		alert.setContentText("Esta usted seguro de eliminar esta impuesto: ");

		// PONER ICONO DEL PADRE
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		//stage.getIcons().setAll(ImpuestoApp.getPrimaryStage().getIcons());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

		}
	}
}

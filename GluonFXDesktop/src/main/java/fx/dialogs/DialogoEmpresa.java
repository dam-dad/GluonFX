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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DialogoEmpresa {
	@FXML
	void onAddButtonAction(ActionEvent event) {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Empresa");
		dialog.setHeaderText("Inserta una nueva empresa");

		// PONER ICONO DEL PADRE
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		//stage.getIcons().setAll(EmpresaApp.getPrimaryStage().getIcons());

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

		Label company = new Label("Id empresa: ");
		Label name = new Label("Nombre: ");
		Label address = new Label("Dirección: ");
		Label city = new Label("Ciudad: ");
		Label country = new Label("Pais: ");
		Label email = new Label("Email: ");
		Label phone = new Label("Teléfono: ");

		ComboBox<String> companyCombo = new ComboBox<>();
		companyCombo.setPromptText("Empresa");

		TextField nameText = new TextField();
		nameText.setPromptText("Nombre empresa:");

		TextField addressText = new TextField();
		addressText.setPromptText("Dirección:");

		TextField cityText = new TextField();
		cityText.setPromptText("Ciudad:");

		TextField countryText = new TextField();
		countryText.setPromptText("Pais:");

		TextField emailText = new TextField();
		emailText.setPromptText("Dirrección:");

		TextField phoneText = new TextField();
		phoneText.setPromptText("Teléfono:");

		HBox customerBox = new HBox(company, companyCombo, name, nameText);
		customerBox.setSpacing(10);

		HBox addressBox = new HBox(address, addressText);
		addressBox.setSpacing(10);

		HBox cityBox = new HBox(city, cityText, country, countryText);
		cityBox.setSpacing(10);

		HBox emailBox = new HBox(email, emailText);
		emailBox.setSpacing(10);

		HBox phoneBox = new HBox(phone, phoneText);
		phoneBox.setSpacing(10);

		grid.add(customerBox, 0, 0);
		grid.add(addressBox, 0, 1);
		grid.add(cityBox, 0, 2);
		grid.add(emailBox, 0, 3);
		grid.add(phoneBox, 0, 4);

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
		alert.setContentText("Esta usted seguro de eliminar esta empresa: ");

		// PONER ICONO DEL PADRE
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		//stage.getIcons().setAll(EmpresaApp.getPrimaryStage().getIcons());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

		}
	}
}

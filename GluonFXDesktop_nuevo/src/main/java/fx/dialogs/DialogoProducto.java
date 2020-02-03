package fx.dialogs;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogoProducto {
	@FXML
	void onAddButtonAction(ActionEvent event) {
		// Create the custom dialog.
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Producto");
		dialog.setHeaderText("Inserta un nuevo producto");

		// PONER ICONO DEL PADRE
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		//stage.getIcons().setAll(ProductoApp.getPrimaryStage().getIcons());

		// Set the icon (must be included in the project).
		// dialog.setGraphic(new
		// ImageView(this.getClass().getResource("/icons/invoice2.png").toString()));

		ButtonType buttonType = new ButtonType("Guardar y salir", ButtonData.OK_DONE);
		ButtonType buttonType2 = new ButtonType("Salir sin guardar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType, buttonType2);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		Label product = new Label("Id producto: ");
		Label name = new Label("Nombre: ");
		Label price = new Label("Precio: ");
		Label description = new Label("Descripción: ");

		ComboBox<String> productCombo = new ComboBox<>();
		productCombo.setPromptText("Producto");

		TextField nameText = new TextField();
		nameText.setPromptText("Nombre producto:");

		TextField priceText = new TextField();
		priceText.setPromptText("Precio:");

		TextArea descriptionText = new TextArea();
		descriptionText.setPromptText("Descripción:");

		Image image = new Image("/icons/pelota.jpg", 100, 100, true, false);
		ImageView productImage = new ImageView();
		productImage.setImage(image);

		Button addImage = new Button("Añadir");
		Button deleteImage = new Button("Eliminar");

		HBox buttonImageBox = new HBox(addImage, deleteImage);
		buttonImageBox.setSpacing(10);

		VBox imageBox = new VBox(productImage, buttonImageBox);
		imageBox.setSpacing(10);

		HBox productBox = new HBox(product, productCombo);
		productBox.setSpacing(10);

		HBox nameBox = new HBox(name, nameText);
		nameBox.setSpacing(10);

		HBox priceBox = new HBox(price, priceText);
		priceBox.setSpacing(10);

		HBox descriptionBox = new HBox(description, descriptionText);
		descriptionBox.setSpacing(10);

		VBox ladoDerecho = new VBox(productBox, nameBox, priceBox);
		ladoDerecho.setSpacing(10);

		HBox parteArriba = new HBox(ladoDerecho, imageBox);
		parteArriba.setSpacing(10);

		VBox root = new VBox(parteArriba, descriptionBox);
		root.setSpacing(10);

		/*
		 * grid.add(productBox, 0, 0); grid.add(nameBox, 0, 1); grid.add(priceBox, 0,
		 * 2); grid.add(descriptionBox, 0, 3); grid.add(imageBox,2,0);
		 * GridPane.setRowSpan(imageBox, 3); GridPane.setColumnSpan(descriptionBox, 2);
		 */

		dialog.getDialogPane().setContent(root);

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
		//stage.getIcons().setAll(ProductoApp.getPrimaryStage().getIcons());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

		}
	}
}

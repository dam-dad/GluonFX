package com.gluonapplication.views;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.PicturesService;
import com.gluonhq.charm.down.plugins.StorageService;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.ProgressIndicator;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.converter.InputStreamInputConverter;
import com.gluonhq.connect.converter.JsonInputConverter;
import com.gluonhq.connect.converter.JsonOutputConverter;
import com.gluonhq.connect.converter.OutputStreamOutputConverter;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;
import com.model.beans.Product;
import com.utils.B64Util;
import com.utils.PngEncoderFX;
import com.utils.ProductPNG;
import com.utils.WrapperProductPNG;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;



/**
 * Clase de tipo controller/presenter para product
 * @author moimah
 *
 */
public class ProductPresenter {

	@FXML
	private View secondary;

	@FXML
	private Tab tabMainProduct;

	@FXML
	private Tab tabDialog;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnNewProduct;

	@FXML
	private Button btnModifyProduct;

	@FXML
	private Button btnDeleteProduct;

	@FXML
	private TableView<Product> tableProducts;

	@FXML
	private TableColumn<Product, String> columnProductId;

	@FXML
	private TableColumn<Product, String> columnProductName;

	@FXML
	private TableColumn<Product, Number> columnProductStock;

	@FXML
	private TableColumn<Product, Number> columnPrice;

	@FXML
	private TextField txtProductId;

	@FXML
	private TextField txtProductName;

	@FXML
	private TextField txtProductDescription;

	@FXML
	private TextField txtPriceUnit;

	@FXML
	private TextField txtStock;

	@FXML
	private Button btnSelectImage;

	@FXML
	private ImageView imgProduct;

	@FXML
	private Button btnSaveProduct;

	@FXML
	private Button btnCancelProduct;

	//Model
	private ProductModel model = new ProductModel();
	//Producto seleccionado
	private Product masterProduct;
	//Opcion 0 nuevo / guardar 1
	private int option = 0; 
	//Imagen a guardar
	File imgFile = null;


	/**
	 *  Inizializacion, configuracion inicial, bindeos y listeners
	 */	
	public void initialize() {
		
		secondary.setShowTransitionFactory(BounceInRightTransition::new);


		secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(
						MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().getDrawer().open()));
				appBar.setTitleText("Productos");				
			}
		});

		tableProducts.itemsProperty().bindBidirectional(model.listProductsProperty());
		model.productSelectedProperty().bind(tableProducts.getSelectionModel().selectedItemProperty());

		columnProductId.setCellValueFactory(v -> v.getValue().productIdProperty());
		columnProductName.setCellValueFactory(v -> v.getValue().nameProperty());
		columnProductStock.setCellValueFactory(v -> v.getValue().stockProperty());
		columnPrice.setCellValueFactory(v -> v.getValue().priceProperty());

		tableProducts.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onProductSelected(nv));

		txtProductId.textProperty().bindBidirectional(model.productIdProperty());
		txtProductName.textProperty().bindBidirectional(model.productNameProperty());
		txtProductDescription.textProperty().bindBidirectional(model.descriptionProperty());
		txtStock.textProperty().bindBidirectional(model.stockProperty());
		txtPriceUnit.textProperty().bindBidirectional(model.priceUnitProperty());
		imgProduct.imageProperty().bindBidirectional(model.imgProductProperty());

		btnSaveProduct.disableProperty()
				.bind(model.productIdProperty().isNull().or(model.descriptionProperty().isNull())
						.or(model.productNameProperty().isNull()).or(model.stockProperty().isNull())
						.or(model.priceUnitProperty().isNull()));

	

		tabDialog.getTabPane().getTabs().remove(tabDialog);

		try {
			updateContent();
		} catch (Exception e) {

		}

	}

	
	/**
	 * Listener que detecta el producto seleccionado sobre la tableProducts
	 * @param nv producto seleccionado
	 */
	private void onProductSelected(Product nv) {

		this.masterProduct = nv;

	}

	/**
	 * Actualiza los productos de la lista con llamada al metodo
	 * que realiza reques a bbdd
	 */
	public void updateContent() {

		getAllProducts();

	}

	/**
	 * Listener que responde a btnDeleteProduct, comprueba que se ha seleccionado
	 * un producto, pide confirmacion y lo elimina
	 * @param event
	 */
	@FXML
	void onActionBtnDeleteProduct(ActionEvent event) {

		if (masterProduct != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Desea eliminar el producto: " + masterProduct.getName());

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				deleteProduct(masterProduct);
			} else {
				alert.close();
			}

		}
	}

	/**
	 * Listener que responde al btnModify, situa el tabDialog
	 * como principal, cambia el estado a modificando 1		
	 * @param event
	 */
	@FXML
	void onActionBtnModify(ActionEvent event) {

		option = 1;
		if (masterProduct != null) {
			tabDialog.setText("Modificar");
			tabRequest(masterProduct);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Seleccione un producto");
			alert.showAndWait();

		}

	}

	/**
	 * Listener que responde al btnNewProducto, situa el tabDialog
	 * como principal, cambia el estado a nuevo 0	
	 * @param event
	 */
	@FXML
	void onActionBtnNewProduct(ActionEvent event) {
		imgFile = null;
		option = 0;
		tabDialog.setText("Nuevo");
		tabRequest(new Product());

	}

	
	/**
	 * Responde al btnSearchProduct, se activa un cuadro de dialogo
	 * que recibe el codigo/nombre del producto, realiza busquedas.
	 * En caso de encontrar resultados se abre tab para modificar 
	 * @param event
	 */
	@FXML
	void onActionBtnSearchProduct(ActionEvent event) {

		Product product = null; // Producto de busqueda

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Buscar producto");
		dialog.setGraphic(null);
		dialog.setHeaderText(null);
		dialog.setContentText("Introduce el codigo/nombre:");

		
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {

			String question = result.get();

			for (Product p : model.getListProducts()) {

				if (p.getName().contains(question)) {
					product = p;
					break;
				} else if (p.getProductId().contains(question)) {
					product = p;
					break;
				}

			}

			if (product != null) {
				tableProducts.getSelectionModel().select(product);
				onActionBtnModify(null);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("No hay resultados");
				alert.showAndWait();
			}
		}

	}

	
	/**
	 * Responde al btnSelectImage, si la plataforma es windows un fileChooser permite seleccionar imagen,
	 * en movil da la opcion de camara o galeria. Carga la imagen seleccionada en el modelo
	 * @param event
	 */
	@FXML
	void onActionBtnSelectImage(ActionEvent event) {

		if (Platform.isDesktop()) {

			FileChooser fileChooser = new FileChooser();

			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png"));
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG files", "*.jpg", "*.jpeg"));

			imgFile = fileChooser.showOpenDialog(null);
			

			Image img = new Image(imgFile.toURI().toString());
			model.setImgProduct(img);

		} else {

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Seleccion de imagen");
			alert.setContentText("Elija una opcion.");

			ButtonType buttonCamera = new ButtonType("Camara");
			ButtonType buttonShare = new ButtonType("Galeria");

			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonCamera, buttonShare, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonCamera) {

				Services.get(PicturesService.class).ifPresent(service -> {
					service.takePhoto(false).ifPresent(image -> {
						model.setImgProduct(image);

					});

				});

			} else if (result.get() == buttonShare) {

				Services.get(PicturesService.class).ifPresent(pictures -> {				
					pictures.loadImageFromGallery().ifPresent(image -> {
						model.setImgProduct(image);

					});
				});

			} else {
				alert.close();
			}

		}

	}

	/**
	 * Oculta el tabDialog, volviendo al tabMainProduct
	 * @param event
	 */
	@FXML
	void onActionbtnCancelProduct(ActionEvent event) {

		tabDialog.getTabPane().getTabs().add(tabMainProduct);
		tabMainProduct.getTabPane().getSelectionModel().select(tabMainProduct);
		tabDialog.getTabPane().getTabs().remove(tabDialog);

	}

	/**
	 * Carga todos los datos del modelo sobre un objeto producto.
	 * Dependiendo de si es nuevo o update, llama al metodo correspondiente
	 * @param event
	 */
	@FXML
	void onActionbtnSaveProduct(ActionEvent event) {

		tabDialog.getTabPane().getTabs().add(tabMainProduct);
		tabMainProduct.getTabPane().getSelectionModel().select(tabMainProduct);
		tabDialog.getTabPane().getTabs().remove(tabDialog);

		File file = null;
		Product product = null;

		if (option == 1) {

			product = masterProduct;
			product.setProductId(model.getProductId());
			product.setName(model.getProductName());

			String price = "" + model.getPriceUnit();
			String priceOK = price.replace(",", ".");
			product.setPrice(Double.valueOf(priceOK));

			product.setStock(Integer.valueOf(model.getStock()));
			product.setDescription(model.getDescription());
			
			try {
				file =  getImageFile(model.getImgProduct());				
			    insertOrUpdateProduct(file, product);
			} catch (Exception e) {				
			}

		
		
		} else {

			product = new Product();
			product.setProductId(model.getProductId());
			product.setName(model.getProductName());

			String price = model.getPriceUnit();
			String priceOK = price.replace(",", ".");
			product.setPrice(Double.valueOf(priceOK));

			product.setStock(Integer.valueOf(model.getStock()));
			product.setDescription(model.getDescription());


			try {
				file =  getImageFile(model.getImgProduct());				
			    insertOrUpdateProduct(file, product);
			} catch (Exception e) {				
			}

		}

	}

	/**
	 * Hace un llamamiento a la tab productDialog, poniendola en primer plano, 
	 * la carga con el contenido recibido de producto
	 * @param product producto a editar o null si es nuevo
	 */
	private void tabRequest(Product product) {

		//clean interface
		model.setProductId("");
		model.setProductName("");
		model.setDescription("");
		model.setPriceUnit("");
		model.setStock("");
		model.setImgProduct(null);

		tabMainProduct.getTabPane().getTabs().add(tabDialog);
		tabDialog.getTabPane().getSelectionModel().select(tabDialog);
		tabMainProduct.getTabPane().getTabs().remove(tabMainProduct);

		try {
			model.setProductId(product.getProductId());
		} catch (Exception e) {
		}
		try {
			model.setProductName(product.getName());
		} catch (Exception e) {
		}
		try {
			model.setDescription(product.getDescription());
		} catch (Exception e) {
		}
		try { 		
			model.setPriceUnit(""+product.getPrice());
		} catch (Exception e) {
		}
		try {
			model.setStock("" + product.getStock());
		} catch (Exception e) {
		}
		try {
			model.setImgProduct(new Image(product.getUrl()));
		} catch (Exception e) {
		}

	}

	/**
	 * Realiza consulta de todos los productos de la bbdd 
	 * y los aplica sobre el modelo.
	 */
	private void getAllProducts() {

		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
						.method("GET")
						.host("http://52.161.156.63:4132")
						.path("product")
						.contentType("application/json;charset=UTF-8");

		// retrieve a list from the DataProvider
		GluonObservableList<Product> result = DataProvider.retrieveList(restClient.createListDataReader(Product.class));

		result.initializedProperty().addListener((obs, ov, nv) -> {
			if (nv != null) {
				model.setListProducts(result);

			}
		});

	}

	/**
	 * Elimina un producto existente de la bbdd, y actualiza el contenido
	 * @param product producto a eliminar
	 */
	private void deleteProduct(Product product) {

		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
						.method("DELETE")
						.host("http://52.161.156.63:4132")
						.path("product/" + product.getId())
						.contentType("application/json;charset=UTF-8");

		// create an observable object
		GluonObservableObject<Product> result = new GluonObservableObject<>();
		result.set(product);

		// Create the converters
		OutputStreamOutputConverter<Product> outputConverter = new JsonOutputConverter<>(Product.class);
		InputStreamInputConverter<Product> inputConverter = new JsonInputConverter<>(Product.class);

		// Delete the object
		DataProvider.removeObject(result, restClient.createObjectDataRemover(outputConverter, inputConverter));

		result.stateProperty().addListener((obs, ov, nv) -> {

			updateContent();

		});

	}

	/**
	 * Inserta y actualiza un producto con o sin imagen en la bddd,
	 * realiza una conversion del fichero a String en b64. Un progress dialog
	 * se mostrara mientras se realizan los cambios
	 * @param file fichero de imagen
	 * @param product producto a insertar/actualiar
	 */
	private void insertOrUpdateProduct(File file, Product product) {

		WrapperProductPNG wrapper = new WrapperProductPNG();
		ProductPNG productPNG = null;
		
		try {
			String b64 = B64Util.encoder(file.getAbsolutePath());		
			 productPNG = new ProductPNG();
			productPNG.setId("tempImg.png");
			productPNG.setB64(b64);
		}catch (Exception e) {
			
		}
				
		wrapper.setProduct(product);
		wrapper.setProductPNG(productPNG);
		
		//Create a dialog
		
		Dialog<ArrayList<Object>> dialog  = progressDialog();

		
		dialog.show();
			
		
	   // create a RestClient to the specific URL
	   RestClient restClient= RestClient.create()
	               .method("POST")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/png")	            
	               .contentType("application/json");
	              
	    //Create the converter     	       
	    OutputStreamOutputConverter<WrapperProductPNG> outputConverter = new JsonOutputConverter<>(WrapperProductPNG.class);
	    InputStreamInputConverter<WrapperProductPNG> inputConverter = new JsonInputConverter<>(WrapperProductPNG.class);

	      	       	       
	    //store objects on the server
	    GluonObservableObject<WrapperProductPNG> result = DataProvider.storeObject(wrapper, restClient.createObjectDataWriter(outputConverter, inputConverter));
	      
		result.stateProperty().addListener((obs, ov, nv) -> {		
			
			updateContent();
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
			dialog.close();
			
				
		});  
		
		
		
		
		
		
	}

	/**
	 * Convierte una imagen en un fichero
	 * @param image imagen a convertir
	 * @return file de la imagen seleccionada
	 */
	private File getImageFile(Image image) {

		if (image == null) {
			return null;
		}

		// 1. Encode image to png
		PngEncoderFX encoder = new PngEncoderFX(image, true);
		byte[] bytes = encoder.pngEncode();

		// 2.Write byte array to a file in public storage
		File root = Services.get(StorageService.class).flatMap(storage -> storage.getPublicStorage("Pictures"))
				.orElse(null);
		if (root != null) {
			File file = new File(root,
					"Image-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuuMMdd-HHmmss")) + ".png");
			try (FileOutputStream fos = new FileOutputStream(file)) {
				fos.write(bytes);
				return file;
			} catch (IOException ex) {
				System.out.println("Error: " + ex);
			}
		}
		return null;
	}


	
	/**
	 * Cuadro de dialogo personalizado, con un progressIndicartor
	 * @return dialogo personalizado
	 */
	public Dialog<ArrayList<Object>> progressDialog() {
		
		// Crear un dialogo personalizado.
		Dialog<ArrayList<Object>> dialog = new Dialog<>();
		dialog.setTitle("Guardando");
		
		// Crear GridPane  y elementos
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		
		ProgressIndicator progressIndicator  = new ProgressIndicator();				

		grid.add(new Label("Guardando:"), 0, 0);
		grid.add(progressIndicator, 1, 0);
		

		dialog.getDialogPane().setContent(grid);

	
		return dialog;

	}

}
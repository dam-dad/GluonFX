package fx.dialogs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dad.javafx.componentes.TextDecimal;
import model.entities.Invoice;
import model.entities.InvoiceDetail;
import model.entities.Product;
import model.beans.InvoiceBean;
import model.beans.InvoiceDetailBean;
import model.beans.ProductBean;

public class AddDetailDialogController extends Dialog<AddDetailDialogModel> implements Initializable{
	@FXML
	private VBox root;
	@FXML
	private ComboBox<ProductBean> cmbProduct;
	@FXML
	private TextDecimal txtStock;
	@FXML
	private TextDecimal txtCantidad;
    @FXML
    private TextDecimal txtPriceUd;
	@FXML
	private TextField txtProdcutId;
	@FXML
	private Button btnSearch;
	@FXML
	private ImageView imgProduct;
			
	private AddDetailDialogModel model = new AddDetailDialogModel();
	
	private List<ProductBean> listProducts = new ArrayList<ProductBean>(); 
	
	private ButtonType REGISTRAR_BUTTON_TYPE = new ButtonType("Registrar", ButtonData.OK_DONE);
	
	private ProductBean masterProductBean;
	
	private InvoiceDetail invoiceDetail = new InvoiceDetail();  
	
	//private Invoice invoice;  
	
	private ProductBean productBean; 
	
	public AddDetailDialogController(Invoice invoice) {
		super();
		
			
		
		setTitle("Nuevo detalle");
		//setHeaderText("Introduzca .................");
		//setGraphic(new ImageView(getClass().getResource("/images/telefono.png").toString()));
		getDialogPane().getButtonTypes().addAll(
				REGISTRAR_BUTTON_TYPE, // botón personalizado
				ButtonType.CANCEL
			);
		getDialogPane().setContent(loadContent("/fxml/AddDetailDialogView.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	
		    	AddDetailDialogModel detailForDialog = new AddDetailDialogModel();
		    	
		    	
		    	detailForDialog.setCantidad(model.getCantidad());
		    	detailForDialog.setPriceUnit(model.getPriceUnit());
		    	detailForDialog.setStock(model.getStock());
		    	detailForDialog.setImgProduct(model.getImgProduct());
		    	detailForDialog.setListProducts(model.getListProducts());
		    	detailForDialog.setProductId(model.getProductId());
		    	detailForDialog.setProductSelected(model.getProductSelected());
		     
					    	
		    	return detailForDialog;
		       
		    }
		    return null;
		});	
	}
	
	
	private Node loadContent(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			loader.setController(this);
			return loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model.productIdProperty().bindBidirectional(txtProdcutId.textProperty());
		model.listProductsProperty().bindBidirectional(cmbProduct.itemsProperty());		
		model.productSelectedProperty().bindBidirectional(cmbProduct.valueProperty());
		model.cantidadProperty().bindBidirectional(txtCantidad.decimalProperty());	
		model.priceUnitProperty().bindBidirectional(txtPriceUd.decimalProperty());
		model.imgProductProperty().bindBidirectional(imgProduct.imageProperty());
		model.stockProperty().bindBidirectional(txtStock.decimalProperty());
		model.productSelectedProperty().bindBidirectional(cmbProduct.valueProperty());
		cmbProduct.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onProductSelected(nv)); 
		
		
		Node registrarButton = getDialogPane().lookupButton(REGISTRAR_BUTTON_TYPE);

		registrarButton.disableProperty()
				.bind(
						model.productIdProperty().isNull()
						.or(model.productSelectedProperty().isNull())
						.or(model.cantidadProperty().lessThan(model.getStock())));
		
	}
	
	
	private Object onProductSelected(Object nv) {
		
		this.masterProductBean = (ProductBean) nv; 
		
		
		model.setProductId(masterProductBean.getProductId());
		model.setImgProduct(new Image(masterProductBean.getUrl()));
		model.setStock(masterProductBean.getStock());
		model.setPriceUnit(masterProductBean.getPrice());
		
		
		return null;
	}
	
    @FXML
    private void onClickBtnSearch(ActionEvent event) {
    	
		String productId = txtProdcutId.getText().trim();

		try {

			for (ProductBean p : cmbProduct.getItems()) {
				if (p.getProductId().contentEquals(productId)) {
					cmbProduct.getSelectionModel().select(p);
				}
			}

		} catch (Exception e) {
		}

    }


	public void chargeProducts(List<ProductBean> listProducts2) {
		model.setListProducts(FXCollections.observableArrayList(listProducts2));
	}

}

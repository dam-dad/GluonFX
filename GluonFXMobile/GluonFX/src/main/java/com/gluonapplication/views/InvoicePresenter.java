package com.gluonapplication.views;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.ShareService;
import com.gluonhq.charm.down.plugins.StorageService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
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
import com.model.beans.*;
import com.utils.B64Util;
import com.utils.WrapperConceptInvoice;
import com.utils.WrapperCustomerInvoice;
import com.utils.WrapperInvoiceDetail;
import com.utils.InvoicePDF;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;


/**
 * Clase tipo controller/presenter para Invoice
 * @author moimah
 *
 */
public class InvoicePresenter {
	
		

	@FXML
	private View primary;

	@FXML
	private Tab tabInvoices;

	@FXML
	private TableView<Invoice> tableInvoices; 

	@FXML
	private TableColumn<Invoice, String> columnDate;

	@FXML
	private TableColumn<Invoice, Customer> columnCustomer;

	@FXML
	private TableColumn<Invoice, Number> columPrice;

	@FXML
	private TableColumn<Invoice, Number> columnStatus;

	@FXML
	private Button btnNewInvoice;

	@FXML
	private Tab tabCurrentInvoice;

	@FXML
	private TextField txtCustomerName; 

	@FXML
	private TextField txtNIF;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtPhone;

	@FXML
	private Button btnSearchCustomer;

	@FXML
	private TextField txtConcept;

	@FXML
	private ComboBox<Tax> cmbTax;

	@FXML
	private ToggleGroup tooglePayMethod;

	@FXML
	private RadioButton radioCash;

	@FXML
	private RadioButton radioTransfer;

	@FXML
	private RadioButton radioCredit;

	@FXML
	private ToggleGroup toogleStatus;

	@FXML
	private RadioButton radioReleased;

	@FXML
	private RadioButton radioPending;

	@FXML
	private RadioButton radioAprobed;

	@FXML
	private TextField txtSearchProduct;

	@FXML
	private Button btnSearchProduct;

	@FXML
	private Button btnSaveInvoiceInformation;

	@FXML
	private ImageView imgProduct;

	@FXML
	private TextField txtProductDescription;

	@FXML
	private TextField txtUdsStock;

	@FXML
	private TextField txtQuantity;

	@FXML
	private Button btnAddDetail;

	@FXML
	private ListView<Product> listProducts;

	@FXML
	private TableView<InvoiceDetail> tableDetails;

	@FXML
	private TableColumn<InvoiceDetail, Product> columnProduct;

	@FXML
	private TableColumn<InvoiceDetail, Number> columnUds;

	@FXML
	private TableColumn<InvoiceDetail, Number> columnPriceUnit;

	@FXML
	private TableColumn<InvoiceDetail, Number> ColumnSubtotal;

	@FXML
	private TextField txtBase;

	@FXML
	private TextField txtTotalToPay;

	@FXML
	private TextField txtTaxPercentage;

	@FXML
	private TextField txtTaxTotal;
	
	@FXML
	private TextField txtPrecioUd;
	
	@FXML
	private TextField  txtEmail;
	
	@FXML
	private TextField txtCity;

	// Model
	private InvoiceModel model = new InvoiceModel();
	private Invoice invoiceMaster; // //Factura seleccionada
	
	//Parametros por defecto
	private Company DEFAULT_COMPANY; 
	private Tax DEFAULT_TAX;
	private PayMethod DEFAULT_PAY_METHOD;
	
	//Fichero a guardar
	private File pdfFile = null;
	
	//Lista de facturas
	List<Invoice> listBeans;
	

	//Ultimo indice seleccionado
	int selectedIndex = 0; 
	
	
	/**
	 *  Inizializacion, configuracion inicial, bindeos y listeners
	 */	
	public void initialize() {
		
	
		primary.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(
						MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().getDrawer().open()));
				appBar.setTitleText("Facturas");
				appBar.getActionItems()				
						.add(MaterialDesignIcon.PICTURE_AS_PDF.button(e ->generarPDF()));
			}
		});
			

		// BINDINGS
		tableInvoices.itemsProperty().bindBidirectional(model.listInvoicesProperty());
		model.invoiceSelectedProperty().bind(tableInvoices.getSelectionModel().selectedItemProperty());

		txtCustomerName.textProperty().bindBidirectional(model.customerNameProperty());
		txtNIF.textProperty().bindBidirectional(model.customerNIFProperty());
		txtAddress.textProperty().bindBidirectional(model.customerAddressProperty());
		txtPhone.textProperty().bindBidirectional(model.customerPhoneProperty());
		txtEmail.textProperty().bindBidirectional(model.customerEmailProperty());
		txtCity.textProperty().bindBidirectional(model.customerCityProperty());

		txtConcept.textProperty().bindBidirectional(model.conceptDescriptionProperty());

		cmbTax.itemsProperty().bindBidirectional(model.listTaxesProperty());
		model.taxSelectedProperty().bind(cmbTax.getSelectionModel().selectedItemProperty());

		radioCash.selectedProperty().bindBidirectional(model.cashProperty());
		radioTransfer.selectedProperty().bindBidirectional(model.bankTransferProperty());
		radioCredit.selectedProperty().bindBidirectional(model.creditProperty());

		radioReleased.selectedProperty().bindBidirectional(model.releasedProperty());
		radioPending.selectedProperty().bindBidirectional(model.pendingProperty());
		radioAprobed.selectedProperty().bindBidirectional(model.AprobedProperty());

		txtSearchProduct.textProperty().bindBidirectional(model.searchProductProperty());
		txtProductDescription.textProperty().bindBidirectional(model.productDescriptionProperty());
		txtUdsStock.textProperty().bindBidirectional(model.udsStockProperty());
		txtQuantity.textProperty().bindBidirectional(model.quantityProperty());
		txtPrecioUd.textProperty().bindBidirectional(model.priceUnitProperty());

		imgProduct.imageProperty().bindBidirectional(model.imgProductProperty());

		listProducts.itemsProperty().bindBidirectional(model.listProductsProperty());
		model.productSelectedProperty().bind(listProducts.getSelectionModel().selectedItemProperty());
		
		imgProduct.imageProperty().bindBidirectional(model.imgProductProperty());		
		

		tableDetails.itemsProperty().bindBidirectional(model.listDetailsProperty());
		model.detailSelectedProperty().bind(tableDetails.getSelectionModel().selectedItemProperty());

		txtBase.textProperty().bindBidirectional(model.priceProperty());
		txtTotalToPay.textProperty().bind(model.priceTaxIncludedProperty());
		txtTaxPercentage.textProperty().bindBidirectional(model.taxPercentageProperty());
		txtTaxTotal.textProperty().bindBidirectional(model.taxTotalProperty());

		// Table invoices cells configuration
		columnDate.setCellValueFactory(v -> v.getValue().invoiceDateProperty());
		columnCustomer.setCellValueFactory(v -> v.getValue().customerProperty());
		columPrice.setCellValueFactory(v -> v.getValue().priceProperty());
		columnStatus.setCellValueFactory(v -> v.getValue().statusProperty());

		// Table details cells configuration
		columnProduct.setCellValueFactory(v -> v.getValue().productProperty());
		columnUds.setCellValueFactory(v -> v.getValue().quantityProperty());
		columnPriceUnit.setCellValueFactory(v -> v.getValue().priceUnitProperty());
		ColumnSubtotal.setCellValueFactory(v -> v.getValue().priceProperty());

		// LISTENERS
		tableInvoices.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onInvoiceSelected(nv));

		txtConcept.focusedProperty().addListener((o, ov, nv) -> onConceptChanged(nv));

		cmbTax.focusedProperty().addListener((o, ov, nv) -> onTaxChanged(nv));
		

		// radioButtons payMethod listeners		
		radioTransfer.focusedProperty().addListener((o, ov, nv) -> onPayMethodChanged(nv));
		radioCash.focusedProperty().addListener((o, ov, nv) -> onPayMethodChanged(nv));
		radioCredit.focusedProperty().addListener((o, ov, nv) -> onPayMethodChanged(nv));

		// radioButtons status listeners
		radioReleased.selectedProperty().addListener((o, ov, nv) -> onStatusChanged(nv));
		radioPending.selectedProperty().addListener((o, ov, nv) -> onStatusChanged(nv));
		radioAprobed.selectedProperty().addListener((o, ov, nv) -> onStatusChanged(nv));


		// list product listeners
		listProducts.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onProductSelected(nv));

		// TextFormatters and quantity listener
		textFieldFormmater(txtQuantity);
		txtQuantity.textProperty().addListener((o, ov, nv) -> onQuantityChanged(ov, nv));
		
		tableInvoices.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {

			long startTime;

			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
					
					startTime = System.currentTimeMillis();
						
				} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {

					if (System.currentTimeMillis() - startTime > 1 * 800) {

						deleteInvoice();

					} else {
						
						System.out.println(startTime);
						System.out.println("Pressed for " + (System.currentTimeMillis() - startTime) + " milliseconds");
					}

				}
			}
		});

		
		
		
		
		
		//tableDetails.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onDetailSelected(nv));
		//tableDetails.onMouseClickedProperty().addListener((o, ov, nv) -> onMousePressed(nv));
		//tableDetails.onMousePressedProperty().addListener((o, ov, nv) -> onMousePressed(nv));
		//tableDetails.onMouseReleasedProperty().addListener((o, ov, nv) -> onMousePressed(nv));
	
		tableDetails.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {

			long startTime;

			@Override
			public void handle(MouseEvent event) {
				if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
					
					startTime = System.currentTimeMillis();
						
				} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {

					if (System.currentTimeMillis() - startTime > 1 * 800) {

						deleteDetail();

					} else {
						
						System.out.println(startTime);
						System.out.println("Pressed for " + (System.currentTimeMillis() - startTime) + " milliseconds");
					}

				}
			}
		});
		
		try {
			updateContent();
		}catch (Exception e) {
		
		}
		
		

	}



	
	/**
	 * Constructor vacio
	 */
	public InvoicePresenter() {
	
	}

	/**
	 * Listener que detecta la invoice seleccionada y aplica 
	 * sus propiedades sobre el modelo
	 * @param nv invoiceSeleccionada
	 */
	private void onInvoiceSelected(Invoice nv) {
				

		this.invoiceMaster = nv;
		
		try {
			
						
			tabCurrentInvoice.setDisable(false);

			// Clean interface
			model.setCustomerName("");
			model.setCustomerAddress("");
			model.setCustomerPhone("");
			model.setCustomerNIF("");
			model.setConceptDescription("");
			model.setCustomerEmail("");
			model.setCustomerCity("");

			// Set customer information
			try {
				model.setCustomerName(invoiceMaster.getCustomer().getName());
			} catch (Exception e) {
			}
			try {
				model.setCustomerNIF(invoiceMaster.getCustomer().getCustomerId());
			} catch (Exception e) {
			}
			try {
				model.setCustomerAddress(invoiceMaster.getCustomer().getAddress());
			} catch (Exception e) {
			}
			try {
				model.setCustomerPhone(invoiceMaster.getCustomer().getPhone());
			} catch (Exception e) {
			}
			try {
				model.setCustomerEmail(invoiceMaster.getCustomer().getEmail());
			} catch (Exception e) {
			}			
			try {
				model.setCustomerCity(invoiceMaster.getCustomer().getCity());
			} catch (Exception e) {
			}

			// Set concept information
			try {
				model.setConceptDescription(invoiceMaster.getConceptInvoices().get(0).getDescription());
			} catch (Exception e) {
			}

			// set tax information
			try {

				for (Tax t : model.listTaxesProperty()) {
					if (t.getId() == invoiceMaster.getTax().getId()) {
						cmbTax.getSelectionModel().select(t); // I cant select with mvc in combo
					}
				}

			} catch (Exception e) {

			}

			// Set paymethod information
			if (invoiceMaster.getPayMethod().getId() == model.getPayMethodsList().get(1).getId()) {
				model.setCash(true);
				model.setPayMethodSelected(model.getPayMethodsList().get(1));
			} else if (invoiceMaster.getPayMethod().getId() == model.getPayMethodsList().get(2).getId()) {
				model.setCredit(true);
				model.setPayMethodSelected(model.getPayMethodsList().get(2));
			} else {
				model.setBankTransfer(true);
				model.setPayMethodSelected(model.getPayMethodsList().get(0));
			}

			// Status information

			if (invoiceMaster.getStatus() == 0) {
				model.setReleased(true);
			} else if (invoiceMaster.getStatus() == 1) {
				model.setPending(true);
			} else {
				model.setAprobed(true);
			}

			// Invoice details table
			try {
				model.setListDetails(invoiceMaster.getInvoiceDetails());				
			} catch (Exception e) {

			}

			// Invoice prices
			model.setPrice(String.valueOf(invoiceMaster.getPrice()));
			model.setTaxPercentage(String.valueOf(invoiceMaster.getTax().getPercentage()));
			model.setTaxTotal(String.valueOf(invoiceMaster.getTaxTotal()));
			model.setPriceTaxIncluded(String.valueOf(invoiceMaster.getPriceTaxesIncluded()));
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		

	}

	

	
	/**
	 * Listener que detecta el cambios en el en el estado de la fáctura
	 * realiza comparacion con los distintos estados del modelo y se los aplica
	 * al objeto invoiceMaster y actualiza el invoice en la BBDD
	 * @param nv cambio en el estado
	 */
	private void onStatusChanged(Boolean nv) {
		
		
		if(model.isReleased()) {
			invoiceMaster.setStatus(0);
		}else if(model.isPending()) {
			invoiceMaster.setStatus(1);
		}else {
			invoiceMaster.setStatus(2);
		}
		
		
		Invoice invoice = invoiceMaster;
		
		updateInvoice(invoice);

		
	}

	/**
	 * Listener que detecta cambios en en el tipo de tax
	 * y se los aplica al objeto invoiceMaster y actualiza el
	 * invoice en la BBDD
	 * @param nv
	 */
	private void onTaxChanged(Boolean nv) {
		
		if(!nv) {
			
			Invoice invoice = null;
			
			try {

				invoiceMaster.setTax(model.getTaxSelected());
				invoice = invoiceMaster;
				
				updateInvoice(invoice);

			} catch (Exception e) {

			}
		}

		

	}

	
	/**
	 * Listener que detecta cambios en el PayMethod a traves de
	 * los RadioButtons vinculados, realiza una comparacion y 
	 * aplica los cambios sobre el invoiceMaster, y actualiza el 
	 * invoice en la BDD
	 * @param nv
	 */
	private void onPayMethodChanged(Boolean nv) {
		
		
		if(!nv) {
			
						
			PayMethod payMethod = invoiceMaster.getPayMethod();
			
			if(payMethod == null) {
				payMethod = model.getPayMethodsList().get(0); //Write default payMethod
				invoiceMaster.setPayMethod(payMethod);
			}
			
			if(model.isBankTransfer()) {
				invoiceMaster.setPayMethod(model.getPayMethodsList().get(0));			
			}else if(model.isCash()) {
				invoiceMaster.setPayMethod(model.getPayMethodsList().get(1));
			}else {
				invoiceMaster.setPayMethod(model.getPayMethodsList().get(2));
			}
			

			Invoice invoice = invoiceMaster;
			
			updateInvoice(invoice);
		}
		
		
	}

	/**
	 * Listener que detecta cambios en el focus del concepto
	 * si detecta que hay caracteres actualiza el concepto en la bbdd
	 * @param nv
	 */
	private void onConceptChanged(Boolean nv) {

		ConceptInvoice conceptInvoice = null;
		Invoice invoice  = invoiceMaster;

		if (!nv) {

			try {

				conceptInvoice = invoiceMaster.getConceptInvoices().get(0);
				

			} catch (Exception e) {

			}

			if (model.getConceptDescription().length() > 0) {

				if (conceptInvoice != null) {
					
					conceptInvoice.setDescription(model.getConceptDescription());
					updateConceptInvoice(conceptInvoice, invoice);

				} else {
				
					conceptInvoice = new ConceptInvoice();
					conceptInvoice.setDescription(model.getConceptDescription());
					conceptInvoice.setPrice(0.0);

					insertConceptInvoice(conceptInvoice, invoice);

				}
			}

		}

	}

	
	/**
	 * Listener que detecta cambios en la cantidad del detalle,
	 * compara que la cantidad sea inferior al stock, en caso de ser
	 * superior, devuelve el antiguo valor al modelo
	 * @param ov antiguo valor
	 * @param nv nuevo valor
	 */
	private void onQuantityChanged(String ov, String nv) {

		try {

			double quantity = Double.parseDouble(nv);
			double stock = Double.parseDouble(model.getUdsStock());
			if (quantity > stock) {
				model.setQuantity(ov);
			}

		} catch (Exception e) {

		}

	}
	
	
	/**
	 * Detecta el producto seleccionado, limpia y actualiza el modelo 
	 * con el producto que se ha selccionado
	 * @param nv producto seleccionado
	 */
	private void onProductSelected(Product nv) {

		model.setProductDescription("");
		model.setUdsStock("");
		model.setQuantity("");
		model.setPriceUnit("");
		model.setImgProduct(null);

		try {
			model.setProductDescription(nv.getDescription());
		} catch (Exception e) {
		}
		try {
			model.setUdsStock(String.valueOf(nv.getStock()));
		} catch (Exception e) {
		}
		try {			
			model.setImgProduct(new Image(nv.getUrl()));			
		}catch (Exception e) {			
		}
		try {			
			model.setPriceUnit(String.valueOf(nv.getPrice()));		
		}catch (Exception e) {			
		}

	}

	
	/**
	 * Responde ante el evento de pulsar sobre el btnNewInvoice
	 * llama al metodo que se encarga de crear una nueva factura
	 * @param event
	 */	
	@FXML
	void onClickBtnNewInvoice(ActionEvent event) {
		
		createNewInvoice();
		
	}
	
	
	
	/**
	 * Responde al evento de pulsar sobre el boton btnSearchCustomer
	 * realiza una busqueda de cliente en base al id o nombre indicado
	 * en los campos de texto, en caso de encontrar cliente rellena la 
	 * vista con los resultados obtenidos en la busqueda
	 * @param event
	 */
	@FXML
	void onClickBtnSearchCustomer(ActionEvent event) {

		Customer result = null;

		// Search by name
		try {

			String name = model.getCustomerName();

			if (name.length() > 0) {

				for (Customer c : model.getListCustomers()) {

					if (c.getName().contains(name)) {
						result = c;
						break;
					}
				}

			}

		} catch (Exception e) {

		}

		// Search by NIF
		try {

			String nif = model.getCustomerNIF();

			if (nif.length() > 2) {

				for (Customer c : model.getListCustomers()) {

					if (c.getCustomerId().contains(nif)) {
						result = c;

						break;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (result != null) {

			invoiceMaster.setCustomer(result);

			model.setCustomerName(invoiceMaster.getCustomer().getName());
			model.setCustomerAddress(invoiceMaster.getCustomer().getAddress());
			model.setCustomerNIF(invoiceMaster.getCustomer().getCustomerId());
			model.setCustomerPhone(invoiceMaster.getCustomer().getPhone());
			model.setCustomerEmail(invoiceMaster.getCustomer().getEmail());
			model.setCustomerCity(invoiceMaster.getCustomer().getCity());

		} else {

			model.setCustomerName("");
			model.setCustomerAddress("");
			model.setCustomerNIF("");
			model.setCustomerPhone("");
			model.setCustomerEmail("");
			model.setCustomerCity("");

		}

	}
	

	/**
	 * Responde al invento de pulsar el bnSaveInvoiceInformation
	 * comprueba que el cliente exista o no y lo actualiza
	 * en la bbdd asociandolo a la factura actual
	 * @param event
	 */
	@FXML
	void onClickbtnSaveInvoiceInformation(ActionEvent event) {
		
								
			Customer customer = null;
			
			try {
				
				//Check if the customer exist				 
				for(Customer c : model.getListCustomers()) {
					
					if(c.getCustomerId().equals(model.getCustomerNIF())) {				
						customer = c; 		
						break;
					}		
				}
				
				if(customer == null) {
					customer = new Customer();
				}
				
				customer.setName(model.getCustomerName());
				customer.setCustomerId(model.getCustomerNIF());
				customer.setAddress(model.getCustomerAddress());
				customer.setPhone(model.getCustomerPhone());
				customer.setEmail(model.getCustomerEmail());
				customer.setCity(model.getCustomerCity());
				customer.setCountry("Spain");
				
				insertCustomer(customer, invoiceMaster);
				
				
			}catch (Exception e) {
				
			}

			
			
			
		
	}
	
	
	/**
	 * Responde al evento de pulsar sobre el btnSearchProduct
	 * realiza una busqueda de productos con coincidencias en el 
	 * nombre o el id, ordena la lista por coincidencias
	 * @param event
	 */
	@FXML
	void onClickBtnSearchProduct(ActionEvent event) {

		List<Product> listFinded = new ArrayList<Product>();
		List<Product> listNotFinded = new ArrayList<Product>();
		
		boolean results = false; 

		try {

			for (Product p : model.getListProducts()) {
				if (p.getName().contains(model.getSearchProduct())
						|| p.getProductId().contains(model.getSearchProduct())) {

					listFinded.add(p);
					results = true;
				} else {
					listNotFinded.add(p);
				}
			}

			listFinded.addAll(listNotFinded);

			System.out.println(listFinded.size());

			model.getListProducts().clear();			
			model.setListProducts(FXCollections.observableArrayList(listFinded));
			
			//If find result select the first coincidence
			if(results) {
				listProducts.getSelectionModel().select(0); //I cant select with model 
			}
			

		} catch (Exception e) {

		}
	}
	
	
	/**
	 * Responde al evento de pulsar sobre el btnAddDetail,
	 * agrega un nuevo detalle a la bbdd con los datos seleccionados
	 * de producto y cantidad
	 * @param event
	 */
	@FXML
	void onClickBtnAddDetail(ActionEvent event) {

		InvoiceDetail invoiceDetail = new InvoiceDetail();	
		invoiceDetail.setProduct(model.getProductSelected());
		invoiceDetail.setQuantity(Double.parseDouble(model.getQuantity()));
		
		insertInvoiceDetail(invoiceDetail, invoiceMaster);		
		
	}

	
	/**
	 * Crea una nueva factura con fecha actual, y tax, company, payMethod 
	 * por defecto
	 */	
	public void createNewInvoice() {
		
		Invoice invoice = new Invoice();		
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		Date today = Calendar.getInstance().getTime();
		String todayAsString = df.format(today);
	
		invoice.setInvoiceDate(todayAsString);
		
	
		invoice.setConceptId(1);
		invoice.setPrice(0.0);
		invoice.setPriceTaxesIncluded(0.0);
		invoice.setTaxTotal(0.0);
		invoice.setTax(DEFAULT_TAX);
		invoice.setCompany(DEFAULT_COMPANY);
		invoice.setPayMethod(DEFAULT_PAY_METHOD);
		invoice.setStatus(0);		
		
		insertInvoice(invoice);
		
		
		
	}
	
	
	
	/**
	 * Llama a los metodos que obtienen los datos de la bbdd
	 * y actualiza la vista con la ultima informacion
	 */
	public void updateContent() {

		
		saveSelectedIndex();		
		
		getAllInvoices();		
		getAllCustomers();
		getAllTaxes();
		getAllPayMethods();
		getAllProducts();
		getAllCompanies();


	}
	
	
	/**
	 * Guarda el ultipo indice selecciado sobre tableInvoices
	 */
	public void saveSelectedIndex() {
		try {			
			selectedIndex = tableInvoices.getSelectionModel().getSelectedIndex(); 			
		} catch (Exception e) {
		
		}
		
	}
	
	
	/**
	 * Selecciona el ultimo indice recordado sobre  tableInvoices
	 */
	public void rememberSelectedInvoice() {
		
		try {
			tableInvoices.getSelectionModel().select(selectedIndex);
		}catch (Exception e) {
			
		}
		
				
	}
	
	
	/**
	 * Elimina la factura seleccionada, un cuadro de dialogo
	 * pide informacion para confirmar el borrado
	 */
	public void deleteInvoice() {
		
		Invoice invoice = null;
		
		try {
			
			invoice = invoiceMaster;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(invoice != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar la factura seleccionada");
			alert.setGraphic(null);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				deleteInvoice(invoice);
			}
		}
		
	}
	
	
	/**
	 * Elimina el detalle seleccionado, un cuadro de dialogo 
	 * pide informacion para confirmar el borrado
	 */
	public void deleteDetail() {
		
		InvoiceDetail invoiceDetail = null;
		
		try {
			
			invoiceDetail = model.getDetailSelected();
						
		} catch (Exception e) {
			
		}
		
		if(invoiceDetail != null) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar el detalle seleccionado");						
			alert.setGraphic(null);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				
				deleteInvoiceDetail(invoiceDetail);
			   
			    
			} else {
			  
			}
		}
		
		
	}

	/**
	 * Metodo tipo TextFormater se encarga de que solo se puedan introducir valores
	 * de coma flotante en los textfield	
	 * @param TextField textField a aplicarle cambios
	 */

	public void textFieldFormmater(TextField txt) {
		DecimalFormat format = new DecimalFormat("#.0");
		txt.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}
			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));
	}
	
	
		

	/**
	 * Realiza consulta a bbdd de todos los productos, los aplica
	 * sobre el modelo, y recuerda la ultima factura seleccionada
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
            	rememberSelectedInvoice();
            
            
            }
        });        
		
	}

	

	/**
	 * Realiza consulta a bbdd de todos los impuestos, los aplica
	 * sobre el modelo, guarda el impuesto por defecto,
	 *  y recuerda la ultima factura seleccionada
	 */	
	private void getAllTaxes() {

		
		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
              .method("GET")
              .host("http://52.161.156.63:4132")                
              .path("/tax")                    
              .contentType("application/json;charset=UTF-8");

		// retrieve a list from the DataProvider
		GluonObservableList<Tax> result = DataProvider.retrieveList(restClient.createListDataReader(Tax.class));

		
        result.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv != null) {
              
            	model.setListTaxes(result);
            	DEFAULT_TAX = model.getListTaxes().get(0);
            	rememberSelectedInvoice();
            
            }
        });        
		
	}
	
	
	
	

	/**
	 * Realiza consulta a bbdd de todos los metodos de pago, los aplica
	 * sobre el modelo, guarda el metodo de pago por defecto,
	 *  y recuerda la ultima factura seleccionada
	 */		 
	private void getAllPayMethods() {

		
		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
              .method("GET")
              .host("http://52.161.156.63:4132")                
              .path("paymethod")                    
              .contentType("application/json;charset=UTF-8");

		// retrieve a list from the DataProvider
		GluonObservableList<PayMethod> result = DataProvider.retrieveList(restClient.createListDataReader(PayMethod.class));

		
        result.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv != null) {
              
            	model.setPayMethodsList(result);
            	DEFAULT_PAY_METHOD = model.getPayMethodsList().get(0);
            	rememberSelectedInvoice();
            
            	            
            }
        });        
		
	}
	

	
	/**
	 * Realiza consulta a bbdd de todos las empresas, los aplica
	 * sobre el modelo, guarda el la empresa por defecto,
	 *  y recuerda la ultima factura seleccionada
	 */	
	private void getAllCompanies() {

		
		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
              .method("GET")
              .host("http://52.161.156.63:4132")                
              .path("company")                    
              .contentType("application/json;charset=UTF-8");

		// retrieve a list from the DataProvider
		GluonObservableList<Company> result = DataProvider.retrieveList(restClient.createListDataReader(Company.class));

		
        result.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv != null) {
              
            	model.setListCompanies(result);
            	DEFAULT_COMPANY = model.getListCompanies().get(0);
            	rememberSelectedInvoice();
            }
        });        
		
	}
	
	
	/**
	 * Realiza consulta a bbdd de todos los clientes, los aplica
	 * sobre el modelo y recuerda la ultima factura seleccionada	
	 */	 
	private void getAllCustomers() {

		
		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
              .method("GET")
              .host("http://52.161.156.63:4132")                
              .path("customer")                    
              .contentType("application/json;charset=UTF-8");

		// retrieve a list from the DataProvider
		GluonObservableList<Customer> result = DataProvider.retrieveList(restClient.createListDataReader(Customer.class));

		
        result.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv != null) {
            	
            	model.setListCustomers(result);
            	rememberSelectedInvoice();
            	   
            }
        });        
		
	}
	
	/**
	 * Inserta un nuevo cliente en la bbdd y actualiza el contenido,
	 * hace uso de un Wrapper de customer e invoice para vincularlos
	 * asociarlos en la API REST
	 * @param customer nuevo cliente
	 */
	private void insertCustomer(Customer customer, Invoice invoice) {
		
		WrapperCustomerInvoice wrapper = new WrapperCustomerInvoice();
		wrapper.setCustomer(customer);
		wrapper.setInvoice(invoice);
	
				
		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("POST")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/customerInvoice")	            
	               .contentType("application/json");
	              
	      //Create the converters     	       
	       OutputStreamOutputConverter<WrapperCustomerInvoice> outputConverter = new JsonOutputConverter<>(WrapperCustomerInvoice.class);
	       InputStreamInputConverter<WrapperCustomerInvoice> inputConverter = new JsonInputConverter<>(WrapperCustomerInvoice.class);
	    	       
	       	       
	       //store object on the server
	       GluonObservableObject<WrapperCustomerInvoice> result = DataProvider.storeObject(wrapper, restClient.createObjectDataWriter(outputConverter, inputConverter));
	       
	       result.stateProperty().addListener((obs, ov, nv) -> {
	    	  
	    	   updateContent();
	    	   	
	        });  
	       
	}
	

	
	
	/**
	 * Inserta un nuevo concepto en la bbdd, encapsula la factura a la que esta asociado
	 * y al concepto en un wrapper. Ademas actualiza el contenido
	 * @param conceptInvoice nuevo concepto
	 * @param invoice factura del concepto
	 */
	private void insertConceptInvoice(ConceptInvoice conceptInvoice, Invoice invoice) {
	
		//Create and fill the wrapper		
		WrapperConceptInvoice wrapper = new WrapperConceptInvoice();
		wrapper.setInvoice(invoice);
		wrapper.setConceptInvoice(conceptInvoice);
		
	
		
		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("POST")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/conceptinvoice")	            
	               .contentType("application/json");
	              
	      //Create the converter     	       
	       OutputStreamOutputConverter<WrapperConceptInvoice> outputConverter = new JsonOutputConverter<>(WrapperConceptInvoice.class);
	       InputStreamInputConverter<WrapperConceptInvoice> inputConverter = new JsonInputConverter<>(WrapperConceptInvoice.class);
	    	       
	      	       	       
	       //store objects on the server
	        GluonObservableObject<WrapperConceptInvoice> result = DataProvider.storeObject(wrapper, restClient.createObjectDataWriter(outputConverter, inputConverter));
	      
		result.stateProperty().addListener((obs, ov, nv) -> {
				updateContent();
		});  
		     
		     
	}
	
	
	/**
	 * Actualiza un concepto existente en la bbdd, encapsula la factura a la que esta asociado
	 * y al concepto en un wrapper
	 * @param conceptInvoice concepto a actualizar
	 * @param invoice factura del concepto
	 */
	private void updateConceptInvoice(ConceptInvoice conceptInvoice, Invoice invoice) {
		

		// Create and fill the wrapper
		WrapperConceptInvoice wrapper = new WrapperConceptInvoice();
		wrapper.setInvoice(invoice);
		wrapper.setConceptInvoice(conceptInvoice);

		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("PUT")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/conceptinvoice")	            
	               .contentType("application/json;charset=utf8");
	              
	       //Create the converter     	       
	       OutputStreamOutputConverter<WrapperConceptInvoice> outputConverter = new JsonOutputConverter<>(WrapperConceptInvoice.class);
	       InputStreamInputConverter<WrapperConceptInvoice> inputConverter = new JsonInputConverter<>(WrapperConceptInvoice.class);
	    	       
	      	       	       
	       //store objects on the server
	       DataProvider.storeObject(wrapper, restClient.createObjectDataWriter(outputConverter, inputConverter));
		     
	}
	
	
	

	/**
	 * Inserta un nuevo detalle en la bbdd, encapsula su factura
	 * y el detalle en un wrapper. Ademas actualiza el contenido
	 * @param invoiceDetail
	 * @param invoice
	 */
	private void insertInvoiceDetail(InvoiceDetail invoiceDetail, Invoice invoice) {
		
		
		//Create and fill the wrapper		
		WrapperInvoiceDetail wrapper = new WrapperInvoiceDetail();
		wrapper.setInvoice(invoice);
		wrapper.setInvoiceDetail(invoiceDetail);
		
		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("POST")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/invoicedetail")	            
	               .contentType("application/json");
	              
	      //Create the converter     	       
	       OutputStreamOutputConverter<WrapperInvoiceDetail> outputConverter = new JsonOutputConverter<>(WrapperInvoiceDetail.class);
	       InputStreamInputConverter<WrapperInvoiceDetail> inputConverter = new JsonInputConverter<>(WrapperInvoiceDetail.class);
	    	       
	      	       	       
	       //store objects on the server
	       GluonObservableObject<WrapperInvoiceDetail> result = DataProvider.storeObject(wrapper, restClient.createObjectDataWriter(outputConverter, inputConverter));
	       
	       result.stateProperty().addListener((obs, ov, nv) -> {
	    	   
	    	   updateContent();
	 	      
	        });   
	       
	       
		     
	}
	
	/**
	 * Actualiza un nuevo detalle en la bbdd, encapsula su factura
	 * y el detalle en un wrapper. Ademas actualiza el contenido
	 * @param invoiceDetail
	 * @param invoice
	 */
	@SuppressWarnings("unused")
	private void updateInvoiceDetail(InvoiceDetail invoiceDetail, Invoice invoice) {
		

		// Create and fill the wrapper
		WrapperInvoiceDetail wrapper = new WrapperInvoiceDetail();
		wrapper.setInvoice(invoice);
		wrapper.setInvoiceDetail(invoiceDetail);

		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("PUT")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/invoicedetail")	            
	               .contentType("application/json;charset=utf8");
	              
	       //Create the converter     	       
	       OutputStreamOutputConverter<WrapperInvoiceDetail> outputConverter = new JsonOutputConverter<>(WrapperInvoiceDetail.class);
	       InputStreamInputConverter<WrapperInvoiceDetail> inputConverter = new JsonInputConverter<>(WrapperInvoiceDetail.class);
	    	       
	      	       	       
	       //store objects on the server
	       DataProvider.storeObject(wrapper, restClient.createObjectDataWriter(outputConverter, inputConverter));
		     
	}
	
	
	/**
	 * Elimina una detalle de factura existente de la bbdd y actualiza el contenido
	 * @param invoiceDetail factura a eliminar
	 */
	private void deleteInvoiceDetail(InvoiceDetail invoiceDetail) {
		
		
		// create a RestClient to the specific URL
        RestClient restClient = RestClient.create()
                .method("DELETE")
                .host("http://52.161.156.63:4132")                
                .path("invoicedetail/"+invoiceDetail.getId())                   
                .contentType("application/json;charset=UTF-8");
                
        
        // create an observable object
        GluonObservableObject<InvoiceDetail> result = new GluonObservableObject<>(); 
        result.set(invoiceDetail);	
        
        //Create the converters
        OutputStreamOutputConverter<InvoiceDetail> outputConverter = new JsonOutputConverter<>(InvoiceDetail.class);
	    InputStreamInputConverter<InvoiceDetail> inputConverter = new JsonInputConverter<>(InvoiceDetail.class);      
	        
	    //Delete the object 
        DataProvider.removeObject(result, restClient.createObjectDataRemover(outputConverter, inputConverter));
        
        result.stateProperty().addListener((obs, ov, nv) -> {
    	   	
	    	   updateContent();
	    	   	
	        });  
    
		
	}
	
	
	/**
	 * Realiza consulta a bbdd de todos las facturas, los aplica
	 * sobre el modelo, guarda el la empresa por defecto,
	 *  y recuerda la ultima factura seleccionada
	 */	
	private void getAllInvoices() {

		
		// create a RestClient to the specific URL
		RestClient restClient = RestClient.create()
              .method("GET")
              .host("http://52.161.156.63:4132")                
              .path("invoice")                    
              .contentType("application/json;charset=UTF-8");

		// retrieve a list from the DataProvider
		GluonObservableList<Invoice> result = DataProvider.retrieveList(restClient.createListDataReader(Invoice.class));

		
        result.initializedProperty().addListener((obs, ov, nv) -> {
            if (nv != null) {
            	            
            	Collections.reverse(result); //Order by date            	
            	model.setListInvoices(result);
            	rememberSelectedInvoice();
 
            }
        });        
		
	}
	
	/**
	 * Inserta una nueva factura en la bbdd y actualiza el contenido
	 * @param invoice nueva factura
	 */
	private void insertInvoice(Invoice invoice) {
		
						
		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("POST")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/invoice")	            
	               .contentType("application/json");
	              
	      //Create the converters     	       
	       OutputStreamOutputConverter<Invoice> outputConverter = new JsonOutputConverter<>(Invoice.class);
	       InputStreamInputConverter<Invoice> inputConverter = new JsonInputConverter<>(Invoice.class);
	    	       
	       	       
	       //store object on the server
	       GluonObservableObject<Invoice> result  = DataProvider.storeObject(invoice, restClient.createObjectDataWriter(outputConverter, inputConverter));
	       
	       result.stateProperty().addListener((obs, ov, nv) -> {
	    	   	
	    	   updateContent();
	    	   	
	        });        
	      
		     
	}
	
	/**
	 * Actualiza una factura existente en la bbdd y renueva el contenido
	 * @param invoice factura a actualizar
	 */
	private void updateInvoice(Invoice invoice) {
		
					
		  // create a RestClient to the specific URL
	       RestClient restClient= RestClient.create()
	               .method("PUT")	               
	               .host("http://52.161.156.63:4132")  	               
	               .path("/invoice")	            
	               .contentType("application/json;charset=utf8");
	              
	      //Create the converters     	       
	       OutputStreamOutputConverter<Invoice> outputConverter = new JsonOutputConverter<>(Invoice.class);
	       InputStreamInputConverter<Invoice> inputConverter = new JsonInputConverter<>(Invoice.class);
	    	       
	       	       
	       //store object on the server
	       GluonObservableObject<Invoice> result = DataProvider.storeObject(invoice, restClient.createObjectDataWriter(outputConverter, inputConverter));
	       
	       result.stateProperty().addListener((obs, ov, nv) -> {
	    	  
	    	   updateContent();
	    	   	
	        });  
		     
	}
	
	
	/**
	 * Elimina una factura existente y renueva el contenido
	 * @param invoice factura a eliminar
	 */
	private void deleteInvoice(Invoice invoice) {
		
		
		// create a RestClient to the specific URL
        RestClient restClient = RestClient.create()
                .method("DELETE")
                .host("http://52.161.156.63:4132")                
                .path("invoice/"+invoice.getId())                   
                .contentType("application/json;charset=UTF-8");
                
        
        // create an observable object
        GluonObservableObject<Invoice> result = new GluonObservableObject<>(); 
        result.set(invoice);	
        
        //Create the converters
        OutputStreamOutputConverter<Invoice> outputConverter = new JsonOutputConverter<>(Invoice.class);
	    InputStreamInputConverter<Invoice> inputConverter = new JsonInputConverter<>(Invoice.class);      
	        
	    //Delete the object 
        DataProvider.removeObject(result, restClient.createObjectDataRemover(outputConverter, inputConverter));
        
        result.stateProperty().addListener((obs, ov, nv) -> {
    	   	
	    	   updateContent();
	    	   	
	        });  
    		
	}
	
	
	/**
	 * Genera un pdf en base a la factura seleccinado, realizando llamada a la bbdd
	 * decodifica String b64 y crea el archivo. Para PC  usa fileChooser para almacenarlo,
	 * para movil da al usuario la opcion de compartir el fichero
	 */
	public void generarPDF() {		
		
		
		// create a RestClient to the specific URL
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://52.161.156.63:4132")                
                .path("pdf/"+invoiceMaster.getId())                   
                .contentType("application/json;charset=UTF-8");
                
        
        // retrieve an object from the DataProvider
        GluonObservableObject<InvoicePDF> result = DataProvider.retrieveObject(restClient.createObjectDataReader(InvoicePDF.class));       
               
        // show information on the retrieved and inicialize a global object   
        result.initializedProperty().addListener((obs, ov, nv) -> {
        	
        	
            if (nv != null) {     
            	
            	String b64 = result.get().getBase64();
            	String fileName = result.get().getInvoiceId();
            	
            	if(Platform.isDesktop()) {
            		
                	if(b64 != null) {
                	
                		FileChooser fileChooser = new FileChooser();
                		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
                		fileChooser.setInitialFileName(fileName.concat(".pdf"));

                		pdfFile = fileChooser.showSaveDialog(null);

                		if (pdfFile != null) {		
                			
                			if(!pdfFile.getName().contains(".pdf")) { //Nos aseguramos que contenga extension
                				  pdfFile = new File(pdfFile.getAbsolutePath() + ".pdf"); //Se le asigna extension
                			}

                				try {                					
                					B64Util.decoder(b64, pdfFile.getAbsolutePath());    
                				} catch (Exception e) {
                					e.printStackTrace();
                				}
                			
                		}
                		           		
					}

				} else {
										
					
					 pdfFile = Services.get(StorageService.class)
				            .flatMap(s -> s.getPublicStorage(fileName))							
				            .orElseThrow(() -> new RuntimeException("Folder notavailable")); 
								

            		if (pdfFile != null) {		
            			
            			if(!pdfFile.getName().contains(".pdf")) { //Nos aseguramos que contenga extension
            				  pdfFile = new File(pdfFile.getAbsolutePath() + ".pdf"); //Se le asigna extension xml
            			}

            				try {                					
            					B64Util.decoder(b64, pdfFile.getAbsolutePath());   
            					
            					// Share file
            			        if (pdfFile != null) {
            			            Services.get(ShareService.class).ifPresent(share -> {
            			                share.share("document/pdf", pdfFile);
            			            });
            					
            			        }
            				} catch (Exception e) {
            					e.printStackTrace();
            				}
            			
            		}
								
				}
                       
            }
        });        
             

	}
}

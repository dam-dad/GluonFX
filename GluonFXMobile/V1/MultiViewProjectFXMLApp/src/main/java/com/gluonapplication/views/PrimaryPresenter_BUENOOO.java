package com.gluonapplication.views;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.sound.midi.Soundbank;

import org.hibernate.mapping.Array;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.model.beans.CompanyBean;
import com.model.beans.CustomerBean;
import com.model.beans.InvoiceBean;
import com.model.beans.InvoiceDetailBean;
import com.model.beans.PayMethodBean;
import com.model.beans.PrimaryModel;
import com.model.beans.ProductBean;
import com.model.beans.TaxBean;
import com.model.entities.Company;
import com.model.entities.ConceptInvoice;
import com.model.entities.Customer;
import com.model.entities.Invoice;
import com.model.entities.InvoiceDetail;
import com.model.entities.PayMethod;
import com.model.entities.Product;
import com.model.entities.Tax;
import com.utils.HibernateController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class PrimaryPresenter_BUENOOO {

	@FXML
	private View primary;

	@FXML
	private Tab tabInvoices;

	@FXML
	private TableView<InvoiceBean> tableInvoices; // OK

	@FXML
	private TableColumn<InvoiceBean, LocalDate> columnDate;

	@FXML
	private TableColumn<InvoiceBean, CustomerBean> columnCustomer;

	@FXML
	private TableColumn<InvoiceBean, Number> columPrice;

	@FXML
	private TableColumn<InvoiceBean, Number> columnStatus;

	@FXML
	private Button btnNewInvoice;

	@FXML
	private Tab tabCurrentInvoice;

	@FXML
	private TextField txtCustomerName; // TODO

	@FXML
	private TextField txtNIF;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtPhone;

	@FXML
	private Button btnSearchCustomer;

	@FXML
	private TextArea txtConcept;

	@FXML
	private ComboBox<TaxBean> cmbTax;

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
	private RadioButton radioSended;

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
	private TextArea txtProductDescription;

	@FXML
	private TextField txtUdsStock;

	@FXML
	private TextField txtQuantity;

	@FXML
	private Button btnAddDetail;

	@FXML
	private ListView<ProductBean> listProducts;

	@FXML
	private TableView<InvoiceDetailBean> tableDetails;

	@FXML
	private TableColumn<InvoiceDetailBean, ProductBean> columnProduct;

	@FXML
	private TableColumn<InvoiceDetailBean, Number> columnUds;

	@FXML
	private TableColumn<InvoiceDetailBean, Number> columnPriceUnit;

	@FXML
	private TableColumn<InvoiceDetailBean, Number> ColumnSubtotal;

	@FXML
	private TextField txtBase;

	@FXML
	private TextField txtTotalToPay;

	@FXML
	private TextField txtTaxPercentage;

	@FXML
	private TextField txtTaxTotal;

	// Model
	private PrimaryModel model = new PrimaryModel();
	private InvoiceBean invoiceMaster; // Is the selected invoice in the model, but is easy to work this injected object
	
	private Company DEFAULT_COMPANY; 
	private Tax DEFAULT_TAX;
	private PayMethod DEFAULT_PAY_METHOD;
	
	
	// neccesary
	HibernateController hibernate = new HibernateController();

	public void initialize() {
		
	
		primary.showingProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue) {
				AppBar appBar = MobileApplication.getInstance().getAppBar();
				appBar.setNavIcon(
						MaterialDesignIcon.MENU.button(e -> MobileApplication.getInstance().getDrawer().open()));
				appBar.setTitleText("Facturas");
				appBar.getActionItems()				
						.add(MaterialDesignIcon.PICTURE_AS_PDF.button(e -> System.out.println("Search")));
			}
		});

		// BINDINGS
		tableInvoices.itemsProperty().bindBidirectional(model.listInvoicesProperty());
		model.invoiceSelectedProperty().bind(tableInvoices.getSelectionModel().selectedItemProperty());

		txtCustomerName.textProperty().bindBidirectional(model.customerNameProperty());
		txtNIF.textProperty().bindBidirectional(model.customerNIFProperty());
		txtAddress.textProperty().bindBidirectional(model.customerAddressProperty());
		txtPhone.textProperty().bindBidirectional(model.customerPhoneProperty());

		txtConcept.textProperty().bindBidirectional(model.conceptDescriptionProperty());

		cmbTax.itemsProperty().bindBidirectional(model.listTaxesProperty());
		model.taxSelectedProperty().bind(cmbTax.getSelectionModel().selectedItemProperty());

		radioCash.selectedProperty().bindBidirectional(model.cashProperty());
		radioTransfer.selectedProperty().bindBidirectional(model.bankTransferProperty());
		radioCredit.selectedProperty().bindBidirectional(model.creditProperty());

		radioSended.selectedProperty().bindBidirectional(model.sendedProperty());
		radioPending.selectedProperty().bindBidirectional(model.pendingProperty());
		radioAprobed.selectedProperty().bindBidirectional(model.AprobedProperty());

		txtSearchProduct.textProperty().bindBidirectional(model.searchProductProperty());
		txtProductDescription.textProperty().bindBidirectional(model.productDescriptionProperty());
		txtUdsStock.textProperty().bindBidirectional(model.udsStockProperty());
		txtQuantity.textProperty().bindBidirectional(model.quantityProperty());

		imgProduct.imageProperty().bindBidirectional(model.imgProductProperty());

		listProducts.itemsProperty().bindBidirectional(model.listProductsProperty());
		model.productSelectedProperty().bind(listProducts.getSelectionModel().selectedItemProperty());

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
		radioSended.focusedProperty().addListener((o, ov, nv) -> onStatusChanged(nv));
		radioPending.focusedProperty().addListener((o, ov, nv) -> onStatusChanged(nv));
		radioAprobed.focusedProperty().addListener((o, ov, nv) -> onStatusChanged(nv));

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

	}





	public PrimaryPresenter_BUENOOO() {
		hibernate.start();
		updateContent();

	}

	private void onInvoiceSelected(InvoiceBean nv) {

		this.invoiceMaster = nv;
		
		tabCurrentInvoice.setDisable(false);

		// Clean interface
		model.setCustomerName("");
		model.setCustomerAddress("");
		model.setCustomerPhone("");
		model.setCustomerNIF("");
		model.setConceptDescription("");

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

		// Set concept information
		try {
			model.setConceptDescription(invoiceMaster.getConceptInvoices().get(0).getDescription());
		} catch (Exception e) {
		}

		// set tax information
		try {

			for (TaxBean t : model.listTaxesProperty()) {
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

		if (invoiceMaster.getStatus() == 2) {
			model.setAprobed(true);
		} else if (invoiceMaster.getStatus() == 1) {
			model.setPending(true);
		} else {
			model.setSended(true);
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

	}

	
	
	
	private void onDetailSelected(InvoiceDetailBean nv) {
		
//		Alert alert = new Alert(AlertType.CONFIRMATION);
//		alert.setTitle("Eliminar detalle");
//		alert.setHeaderText("Eliminar detalle");
//		alert.setContentText("Seguro que desea eliminarlo");
//
//		Optional<ButtonType> result = alert.showAndWait();
//		if (result.get() == ButtonType.OK){
//		    // ... user chose OK
//		} else {
//		    alert.close();
//		}
	}
	
	/*
	 * When invoice status get or lost the focus update the bbdd
	 */

	private void onStatusChanged(Boolean nv) {

		Invoice invoice = null;
		int status = 0;

		try {

			if (model.isAprobed()) {
				status = 2;
			} else if (model.isPending()) {
				status = 1;
			} else {
				status = 0;
			}

			invoiceMaster.setStatus(status);

			invoice = invoiceMaster.getInvoice();

			hibernate.saveOrUpdate(invoice);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * When taxCombo get or lost the focus update bbdd
	 */

	private void onTaxChanged(Boolean nv) {

		Invoice invoice = null;
		Tax tax = null;

		try {

			invoiceMaster.setTax(model.getTaxSelected());

			invoice = invoiceMaster.getInvoice();
			tax = invoiceMaster.getTax().getTax();
			invoice.setTax(tax);

			hibernate.saveOrUpdate(invoice);
			System.out.println(invoice.getTax().getDescription());
			updateContent();

		} catch (Exception e) {

		}

	}

	/*
	 * When the payMetod radiobuttons lost6 the focus update bbdd
	 */

	private void onPayMethodChanged(Boolean nv) {

		Invoice invoice = null;
		PayMethod payMethod = null;

		if (!nv) {

			try {

				invoice = invoiceMaster.getInvoice();
				payMethod = invoice.getPayMethod();

				if (payMethod == null) {
					payMethod = model.getPayMethodsList().get(0).getPayMethod();
				}

				if (model.isCash()) {
					model.setPayMethodSelected(model.getPayMethodsList().get(1));
					payMethod = model.getPayMethodSelected().getPayMethod();
				} else if (model.isCredit()) {
					model.setPayMethodSelected(model.getPayMethodsList().get(2));
					payMethod = model.getPayMethodSelected().getPayMethod();
				} else {
					model.setPayMethodSelected(model.getPayMethodsList().get(0));
					payMethod = model.getPayMethodSelected().getPayMethod();
				}

				invoice.setPayMethod(payMethod);
				hibernate.saveOrUpdate(invoice);
				updateContent();

			} catch (Exception e) {

			}

		}
	}

	/*
	 * When concept los the focus update bbdd
	 */

	private void onConceptChanged(Boolean nv) {

		ConceptInvoice concept = null;
		Invoice invoice = null;

		if (!nv) {

			try {
				invoice = invoiceMaster.getInvoice();
				concept = invoice.getConceptInvoices().get(0);

			} catch (Exception e) {

			}

			if (model.getConceptDescription().length() > 0) {

				if (concept != null) {
					System.out.println("Tiene concepto");
					concept.setDescription(model.getConceptDescription());

					hibernate.saveOrUpdate(concept);

					updateContent();

				} else {
					System.out.println("Have not concept");
					concept = new ConceptInvoice();
					concept.setInvoice(invoice);
					concept.setDescription(model.getConceptDescription());
					concept.setPrice(0.0);
					hibernate.save(concept);
					updateContent();
				}
			}

		}

	}

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

	private void onProductSelected(ProductBean nv) {

		model.setProductDescription("");
		model.setUdsStock("");
		model.setQuantity("");

		try {
			model.setProductDescription(nv.getDescription());
		} catch (Exception e) {
		}
		try {
			model.setUdsStock(String.valueOf(nv.getStock()));
		} catch (Exception e) {
		}

	}

	// Action events
	
	
	
	@FXML
	void onClickBtnNewInvoice(ActionEvent event) {
		
		createNewInvoice();
		
	}
	
	
	@FXML
	void onClickBtnSearchCustomer(ActionEvent event) {

		CustomerBean result = null;

		// Search by name
		try {

			String name = model.getCustomerName();

			if (name.length() > 0) {

				for (CustomerBean c : model.getListCustomers()) {

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

				for (CustomerBean c : model.getListCustomers()) {

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

		} else {

			model.setCustomerName("");
			model.setCustomerAddress("");
			model.setCustomerNIF("");
			model.setCustomerPhone("");

		}

	}
	


	@FXML
	void onClickbtnSaveInvoiceInformation(ActionEvent event) {

		Customer customer = new Customer();
		customer.setName(model.getCustomerName());
		customer.setCustomerId(model.getCustomerNIF());
		customer.setAddress(model.getCustomerAddress());
		customer.setPhone(model.getCustomerPhone());

		System.out.println(customer);

		invoiceMaster.setCustomer(new CustomerBean(customer));

		System.out.println(invoiceMaster.getCustomer().getName());

	}

	@FXML
	void onClickBtnSearchProduct(ActionEvent event) {

		List<ProductBean> listFinded = new ArrayList<ProductBean>();
		List<ProductBean> listNotFinded = new ArrayList<ProductBean>();

		try {

			for (ProductBean p : model.getListProducts()) {
				if (p.getName().contains(model.getSearchProduct())
						|| p.getProductId().contains(model.getSearchProduct())) {

					listFinded.add(p);
				} else {
					listNotFinded.add(p);
				}
			}

			listFinded.addAll(listNotFinded);

			System.out.println(listFinded.size());

			model.getListProducts().clear();
			model.setListProducts(FXCollections.observableArrayList(listFinded));

			System.out.println("ANADIDA");

		} catch (Exception e) {

		}
	}

	@FXML
	void onClickBtnAddDetail(ActionEvent event) {

		InvoiceDetail detail = new InvoiceDetail();
		detail.setInvoice(invoiceMaster.getInvoice());
		detail.setProduct(model.getProductSelected().getProduct());
		detail.setQuantity(Double.parseDouble(model.getQuantity()));

		hibernate.saveOrUpdate(detail);
		updateContent();

	}

	// My methods

	public void selectAllInvoices() {

		try {
			model.getListInvoices().clear();
		} catch (Exception e) {

		}

		List<Invoice> list = hibernate.selectAll("Invoice");
		List<InvoiceBean> listBeans = new ArrayList<>();

		for (Invoice i : list) {
			listBeans.add(new InvoiceBean(i));
		}
		Collections.reverse(listBeans);

		model.setListInvoices(FXCollections.observableArrayList(listBeans));

	}

	public void selectAllCustomers() {

		List<Customer> list = hibernate.selectAll("Customer");
		List<CustomerBean> listBeans = new ArrayList<>();

		for (Customer i : list) {
			listBeans.add(new CustomerBean(i));
		}

		model.setListCustomers(FXCollections.observableArrayList(listBeans));

	}

	public void selectAllTaxes() {

		List<Tax> list = hibernate.selectAll("Tax");
		List<TaxBean> listBeans = new ArrayList<>();

		for (Tax t : list) {
			listBeans.add(new TaxBean(t));
		}
		
		DEFAULT_TAX = list.get(0);
		model.setListTaxes(FXCollections.observableArrayList(listBeans));

	}

	public void selectAllProducts() {
		List<Product> list = hibernate.selectAll("Product");
		List<ProductBean> listBeans = new ArrayList<>();

		for (Product p : list) {
			listBeans.add(new ProductBean(p));
		}

		model.setListProducts(FXCollections.observableArrayList(listBeans));

	}

	public void selectAllPayMethods() {

		List<PayMethod> list = hibernate.selectAll("PayMethod");
		List<PayMethodBean> listBeans = new ArrayList<>();

		for (PayMethod p : list) {
			listBeans.add(new PayMethodBean(p));
		}

		DEFAULT_PAY_METHOD = list.get(0);
		
		model.setPayMethodsList(FXCollections.observableArrayList(listBeans));

		
	}
	
	
	public void selectAllCompanies() {
		
		List<Company> list = hibernate.selectAll("Company");
		List<CompanyBean> listBeans = new ArrayList<>();
		
		for(Company c : list) {
			listBeans.add(new CompanyBean(c));			
		}
		
		DEFAULT_COMPANY = list.get(0);
		model.setListCompanies(FXCollections.observableArrayList(listBeans));
		
		
		
		
	}
	
	
	
	public void createNewInvoice() {
		
		Invoice invoice = new Invoice();
		
		Date today = Calendar.getInstance().getTime();
		invoice.setInvoiceDate(today);		
			
		invoice.setInvoiceDate(new Date());	
		invoice.setConceptId(1);
		invoice.setPrice(0.0);
		invoice.setPriceTaxesIncluded(0.0);
		invoice.setTaxTotal(0.0);
		invoice.setTax(DEFAULT_TAX);
		invoice.setCompany(DEFAULT_COMPANY);
		invoice.setPayMethod(DEFAULT_PAY_METHOD);
		invoice.setStatus(0);		
		hibernate.save(invoice);
		
		updateContent();
		
	}
	
	
	

	public void updateContent() {

		boolean selected; // true if an invoice is selected
		int selectedIndex = 0; // save the current index

		// check if invoice is selected and save the index
		try {
			if (tableInvoices.getSelectionModel().getSelectedItem() != null) {
				selectedIndex = tableInvoices.getSelectionModel().getSelectedIndex();
				selected = true;
			} else {
				selected = false;
			}

		} catch (Exception e) {
			selected = false;
		}

		selectAllInvoices();
		selectAllCustomers();
		selectAllTaxes();
		selectAllPayMethods();
		selectAllProducts();
		selectAllCompanies();

		// After update the content check if invoice was selected and select again
		if (selected) {
			tableInvoices.getSelectionModel().select(selectedIndex);
		}

	}
	
	
	public void deleteInvoice() {
		
		Invoice invoice = null;
		
		try {
			
			invoice = invoiceMaster.getInvoice();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(invoice != null) {

			Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar el detalle seleccionado");						
			alert.setGraphic(null);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){				
			    hibernate.delete(invoice);
			    updateContent();
			} else {
			  
			}
		}
		
	}
	
	
	public void deleteDetail() {
		
		InvoiceDetail invoiceDetail = null;
		
		try {
			
			invoiceDetail = model.getDetailSelected().getInvoiceDetail();
						
		} catch (Exception e) {
			
		}
		
		if(invoiceDetail != null) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Desea eliminar el detalle seleccionado");						
			alert.setGraphic(null);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				
			    hibernate.delete(invoiceDetail);
			    updateContent();
			} else {
			  
			}
		}
		
		
	}

	/**
	 * Metodo tipo TextFormater se encarga de que solo se puedan introducir valores
	 * de coma flotante en los textfield
	 * 
	 * @param TextField txt
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

}

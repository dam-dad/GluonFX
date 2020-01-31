package fx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import fx.beans.CompanyBean;
import fx.beans.CustomerBean;
import fx.beans.InvoiceBean;
import fx.beans.ProductBean;
import hibernate.HibernateController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class InvoiceController implements Initializable {

	@FXML
    private VBox view;

    @FXML
    private GridPane tableGrid;

    @FXML
    private TableView<InvoiceBean> tableInvoices;

    @FXML
    private TableColumn<?, ?> columnNumeroFactura;

    @FXML
    private TableColumn<?, ?> columnFecha;

    @FXML
    private TableColumn<?, ?> columnCliente;

    @FXML
    private JFXButton addInvoice;

    @FXML
    private JFXButton delInvoice;

    @FXML
    private JFXButton leftHideBttn;

    @FXML
    private ImageView leftHideImage;

    @FXML
    private TextField invoiceIDTxt;

    @FXML
    private TextField dateTxt;

    @FXML
    private TextField nameClientTxt;

    @FXML
    private TextField idClientTxt;

    @FXML
    private TextField directionclientTxt;

    @FXML
    private TextField cpClientTxt;

    @FXML
    private TextField emailClientTxt;

    @FXML
    private TextField tlpClientTxt;

    @FXML
    private ImageView companyIcon;

    @FXML
    private TextArea conceptArea;

    @FXML
    private JFXTextField priceTxt;

    @FXML
    private JFXButton addProductBttn;

    @FXML
    private TableView<?> productsTable;

    @FXML
    private Label productsTotalLbl;

    @FXML
    private Label taxLbl;

    @FXML
    private Label totalLbl;

    @FXML
    private JFXComboBox<?> taxCombo;

    @FXML
    private Label taxPercentageLbl;

    @FXML
    private JFXButton rightHideBttn;

    @FXML
    private ImageView rightHideImage;

    @FXML
    private VBox configurationBox;

    @FXML
    private JFXButton invoiceStatusBttn;

    @FXML
    private VBox invoiceStatusBox;

    @FXML
    private JFXCheckBox createdCheck;

    @FXML
    private JFXCheckBox pendingCheck;

    @FXML
    private JFXCheckBox chargedCheck;

    @FXML
    private JFXCheckBox overdueCheck;

    @FXML
    private JFXButton clientSelectBttn;

    @FXML
    private VBox clientSelectBox;

    @FXML
    private ComboBox<?> clientSelectCombo;

    @FXML
    private JFXButton paymentMethodBttn;

    @FXML
    private VBox paymentMethodBox;

    @FXML
    private JFXCheckBox cashCheck;

    @FXML
    private JFXCheckBox bankTransferCheck;

    @FXML
    private JFXCheckBox creditCheck;

    @FXML
    private JFXButton generatePDFBttn;
    
    @FXML
    private TableColumn<?, ?> columnProduct;

    @FXML
    private TableColumn<?, ?> columnUds;

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private TableColumn<?, ?> columnSubtotal;

    @FXML
    private TableColumn<?, ?> columnActions;

	
	
	
	//hibernate
	private HibernateController hibernate;
	
	//Main Lists
	private List<InvoiceBean> listInvoices = new ArrayList<InvoiceBean>();	
	private List<CompanyBean> listCompanies = new ArrayList<CompanyBean>(); 
	private List<CustomerBean> listCustomer = new ArrayList<CustomerBean>();
	private List<ProductBean> listProducts = new ArrayList<ProductBean>();
	
	
	
	//Beans and atributes for current invoice
	private InvoiceBean masterInvoiceBean; //The most important bean he is the fucking master

//	private List<InvoiceDetailBean> listInvoiceDetails = new ArrayList<InvoiceDetailBean>();
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Insert list of status
		insertListOfStatus();
		
		//table invoice configuration
		columnNumeroFactura.setCellValueFactory(v -> v.getValue().invoiceNumberProperty());
		columnFecha.setCellValueFactory(v -> v.getValue().invoiceDateProperty());
		columnCliente.setCellValueFactory(v -> v.getValue().customerProperty());
		
		//table invoice listener
		tableInvoices.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onInvoiceSelected(nv)); 
		
		//table details configuration;
		columnProduct.setCellValueFactory(v -> v.getValue().productProperty());
		columnUds.setCellValueFactory(v -> v.getValue().quantityProperty());
		columnPrice.setCellValueFactory(v -> v.getValue().priceUnitProperty());
		columnSubtotal.setCellValueFactory(v -> v.getValue().priceProperty());
		
		

	}
	
	

	public InvoiceController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvoiceView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	
	//injectors
	public void injectHibernate(HibernateController hibernate) {
		this.hibernate = hibernate;		
	}
	
	/*
	 * Every change call this method for update all the UI
	 */
	public void updateContent() {
		selectAllInvoices();
		selectAllCompanys();
		selectAllCustomers();
		selectAllProducts();

	}

	/*
	 * Make a query for get all invoices from bbdd and update the corresponding list
	 */
	public void selectAllInvoices() {
	
		listInvoices = new ArrayList<InvoiceBean>();
		
		List<Invoice> list =  hibernate.selectAll("Invoice");
			
		for(Invoice i: list) {
			listInvoices.add(new InvoiceBean(i));
		}

		tableInvoices.setItems(FXCollections.observableArrayList(listInvoices));
		
	}
	
	/*
	 * Make a query for get all companies from bbdd and update the corresponding list
	 */
	public void selectAllCompanys() {
		
		listCompanies = new ArrayList<CompanyBean>();
		
		List<Company> list = hibernate.selectAll("Company");
		
		for (Company c : list) {
			listCompanies.add(new CompanyBean(c));
		}
		
		cmbCompany.setItems(FXCollections.observableArrayList(listCompanies));		
		
	}
	
	/*
	 * Make a query for get all customers from bbdd and update the corresponding list
	 */	
	public void selectAllCustomers() {
		
		listCustomer = new ArrayList<CustomerBean>();
		
		List<Customer> list =  hibernate.selectAll("Customer");		
	
		for (Customer c : list) {
			listCustomer.add(new CustomerBean(c));
		}
	
		cmbCustomer.setItems(FXCollections.observableArrayList(listCustomer));
				
	}
	
	/*
	 * Make a query for get all customers from bbdd and update the corresponding list
	 */	
	public void selectAllProducts() {
		
		listProducts = new ArrayList<ProductBean>();
		
		List<Product> list =  hibernate.selectAll("Product");		
	
		for (Product p : list) {
			listProducts.add(new ProductBean(p));
		}
								
	}
	

	
	
	/*
	 * Listener to detect the invoice selected and update the view 
	 */	
	private void onInvoiceSelected(InvoiceBean nv) {
		
		masterInvoiceBean = nv;
		
		calculatePrices();


		// make concept visible
		if (masterInvoiceBean.getConcept() != null) {
			hboxConcept.setVisible(true);
		} else {
			hboxConcept.setVisible(false);
		}

		try {
	
			try {
				
				//Set everything at start position				
				dateInvoice.setValue(LocalDate.now());
				txtInvoiceNumber.setText("");
				txtTotal.setDecimal(0);
				txtPrice.setDecimal(0);
				txtPercent.setDecimal(0);
				txtPriceTaxIncluded.setDecimal(0);
				lblTaxID.setText("Impuesto");
				txtConcept.setText("");
				txtPrecio.setText("");
				
				
				
				//Unbindings
				try {dateInvoice.valueProperty().unbindBidirectional(masterInvoiceBean.invoiceDateProperty());} catch (Exception e) {}
				try {txtInvoiceNumber.textProperty().unbindBidirectional(masterInvoiceBean.invoiceNumberProperty());} catch (Exception e) {}
				try {txtTotal.decimalProperty().unbindBidirectional(masterInvoiceBean.priceProperty());} catch (Exception e) {}
				try {txtPrice.decimalProperty().unbindBidirectional(masterInvoiceBean.priceProperty());} catch (Exception e) {}
				try {txtTaxTotal.decimalProperty().unbindBidirectional(masterInvoiceBean.taxTotalProperty());} catch (Exception e) {}
				try {txtPriceTaxIncluded.decimalProperty().unbindBidirectional(masterInvoiceBean.priceTaxesIncludedProperty());} catch (Exception e) {}
				try {txtPercent.decimalProperty().unbindBidirectional(masterInvoiceBean.getTax().percentageProperty());} catch (Exception e) {}
				try {tableDetails.itemsProperty().unbindBidirectional(masterInvoiceBean.invoiceDetailsProperty());} catch (Exception e) {}
				try {masterInvoiceBean.companyProperty().unbind();	} catch (Exception e) {}
				try {masterInvoiceBean.customerProperty().unbind();} catch (Exception e) {}
				try {masterInvoiceBean.statusProperty().unbind();} catch (Exception e) {}
				try {lblTaxID.textProperty().unbindBidirectional(masterInvoiceBean.getTax().taxIdProperty());} catch (Exception e) {}
				try {txtConcept.textProperty().unbindBidirectional(masterInvoiceBean.getConcept().descriptionProperty());} catch (Exception e) {}
				try {Bindings.unbindBidirectional(txtPrecio.textProperty(), masterInvoiceBean.getConcept().priceProperty());} catch (Exception e) {}
				
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Bindings
			try {dateInvoice.valueProperty().bindBidirectional(masterInvoiceBean.invoiceDateProperty());}catch (Exception e) {}
			try {txtInvoiceNumber.textProperty().bindBidirectional(masterInvoiceBean.invoiceNumberProperty());}catch (Exception e) {}
			try {txtTotal.decimalProperty().bindBidirectional(masterInvoiceBean.priceProperty());}catch (Exception e) {}
			try {txtPrice.decimalProperty().bindBidirectional(masterInvoiceBean.priceProperty());}catch (Exception e) {}
			try {txtPriceTaxIncluded.decimalProperty().bindBidirectional(masterInvoiceBean.priceTaxesIncludedProperty());}catch (Exception e) {}
			try {txtTaxTotal.decimalProperty().bindBidirectional(masterInvoiceBean.taxTotalProperty());}catch (Exception e) {}
			try {txtPercent.decimalProperty().bindBidirectional(masterInvoiceBean.getTax().percentageProperty());}catch (Exception e) {}
			try {lblTaxID.textProperty().bindBidirectional(masterInvoiceBean.getTax().taxIdProperty());}catch (Exception e) {}
			try {tableDetails.itemsProperty().bindBidirectional(masterInvoiceBean.invoiceDetailsProperty());}catch (Exception e) {}
		
					
			
									
						
			try {
				txtConcept.textProperty().bindBidirectional(masterInvoiceBean.getConcept().descriptionProperty());
				Bindings.bindBidirectional(txtPrecio.textProperty(),masterInvoiceBean.getConcept().priceProperty(), new NumberStringConverter());
			} catch (Exception e) {				
			}
			

			try {
				cmbCompany.getSelectionModel().select(masterInvoiceBean.getCompany());			
				masterInvoiceBean.companyProperty().bind(cmbCompany.getSelectionModel().selectedItemProperty());				
			} catch (Exception e) {				
			}
		
			try {
				cmbCustomer.getSelectionModel().select(masterInvoiceBean.getCustomer());
				masterInvoiceBean.customerProperty().bind(cmbCustomer.getSelectionModel().selectedItemProperty());
			} catch (Exception e) {			
			}

			try {
				cmbStatus.getSelectionModel().select(masterInvoiceBean.getStatus());
				masterInvoiceBean.statusProperty().bind(cmbStatus.getSelectionModel().selectedIndexProperty());
			} catch (Exception e) {				
			}
								
			
					
			//calculatePrices();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * Insert a list of status in the comboStatus
	 */
	public void insertListOfStatus() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Creada");
		list.add("Pendiente");
		list.add("Cobrada");
		list.add("Atrasada");
		
		cmbStatus.setItems(FXCollections.observableArrayList(list));
	}
	
	public void updateView() {
		
	}
	
	/*
	 * Calculate prices for the current invoice
	 */
	public void calculatePrices() {
		
		System.out.println("CALCULANDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		
		double totalWoTax = 0;
		double totalTax = 0; 
		double totalTaxIncl = 0;
		
		
	try {

			if (masterInvoiceBean.getInvoiceDetails() != null) {

				for (int i = 0; i < masterInvoiceBean.getInvoiceDetails().size(); i++) {
					totalWoTax += masterInvoiceBean.getInvoiceDetails().get(i).getPrice();
				}

				totalTax = (totalWoTax * masterInvoiceBean.getTax().getPercentage()) / 100;
				totalTaxIncl = totalWoTax + totalTax;

				masterInvoiceBean.setPrice(totalWoTax);
				masterInvoiceBean.setTaxTotal(totalTax);
				masterInvoiceBean.setPriceTaxesIncluded(totalTaxIncl);

				System.out.println(totalWoTax);
				System.out.println(totalTax);
				System.out.println(totalTaxIncl);

				System.out.println("LOS OTROOS");

				System.out.println(masterInvoiceBean.getPrice());
				System.out.println(masterInvoiceBean.getTaxTotal());
				System.out.println(masterInvoiceBean.getPriceTaxesIncluded());

				txtPrice.setDecimal(totalWoTax);
				txtTaxTotal.setDecimal(totalTax);
				txtPriceTaxIncluded.setDecimal(totalTaxIncl);
				hibernate.saveOrUpdate(masterInvoiceBean.getInvoice());

//			if(totalWoTax != masterInvoiceBean.getPrice()) {
//				hibernate.saveOrUpdate(masterInvoiceBean.getInvoice());
//				updateContent();
//			}

			}
		
	} catch (Exception e) {
		
	}

		
	}
	
	

    @FXML
    private void onActionAddDetail(ActionEvent event) {
    	
    	InvoiceDetail invoiceDetail = new InvoiceDetail(); 
    	
    	if(tableInvoices.getSelectionModel().getSelectedItem() != null) {
    	
    	int indexInvoiceFocused = tableInvoices.getSelectionModel().getSelectedIndex();
    	
    	AddDetailDialogController dialog = new AddDetailDialogController(masterInvoiceBean.getInvoice());
    	dialog.chargeProducts(listProducts);
    	Optional<AddDetailDialogModel> result = dialog.showAndWait();
    	
    	double quantity = result.get().getCantidad();
    	double priceUnit = result.get().getProductSelected().getProduct().getPrice();
    	double price = quantity * priceUnit; 
    	invoiceDetail.setInvoice(masterInvoiceBean.getInvoice());
    	invoiceDetail.setProduct(result.get().getProductSelected().getProduct());
    	invoiceDetail.setPrice(price);
    	invoiceDetail.setPriceUnit(priceUnit);
    	invoiceDetail.setQuantity(quantity);
    	
    	
    	hibernate.save(invoiceDetail);
    	updateContent();
    	
    	    	
    	tableInvoices.getSelectionModel().select(indexInvoiceFocused);
    	
    	}
    }

    @FXML
    private void onActionAddInvoice(ActionEvent event) {
    	

    }

    @FXML
    private void onActionRemoveDetail(ActionEvent event) {
    	

    	if(tableDetails.getSelectionModel().getSelectedItem() != null) {
    		
    		int indexInvoiceFocused = tableInvoices.getSelectionModel().getSelectedIndex();
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Eliminar detalle");
    	alert.setHeaderText("Va a eliminar el datalle seleccionado");
    	
    	alert.setContentText("¿Está seguro de ello?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	  InvoiceDetail invoiceDetail = tableDetails.getSelectionModel().getSelectedItem().getInvoiceDetail(); 
    	  
    	  hibernate.delete(invoiceDetail);
    	  
    	  updateContent();
    	  
    	  tableInvoices.getSelectionModel().select(indexInvoiceFocused);
    	  
    	} else {
    	   alert.close();
    	}   	
    	
    	
    }


    }

    @FXML
    private void onActionRemoveInvoice(ActionEvent event) {

    }
	
	
	public VBox getView() {
		return view;
	}



}

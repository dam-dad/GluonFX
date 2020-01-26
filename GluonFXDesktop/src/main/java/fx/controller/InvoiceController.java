package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import dad.javafx.componentes.TextDecimal;
import entities.Company;
import entities.Customer;
import entities.Invoice;
import entities.InvoiceDetail;
import entities.Product;
import entities.Tax;
import fx.beans.CompanyBean;
import fx.beans.CustomerBean;
import fx.beans.InvoiceBean;
import fx.beans.InvoiceDetailBean;

import fx.beans.ProductBean;
import fx.beans.TaxBean;
import hibernate.HibernateController;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InvoiceController implements Initializable {

	@FXML
	private VBox view;
	@FXML
	private TableView<InvoiceBean> tableInvoices;
	@FXML
	private TableColumn<InvoiceBean, String> columnNumeroFactura;
	@FXML
	private TableColumn<InvoiceBean, LocalDate> columnFecha;
	@FXML
	private TableColumn<InvoiceBean, Customer> columnCliente;
	@FXML
	private Button btnAddInvoice;
	@FXML
	private Button btnRemoveInvoice;
	@FXML
	private ComboBox<CompanyBean> cmbCompany;
	@FXML
	private TextField txtInvoiceNumber;
	@FXML
	private ComboBox<CustomerBean> cmbCustomer;
	@FXML
	private DatePicker dateInvoice;
	@FXML
	private TextArea txtConcept;
	@FXML
	private TextField priceFld;
	@FXML
	private TextField txtPrecio;
	@FXML
	private TableView<InvoiceDetailBean> tableDetails;
	@FXML
	private TableColumn<InvoiceDetailBean, Product> columnProduct;
	@FXML
	private TableColumn<InvoiceDetailBean, Number> ColumnUds;
	@FXML
	private TableColumn<InvoiceDetailBean, Number> ColumnPrice;
	@FXML
	private TableColumn<InvoiceDetailBean, Number> columnSubtotal;
	@FXML
	private Button btnAddDetail;
	@FXML
	private Button btnRemoveDetail;
	@FXML
	private ComboBox<String> cmbStatus;
    @FXML
    private Label lblTaxID;
  
    @FXML
    private TextDecimal txtPrice;
    @FXML
    private TextDecimal txtPercent;
    @FXML
    private TextDecimal txtTaxTotal;
    @FXML
    private TextDecimal txtTotal;
    @FXML
    private TextDecimal txtPriceTaxIncluded;
	
	
	
	
	//hibernate
	private HibernateController hibernate;
	
	//Lists
	private List<InvoiceBean> listInvoices = new ArrayList<InvoiceBean>();
	
	private List<CompanyBean> listCompanies = new ArrayList<CompanyBean>(); 
	private List<CustomerBean> listCustomer = new ArrayList<CustomerBean>();
	private List<InvoiceDetailBean> listInvoiceDetails = new ArrayList<InvoiceDetailBean>();
	private List<ProductBean> listProducts = new ArrayList<ProductBean>();
	private List<InvoiceBean> listInvoiceTaxes = new ArrayList<InvoiceBean>();
	private TaxBean tax;
	
	
	
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
		ColumnUds.setCellValueFactory(v -> v.getValue().quantityProperty());
		ColumnPrice.setCellValueFactory(v -> v.getValue().priceUnitProperty());
		columnSubtotal.setCellValueFactory(v -> v.getValue().priceProperty());
		
		//TODO table invoice listener
		
		
		
		
	

	}
	
	private void onInvoiceSelected(InvoiceBean nv) {

		try {

			try {
				//txtObservacion.setText("");
				//TODO SETEAR LAS COSAS A VACIO	
								
				dateInvoice.valueProperty().unbindBidirectional(nv.invoiceDateProperty());
				txtInvoiceNumber.textProperty().unbindBidirectional(nv.invoiceNumberProperty());
				
				
			
			} catch (Exception e) {
			}
			
			dateInvoice.valueProperty().bindBidirectional(nv.invoiceDateProperty());
			txtInvoiceNumber.textProperty().bindBidirectional(nv.invoiceNumberProperty());
			
				
			
			//Select selected items in combos
			
			List<CompanyBean> listCompany = cmbCompany.getItems();
			
			for(CompanyBean c : listCompany) {
				if(c.getCompany() == nv.getCompany()) {
					cmbCompany.getSelectionModel().select(c);
				}
			}
			
						
			List<CustomerBean> listCustomer = cmbCustomer.getItems();
			
			for(CustomerBean c : listCustomer) {
				if(c.getCustomer() == nv.getCustomer()) {
					cmbCustomer.getSelectionModel().select(c);
				}
			}
			
//
			int status = nv.getStatus();
			cmbStatus.getSelectionModel().select(status);	
			
			
			//generate details table
			generateListInvoiceDetailsBean(nv);	
			
			
			//TODO get current tax and write name in table
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	public void updateContent() {
		selectAllInvoices();
		selectAllCompanys();
		selectAllCustomers();

	}

	public void selectAllInvoices() {
	
		List<Invoice> list =  hibernate.selectAll("Invoice");
	
		for(Invoice i: list) {
			listInvoices.add(new InvoiceBean(i));
		}

		tableInvoices.setItems(FXCollections.observableArrayList(listInvoices));
		
	}
	
	
	public void selectAllCompanys() {
		
		List<Company> list = hibernate.selectAll("Company");
		
		for (Company c : list) {
			listCompanies.add(new CompanyBean(c));
		}
		
		cmbCompany.setItems(FXCollections.observableArrayList(listCompanies));		
		
	}
	
	public void generateListInvoiceDetailsBean(InvoiceBean nv) {
		
		System.out.println(nv.getInvoiceDetails().size());
		for(InvoiceDetail i : nv.getInvoiceDetails()) {
			listInvoiceDetails.add(new InvoiceDetailBean(i));
		}
		
		tableDetails.setItems(FXCollections.observableArrayList(listInvoiceDetails));
		
	}
	
	
	
	
	public void selectAllCustomers() {
		
		List<Customer> list =  hibernate.selectAll("Customer");		
	
		for (Customer c : list) {
			listCustomer.add(new CustomerBean(c));
		}
	
		cmbCustomer.setItems(FXCollections.observableArrayList(listCustomer));
				
	}
	
	

	
	
	public void insertListOfStatus() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Creada");
		list.add("Pendiente");
		list.add("Cobrada");
		list.add("Atrasada");
		
		cmbStatus.setItems(FXCollections.observableArrayList(list));
	}
	
	public void fillForm() {
	
		
	}
	
	
	public VBox getView() {
		return view;
	}



}

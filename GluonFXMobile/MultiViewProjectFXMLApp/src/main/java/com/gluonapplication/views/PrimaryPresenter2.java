package com.gluonapplication.views;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.model.beans.CustomerBean;
import com.model.beans.InvoiceBean;
import com.model.beans.InvoiceDetailBean;
import com.model.beans.PayMethodBean;
import com.model.beans.ProductBean;
import com.model.beans.TaxBean;
import com.model.entities.ConceptInvoice;
import com.model.entities.Customer;
import com.model.entities.Invoice;
import com.model.entities.InvoiceDetail;
import com.model.entities.PayMethod;
import com.model.entities.Product;
import com.model.entities.Tax;
import com.sun.javafx.tk.quantum.MasterTimer;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import com.utils.HibernateController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PrimaryPresenter2 {

    @FXML
    private View primary;

    @FXML
    private Tab tabInvoices;

    @FXML
    private TableView<InvoiceBean> tableInvoices;

    @FXML
    private TableColumn<InvoiceBean, LocalDate> columnDate;

    @FXML
    private TableColumn<InvoiceBean, CustomerBean> columnCustomer;

    @FXML
    private TableColumn<InvoiceBean, Number> columPrice;

    @FXML
    private TableColumn<InvoiceBean, Number> columnStatus;

    @FXML
    private Button btnNewInoive;

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
    private RadioButton radioSended;
    
    @FXML
    private RadioButton radioCredit;   

    @FXML
    private ToggleGroup toogleStatus;

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
    private TableColumn<?, ?> columnAcciones;

    @FXML
    private TextField txtBase;

    @FXML
    private TextField txtTotalToPay;

    @FXML
    private TextField txtTaxPercentage;

    @FXML
    private TextField txtTaxTotal;

    
    //Model
    private ListProperty<InvoiceBean> invoiceBeansList = new SimpleListProperty<>();    
    private ListProperty<CustomerBean> customerBeansList = new SimpleListProperty<>();
    private ListProperty<TaxBean> taxBeanList = new SimpleListProperty<>(); 
    private ListProperty<PayMethodBean> payMethodBeanList = new SimpleListProperty<>();
    private ListProperty<ProductBean> productBeansList = new SimpleListProperty<>(); 
    private ListProperty<InvoiceDetailBean> invoiceDetailsList = new SimpleListProperty<>();
    

//    private TextField txtCustomerName;
//    private TextField txtNIF;
//    private TextField txtAddress;
//    private TextField txtPhone;
//    private TextArea txtConcept;
//    private TextField txtSearchProduct;
//    private ImageView imgProduct;
//    private TextArea txtProductDescription;
//    private TextField txtUdsStock;
//    private TextField txtQuantity;
//    private TextField txtBase;
//    private TextField txtTotalToPay;
//    private TextField txtTaxPercentage;
//    private TextField txtTaxTotal; 
    private InvoiceBean invoiceMaster;
    private ProductBean productMaster;
    
    private InvoiceDetailBean invoiceDetailMaster; 
   
    //neccesary
    HibernateController hibernate = new HibernateController(); 
    
    public void initialize() {
    	
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Facturas");
                appBar.getActionItems().add(MaterialDesignIcon.PICTURE_AS_PDF.button(e -> 
                        System.out.println("Search")));
            }
        });
        
        
                
       //table details configuration;
        tableInvoices.itemsProperty().bindBidirectional(invoiceBeansList);       
        
      	columnDate.setCellValueFactory(v -> v.getValue().invoiceDateProperty());
      	columnCustomer.setCellValueFactory(v -> v.getValue().customerProperty());
      	columPrice.setCellValueFactory(v -> v.getValue().priceProperty());
      	columnStatus.setCellValueFactory(v -> v.getValue().statusProperty());
      
      	tableInvoices.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onInvoiceSelected(nv)); 
    	
      	txtConcept.focusedProperty().addListener((o, ov, nv) -> onConceptChanged(nv));
      	
      	//taxes combo configuration     
    	cmbTax.itemsProperty().bindBidirectional(taxBeanList);
    	
      	cmbTax.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onTaxSelected(nv));
      	
      	//radioButtons tax lister  	
        tooglePayMethod.selectedToggleProperty().addListener((o, ov, nv) -> onPayMethodSelected(nv));
        
        //radioButtons
        toogleStatus.selectedToggleProperty().addListener((o, ov, nv) -> onStatusSelected(nv));
      	
     
        
        //list product configuration
        listProducts.itemsProperty().bind(productBeansList);
        
        listProducts.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onProductSelected(nv)); 
              
       
      //TextFormatters
        textFieldFormmater(txtQuantity);
        
       txtQuantity.textProperty().addListener((o, ov, nv) -> onQuantyChanged(ov, nv));
       
       
       //Table details configuration
       tableDetails.itemsProperty().bindBidirectional(invoiceDetailsList);   
       
       columnProduct.setCellValueFactory(v -> v.getValue().productProperty());
       columnUds.setCellValueFactory(v -> v.getValue().quantityProperty());
       columnPriceUnit.setCellValueFactory(v -> v.getValue().priceUnitProperty());
       ColumnSubtotal.setCellValueFactory(v -> v.getValue().priceProperty());
       
       //invoice prices configuration
       
       
    }
    
   
	private void onConceptChanged(Boolean nv) {
		
		ConceptInvoice concept = null;
		Invoice invoice =  null; 
		if(!nv) {
			
			try {
				invoice = invoiceMaster.getInvoice(); 
				concept =  invoice.getConceptInvoices().get(0);				
				
			}catch (Exception e) {
				
			}		
			
			if(txtConcept.getText().length()>0) {
				
				if (concept != null ) {	
					System.out.println("Tiene concepto");
					concept.setDescription(txtConcept.getText());
					
					hibernate.saveOrUpdate(concept);
					
					updateContent();
					
				}else {
					System.out.println("No tiene concepto");
					concept = new ConceptInvoice(); 
					concept.setInvoice(invoice);
					concept.setDescription(txtConcept.getText());
					concept.setPrice(0.0);
					hibernate.save(concept);
					updateContent();
				}
			}
			
			
			
		}
		
		
	
	}


	private void onQuantyChanged(String ov, String nv) {
		
		try {
			
			double quantity = Double.parseDouble(nv);
			double stock = Double.parseDouble(txtUdsStock.getText());
			if(quantity > stock) {
				txtQuantity.setText(ov);
			}
			
		} catch (Exception e) {
			
		}		
		
	}


	private void onProductSelected(ProductBean nv) {
		
		txtProductDescription.setText("");
		txtUdsStock.setText("");
		txtQuantity.setText("");
		
		try {txtProductDescription.setText(nv.getDescription());}catch (Exception e) {}
		try {txtUdsStock.setText(String.valueOf(nv.getStock()));}catch (Exception e) {}
		
		
		
	}


	private void onInvoiceSelected(InvoiceBean nv) {
			  	
        	this.invoiceMaster = nv;    
        	        	
        	
        	//Clean interface
        	txtCustomerName.setText("");
        	txtAddress.setText("");
        	txtAddress.setText("");
        	txtPhone.setText("");
        	txtConcept.setText("");
        	txtConcept.setText("");
        	cmbTax.getSelectionModel().select(0);        	       	
        	
        	
        	//Set customer information
        	try {txtCustomerName.setText(invoiceMaster.getCustomer().getName());}catch (Exception e) {}
        	try {txtNIF.setText(invoiceMaster.getCustomer().getCustomerId());}catch (Exception e) {}
        	try {txtAddress.setText(invoiceMaster.getCustomer().getAddress());}catch (Exception e) {}
        	try {txtPhone.setText(invoiceMaster.getCustomer().getPhone());}catch (Exception e) {}
        	
        	//Set concept information
        	try {txtConcept.setText(invoiceMaster.getConceptInvoices().get(0).getDescription());}catch (Exception e) {}       	
        	        	
        	//set tax information
        	try {cmbTax.setValue(invoiceMaster.getTax());}catch (Exception e) {}
			       		            	
        	
        	
        	
        	//Set paymethod information       
		try {

			PayMethodBean p0 = (PayMethodBean) radioTransfer.getUserData();
			if (invoiceMaster.getPayMethod().getId() == p0.getId()) {
				radioTransfer.setSelected(true);
			}

		} catch (Exception e) {
		}

		try {

			PayMethodBean p1 = (PayMethodBean) radioCredit.getUserData();
			if (invoiceMaster.getPayMethod().getId() == p1.getId()) {
				radioCredit.setSelected(true);
			}

		} catch (Exception e) {
			
		}

		try {

			PayMethodBean p2 = (PayMethodBean) radioCash.getUserData();
			if (invoiceMaster.getPayMethod().getId() == p2.getId()) {
				radioCash.setSelected(true);
			}
		} catch (Exception e) {

		}
		
		//Status information
		try {
			
			if(invoiceMaster.getStatus() == (int)radioAprobed.getUserData()) {
				radioAprobed.setSelected(true);
			}
			
		} catch (Exception e) {			
		}
	
		try {
			
			if(invoiceMaster.getStatus() == (int)radioSended.getUserData()) {
				radioSended.setSelected(true);
			}
			
		} catch (Exception e) {
			
		}
		
		try {
			
			if(invoiceMaster.getStatus() == (int)radioPending.getUserData()) {
				radioPending.setSelected(true);
			}
			
		} catch (Exception e) {
			
		}
		
		
		//Invoice details table
		try {
			invoiceDetailsList.set(invoiceMaster.getInvoiceDetails());
		} catch (Exception e) {
			
		}
		
		//Invoice prices 
		txtBase.setText(String.valueOf(invoiceMaster.getPrice()));
		txtTaxPercentage.setText(String.valueOf(invoiceMaster.getTax().getPercentage()));
		txtTaxTotal.setText(String.valueOf(invoiceMaster.getTaxTotal()));
		txtTotalToPay.setText(String.valueOf(invoiceMaster.getPriceTaxesIncluded()));
        	        			
	}
	
	
	private void onTaxSelected(TaxBean nv) {		
		
		invoiceMaster.setTax(nv);
		
		
	}
	

	private void onStatusSelected(Toggle nv) {
		invoiceMaster.setStatus((int) nv.getUserData());
	}
	

	private void onPayMethodSelected(Toggle nv) {

		invoiceMaster.setPayMethod((PayMethodBean) nv.getToggleGroup().getSelectedToggle().getUserData());

	}

	public PrimaryPresenter2() {
		hibernate.start();
		updateContent();
      
	}
	
	public void updateContent() {
		 selectAllInvoices();
	     selectAllCustomers();
	     selectAllTaxes();
	     selectAllPayMethods();
	     selectAllProducts();
	     //putAllStatus();
	}
    
    //My methods  
    
    public void selectAllInvoices() {
    	
    	List<Invoice> list = hibernate.selectAll("Invoice");
    	List<InvoiceBean> listBeans = new ArrayList<>(); 
    	
    	for(Invoice i : list){
    		listBeans.add(new InvoiceBean(i));
    	}
    	
    	invoiceBeansList.set(FXCollections.observableArrayList(listBeans));
    	
    	    	    	
    }
    
    public void selectAllCustomers() {
    	
    	List<Customer> list = hibernate.selectAll("Customer");
    	List<CustomerBean> listBeans = new ArrayList<>(); 
    	
    	for(Customer i : list){
    		listBeans.add(new CustomerBean(i));
    	}
    	
    	customerBeansList.set(FXCollections.observableArrayList(listBeans));
    	    	    	    	
    }
    
    
    public void selectAllTaxes() {
    	List<Tax> list = hibernate.selectAll("Tax");  	
    	List<TaxBean> listBeans = new ArrayList<>(); 
    	
    	for(Tax t : list) {
    		listBeans.add(new TaxBean(t));
    	}
    	
    	taxBeanList.set(FXCollections.observableArrayList(listBeans));
    	
    }
    
    public void selectAllProducts() {
    	List<Product> list = hibernate.selectAll("Product");  	
    	List<ProductBean> listBeans = new ArrayList<>(); 
    	
    	for(Product p : list) {
    		listBeans.add(new ProductBean(p));
    	}
    	
    	productBeansList.set(FXCollections.observableArrayList(listBeans));
    }
    
    
    public void putAllStatus() {    
    	
    	radioSended.setUserData(0);
    	radioPending.setUserData(1);
    	radioAprobed.setUserData(2);    
    	
    	
    	
    }
    
    
    
    
    
    public void selectAllPayMethods() {
    	List<PayMethod> list = hibernate.selectAll("PayMethod");
    	List<PayMethodBean> listBeans = new ArrayList<>(); 
    	
    	for(PayMethod p :  list) {
    		listBeans.add(new PayMethodBean(p));
    	}
    	
    	payMethodBeanList.set(FXCollections.observableArrayList(listBeans));
    	
    	try {
    		radioCredit.setUserData(payMethodBeanList.get(0));
        	radioCredit.setText(payMethodBeanList.get(0).getDescription());
        	radioCredit.setVisible(true);
    	}catch (Exception e) {
			
		}
    	
    	try {
    		radioTransfer.setUserData(payMethodBeanList.get(1));
        	radioTransfer.setText(payMethodBeanList.get(1).getDescription());
        	radioTransfer.setVisible(true);
		} catch (Exception e) {
			
		}
    	
    	try {
        	radioCash.setUserData(payMethodBeanList.get(2));
        	radioCash.setText(payMethodBeanList.get(2).getDescription());
        	radioCash.setVisible(true);
		} catch (Exception e) {			
		}
    	   	
    	
    }
    
    /**
	* Metodo tipo TextFormater  
	* se encarga de que solo se puedan introducir
	* valores de coma flotante en los textfield
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
    
    
	//Action events
    @FXML
    void onClickBtnSearchCustomer(ActionEvent event) {    	 
    	    	
    	CustomerBean result = null;
        	
    	//Search by name
    	try {
    		
    		String name = txtCustomerName.getText();
	   		
    		if(name.length() > 0) {
    	
    		for(CustomerBean c : customerBeansList) {
    			   		
    			
    			if(c.getName().contains(name)) {
    				result = c;    			
    				break;
    			}
    		}
    		
    		}  
    		
		} catch (Exception e) {
			
		}
    	
    	
      	//Search by NIF	
    	try {
    		
    		String nif = txtNIF.getText();
	   		
    		if(nif.length() > 2) {
    			
    			for(CustomerBean c : customerBeansList) {
	    			        			
        			if(c.getCustomerId().contains(nif)) {
        				result = c;   		
        				
        				break;
        			}    		
        			
        		}  
    		}
    	
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	    	
    	    	
    	if(result != null) {
    		
    		invoiceMaster.setCustomer(result);
    		
    		txtCustomerName.setText(invoiceMaster.getCustomer().getName());
        	txtAddress.setText(invoiceMaster.getCustomer().getAddress());
        	txtNIF.setText(invoiceMaster.getCustomer().getCustomerId());
        	txtPhone.setText(invoiceMaster.getCustomer().getPhone());
    		
	    		   		
    	}else {
    		
    		txtCustomerName.setText("");
        	txtAddress.setText("");
        	txtNIF.setText("");
        	txtPhone.setText("");
        	        
        	
    	}
    	           		
    	
    }
    
    
    @FXML
    void onClickbtnSaveInvoiceInformation(ActionEvent event) {
    	

    	Customer customer = new Customer(); 
    	customer.setName(txtCustomerName.getText());
    	customer.setCustomerId(txtNIF.getText());
    	customer.setAddress(txtAddress.getText());
    	customer.setPhone(txtPhone.getText());
    	
		
    	System.out.println(customer);
    	
    	invoiceMaster.setCustomer(new CustomerBean(customer));
    	
    	System.out.println(invoiceMaster.getCustomer().getName());
    	
    }
    
    

	@FXML
	void onClickBtnSearchProduct(ActionEvent event) {

		List<ProductBean> listFinded = new ArrayList<ProductBean>();
		List<ProductBean> listNotFinded = new ArrayList<ProductBean>();

		try {

			for (ProductBean p : productBeansList) {
				if (p.getName().contains(txtSearchProduct.getText())
						|| p.getProductId().contains(txtSearchProduct.getText())) {
					System.out.println("COUNCIDENCIA");
					listFinded.add(p);
				} else {
					listNotFinded.add(p);
				}
			}

			listFinded.addAll(listNotFinded);

			System.out.println(listFinded.size());

			productBeansList.clear();
			productBeansList.set(FXCollections.observableArrayList(listFinded));

			System.out.println("AÑADIDA");

		} catch (Exception e) {

		}
	}
	
	
	@FXML
	void onClickBtnAddDetail(ActionEvent event) {
		
		int indexInvoiceFocused = tableInvoices.getSelectionModel().getSelectedIndex();
		
		InvoiceDetail detail = new InvoiceDetail(); 
		detail.setInvoice(invoiceMaster.getInvoice());
		detail.setProduct(listProducts.getSelectionModel().getSelectedItem().getProduct());
		detail.setQuantity(Double.parseDouble(txtQuantity.getText()));
	
		
		hibernate.saveOrUpdate(detail);
		updateContent();
		
		tableInvoices.getSelectionModel().select(indexInvoiceFocused);
	}

}


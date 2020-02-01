package com.gluonapplication.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.model.beans.CustomerBean;
import com.model.beans.InvoiceBean;
import com.model.beans.PayMethodBean;
import com.model.beans.ProductBean;
import com.model.beans.TaxBean;
import com.model.entities.Customer;
import com.model.entities.Invoice;
import com.model.entities.PayMethod;
import com.model.entities.Product;
import com.model.entities.Tax;
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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PrimaryPresenter {

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
    private TableView<?> tableDetails;

    @FXML
    private TableColumn<?, ?> columnProduct;

    @FXML
    private TableColumn<?, ?> columnUds;

    @FXML
    private TableColumn<?, ?> columnPriceUnit;

    @FXML
    private TableColumn<?, ?> ColumnSubtotal;

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
     
    
    private InvoiceBean invoiceMaster;
    private ProductBean productMaster;
   
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
    	
      	
      	//taxes combo configuration     
    	cmbTax.itemsProperty().bindBidirectional(taxBeanList);
    	
      	cmbTax.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onTaxSelected(nv));
      	
      	//radioButtons tax lister  	
        tooglePayMethod.selectedToggleProperty().addListener((o, ov, nv) -> onPayMethodSelected(nv));
        
        //radioButtons
        toogleStatus.selectedToggleProperty().addListener((o, ov, nv) -> onStatusSelected(nv));
      	
        //updateContent(); 
        
        //list product configuration
        listProducts.itemsProperty().bind(productBeansList);
        
        listProducts.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onProductSelected(nv)); 
        
        txtQuantity.textProperty().addListener((o, ov, nv) -> onQuantyChanged(ov, nv));
       
    }
    
   
	private void onQuantyChanged(String ov, String nv) {
		
		double quantity = Double.parseDouble(nv);
		double stock = Double.parseDouble(txtUdsStock.getText());
		if(quantity > stock) {
			txtQuantity.setText(ov);
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
			// TODO: handle exception
		}
	
		try {
			
			if(invoiceMaster.getStatus() == (int)radioSended.getUserData()) {
				radioSended.setSelected(true);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			
			if(invoiceMaster.getStatus() == (int)radioPending.getUserData()) {
				radioPending.setSelected(true);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
        	        			
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

	public PrimaryPresenter() {
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
    		
    		for(ProductBean p : productBeansList) {
        		if(p.getName().contains(txtSearchProduct.getText()) || p.getProductId().contains(txtSearchProduct.getText())) {
        			System.out.println("COUNCIDENCIA");
        			listFinded.add(p);        			
        		}else {
        		listNotFinded.add(p);
        		}
        	}
    		
    		
    		listFinded.addAll(listNotFinded);
    	
        	
    		System.out.println(listFinded.size());
    		
    		productBeansList.clear();
    		productBeansList.set(FXCollections.observableArrayList(listFinded));
        	
        	System.out.println("AÑADIDA");
    		
    	}catch (Exception e) {
			
		}
    	    }
  
    
}


package com.gluonapplication.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.model.beans.CompanyBean;
import com.model.beans.CustomerBean;
import com.model.beans.InvoiceBean;
import com.model.entities.Customer;
import com.model.entities.Invoice;
import com.mysql.cj.conf.StringProperty;
import com.utils.HibernateController;

import javafx.animation.Animation.Status;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.gluonhq.charm.glisten.mvc.View;
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
    private ComboBox<?> cmbTax;

    @FXML
    private GridPane radioCredit;

    @FXML
    private ToggleGroup tooglePayMethod;

    @FXML
    private RadioButton radioCash;

    @FXML
    private RadioButton radioTransfer;

    @FXML
    private RadioButton radioSended;

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
    private ListView<?> listProducts;

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
    
   
    
    
    private InvoiceBean master;
    
    //neccesary
    HibernateController hibernate = new HibernateController(); 
    
    public void initialize() {
    	
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        MobileApplication.getInstance().getDrawer().open()));
                appBar.setTitleText("Facturas");
                appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> 
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
    	
      	
        
      	
      	
    }
    
    private void onInvoiceSelected(InvoiceBean nv) {
    	

        	this.master = nv;
        	    	
        	
        	txtCustomerName.setText(master.getCustomer().getName());
        	txtAddress.setText(master.getCustomer().getAddress());
        	txtNIF.setText(master.getCustomer().getCustomerId());
        	txtPhone.setText(master.getCustomer().getPhone());
        	
        	//txtConcept.setText(master);
        	
        	
//        	//Customer 0 position
//        	txtCustomerName.setText("");
//        	txtAddress.setText("");
//        	txtNIF.setText("");
//        	txtPhone.setText("");
//        	
//        	
//        	//Customer unbinds
//    		try {txtCustomerName.textProperty().unbindBidirectional(master.getCustomer().nameProperty());}catch (Exception e) {}
//    		try {txtAddress.textProperty().unbindBidirectional(master.getCustomer().addressProperty());}catch (Exception e) {}
//    		try {txtNIF.textProperty().unbindBidirectional(master.getCustomer().customerIdProperty());}catch (Exception e) {}
//    		try {txtPhone.textProperty().unbindBidirectional(master.getCompany().phoneProperty());}catch (Exception e) {}
//    	
//        	
//        	//Customer binds
//    		txtCustomerName.textProperty().bindBidirectional(master.getCustomer().nameProperty());
//    		txtAddress.textProperty().bindBidirectional(master.getCustomer().addressProperty());
//    		txtNIF.textProperty().bindBidirectional(master.getCustomer().customerIdProperty());
//    		txtPhone.textProperty().bindBidirectional(master.getCompany().phoneProperty());
        	
   	
		
	}

	public PrimaryPresenter() {
		hibernate.start();
        selectAllInvoices();
        selectAllCustomers();
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
    		
    		master.setCustomer(result);
    		
    		txtCustomerName.setText(master.getCustomer().getName());
        	txtAddress.setText(master.getCustomer().getAddress());
        	txtNIF.setText(master.getCustomer().getCustomerId());
        	txtPhone.setText(master.getCustomer().getPhone());
    		
	    		   		
    	}else {
    		
    		txtCustomerName.setText("");
        	txtAddress.setText("");
        	txtNIF.setText("");
        	txtPhone.setText("");
        	        
        	Customer customer = new Customer(); 
        	customer.setName(txtCustomerName.getText());
        	customer.setCustomerId(txtNIF.getText());
        	customer.setAddress(txtAddress.getText());
        	customer.setPhone(txtPhone.getText());
    		
        	System.out.println(customer);
        	
        	master.setCustomer(new CustomerBean(customer));
        	
        	System.out.println(master.getCustomer().getName());
    	}
    	           		
    	
    }
    
    
    @FXML
    void onClickbtnSaveInvoiceInformation(ActionEvent event) {

    }
  
    
}


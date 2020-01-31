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
    private ListProperty<InvoiceBean> invoiceBeans = new SimpleListProperty<>(); 
    
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
        tableInvoices.itemsProperty().bindBidirectional(invoiceBeans);       
        
      	columnDate.setCellValueFactory(v -> v.getValue().invoiceDateProperty());
      	columnCustomer.setCellValueFactory(v -> v.getValue().customerProperty());
      	columPrice.setCellValueFactory(v -> v.getValue().priceProperty());
      	columnStatus.setCellValueFactory(v -> v.getValue().statusProperty());
      
      	tableInvoices.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onInvoiceSelected(nv)); 
    	
      	
        
      	
      	
    }
    
    private void onInvoiceSelected(InvoiceBean nv) {
    	
		txtCustomerName.textProperty().bindBidirectional(nv.getCustomer().nameProperty());
		txtAddress.textProperty().bindBidirectional(nv.getCustomer().addressProperty());
		txtNIF.textProperty().bindBidirectional(nv.getCustomer().customerIdProperty());
		txtPhone.textProperty().bindBidirectional(nv.getCompany().phoneProperty());
		
		//txtConcept.textProperty().bindBidirectional(n);
		
	}

	public PrimaryPresenter() {
		hibernate.start();
        selectAllInvoices();
	}
    
    //My methods  
    
    public void selectAllInvoices() {
    	
    	List<Invoice> list = hibernate.selectAll("Invoice");
    	List<InvoiceBean> listBeans = new ArrayList<>(); 
    	
    	for(Invoice i : list){
    		listBeans.add(new InvoiceBean(i));
    	}
    	
    	invoiceBeans.set(FXCollections.observableArrayList(listBeans));
    	
    	    	    	
    }
    
    
    
    
	
    
  
    
}


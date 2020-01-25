package fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import hibernate.HibernateController;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class InvoiceController implements Initializable {
	
	//FXML

	@FXML
    private VBox view;
	
	@FXML
	private TableView<?> billSelector;

	@FXML
	private Button addBill;

	@FXML
	private Button deleteBill;

	@FXML
	private ComboBox<String> bsName;

	@FXML
	private Label bsID;

	@FXML
	private Label bsDir;

	@FXML
	private Label bsPC;

	@FXML
	private Label bsEmail;

	@FXML
	private Label bsTlp;

	@FXML
	private TextField billFld;

	@FXML
	private ComboBox<String> cltName;

	@FXML
	private Label cltID;

	@FXML
	private Label cltDir;

	@FXML
	private Label cltPC;

	@FXML
	private Label cltEmail;

	@FXML
	private Label cltTlp;

	@FXML
	private TextField dateFld;

	@FXML
	private TextArea conceptArea;

	@FXML
	private TextField priceFld;

	@FXML
	private TableView<?> productsTbl;

	@FXML
	private Button addProduct;

	@FXML
	private Button delProducts;

	@FXML
	private ComboBox<String> statusCmb;

	@FXML
	private TableView<?> taxTable;

	@FXML
	private TextField totalFld;
	
	//hibernate
	HibernateController hibernate;
	
	//model
	
	//Strings
	StringProperty billTxt = new SimpleStringProperty();
	StringProperty dateTxt = new SimpleStringProperty();
	StringProperty conceptTxt = new SimpleStringProperty();
	StringProperty priceTxt = new SimpleStringProperty();

	//ObservableList y list para las tablas, hacer los objetos
	
	private ObservableList<Object> billObs = FXCollections.observableArrayList(new ArrayList<Object>());
	private ListProperty<Object> billLst =  new SimpleListProperty<Object>(billObs);
	private ObservableList<Object> productsObs = FXCollections.observableArrayList(new ArrayList<Object>());
	private ListProperty<Object> productsLst =  new SimpleListProperty<Object>(productsObs);
	private ObservableList<Object> taxObs = FXCollections.observableArrayList(new ArrayList<Object>());
	private ListProperty<Object> taxLst =  new SimpleListProperty<Object>(taxObs);
	
	//ArrayList para Combobox, a rellenar con los estados
	
	ObservableList<String> statusArray = FXCollections.observableArrayList("Ejemplo1", "Ejemplo2");
	ObservableList<String> bsArray = FXCollections.observableArrayList("Empresa1", "Empresa2");
	ObservableList<String> cltArray = FXCollections.observableArrayList("Cliente1", "Cliente2");
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Darle valores base a los combobox
		
		statusCmb.setItems(statusArray);
		bsName.setItems(bsArray);
		cltName.setItems(cltArray);
		
		//Bindings
		
		billFld.textProperty().bindBidirectional(billTxt);
		dateFld.textProperty().bindBidirectional(dateTxt);
		conceptArea.textProperty().bindBidirectional(conceptTxt);
		priceFld.textProperty().bindBidirectional(priceTxt);

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
	
	
	
	//getters y setters
	
	public VBox getView() {
		return view;
	}

	public final StringProperty billTxtProperty() {
		return this.billTxt;
	}
	

	public final String getBillTxt() {
		return this.billTxtProperty().get();
	}
	

	public final void setBillTxt(final String billTxt) {
		this.billTxtProperty().set(billTxt);
	}
	

	public final StringProperty dateTxtProperty() {
		return this.dateTxt;
	}
	

	public final String getDateTxt() {
		return this.dateTxtProperty().get();
	}
	

	public final void setDateTxt(final String dateTxt) {
		this.dateTxtProperty().set(dateTxt);
	}
	

	public final StringProperty conceptTxtProperty() {
		return this.conceptTxt;
	}
	

	public final String getConceptTxt() {
		return this.conceptTxtProperty().get();
	}
	

	public final void setConceptTxt(final String conceptTxt) {
		this.conceptTxtProperty().set(conceptTxt);
	}
	

	public final StringProperty priceTxtProperty() {
		return this.priceTxt;
	}
	

	public final String getPriceTxt() {
		return this.priceTxtProperty().get();
	}
	

	public final void setPriceTxt(final String priceTxt) {
		this.priceTxtProperty().set(priceTxt);
	}
	

	public final ListProperty<Object> billLstProperty() {
		return this.billLst;
	}
	

	public final ObservableList<Object> getBillLst() {
		return this.billLstProperty().get();
	}
	

	public final void setBillLst(final ObservableList<Object> billLst) {
		this.billLstProperty().set(billLst);
	}
	

	public final ListProperty<Object> productsLstProperty() {
		return this.productsLst;
	}
	

	public final ObservableList<Object> getProductsLst() {
		return this.productsLstProperty().get();
	}
	

	public final void setProductsLst(final ObservableList<Object> productsLst) {
		this.productsLstProperty().set(productsLst);
	}
	

	public final ListProperty<Object> taxLstProperty() {
		return this.taxLst;
	}
	

	public final ObservableList<Object> getTaxLst() {
		return this.taxLstProperty().get();
	}
	

	public final void setTaxLst(final ObservableList<Object> taxLst) {
		this.taxLstProperty().set(taxLst);
	}
	
	
	
	
	

}

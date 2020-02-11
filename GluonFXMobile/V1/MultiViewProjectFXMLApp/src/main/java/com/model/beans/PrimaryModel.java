package com.model.beans;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class PrimaryModel {


	private ListProperty<InvoiceBean> listInvoices = new SimpleListProperty<>(); 	
	
	private ObjectProperty<InvoiceBean> invoiceSelected = new SimpleObjectProperty<>(); 

	private StringProperty customerName = new SimpleStringProperty(); 

	private StringProperty customerNIF = new SimpleStringProperty(); 

	private StringProperty customerAddress = new SimpleStringProperty(); 

	private StringProperty customerPhone = new SimpleStringProperty(); 
	
	private StringProperty conceptDescription = new SimpleStringProperty(); 

	private ListProperty<TaxBean> listTaxes = new SimpleListProperty<>();
	
	private ObjectProperty<TaxBean> taxSelected = new SimpleObjectProperty<>(); 
	
//   @FXML
//   private ToggleGroup tooglePayMethod;
	
	private ListProperty<PayMethodBean> payMethodsList = new SimpleListProperty<>();
	
	private ObjectProperty<PayMethodBean> payMethodSelected = new SimpleObjectProperty<>();
	

	private BooleanProperty cash = new SimpleBooleanProperty(); 

	private BooleanProperty bankTransfer = new SimpleBooleanProperty(); 

	private BooleanProperty credit = new SimpleBooleanProperty();  
//
//   @FXML
//   private ToggleGroup toogleStatus;
	
	private BooleanProperty sended = new SimpleBooleanProperty();  

	private BooleanProperty pending = new SimpleBooleanProperty(); 

	private BooleanProperty Aprobed = new SimpleBooleanProperty(); 

	private StringProperty searchProduct = new SimpleStringProperty(); 

	private ObjectProperty<Image> imgProduct = new SimpleObjectProperty<Image>();

	private StringProperty productDescription = new SimpleStringProperty(); 

	private StringProperty udsStock = new SimpleStringProperty(); 

	private StringProperty quantity = new SimpleStringProperty(); 


	private ListProperty<ProductBean> listProducts = new SimpleListProperty<>(); 
	
	private ObjectProperty<ProductBean> productSelected = new SimpleObjectProperty<>(); 

	private ListProperty<InvoiceDetailBean> listDetails = new SimpleListProperty<>(); 
	
	private ObjectProperty<InvoiceDetailBean> detailSelected = new SimpleObjectProperty<>(); 

	private StringProperty price = new SimpleStringProperty(); 
	
	private StringProperty priceTaxIncluded = new SimpleStringProperty(); 
	
	private StringProperty taxPercentage = new SimpleStringProperty(); 
	
	private StringProperty taxTotal = new SimpleStringProperty();
	
	
	private ListProperty<CustomerBean> listCustomers = new SimpleListProperty<>(); 
	
	private ListProperty<CompanyBean> listCompanies = new SimpleListProperty<>();

	public final ListProperty<InvoiceBean> listInvoicesProperty() {
		return this.listInvoices;
	}
	

	public final ObservableList<InvoiceBean> getListInvoices() {
		return this.listInvoicesProperty().get();
	}
	

	public final void setListInvoices(final ObservableList<InvoiceBean> listInvoices) {
		this.listInvoicesProperty().set(listInvoices);
	}
	

	public final ObjectProperty<InvoiceBean> invoiceSelectedProperty() {
		return this.invoiceSelected;
	}
	

	public final InvoiceBean getInvoiceSelected() {
		return this.invoiceSelectedProperty().get();
	}
	

	public final void setInvoiceSelected(final InvoiceBean invoiceSelected) {
		this.invoiceSelectedProperty().set(invoiceSelected);
	}
	

	public final StringProperty customerNameProperty() {
		return this.customerName;
	}
	

	public final String getCustomerName() {
		return this.customerNameProperty().get();
	}
	

	public final void setCustomerName(final String customerName) {
		this.customerNameProperty().set(customerName);
	}
	

	public final StringProperty customerNIFProperty() {
		return this.customerNIF;
	}
	

	public final String getCustomerNIF() {
		return this.customerNIFProperty().get();
	}
	

	public final void setCustomerNIF(final String customerNIF) {
		this.customerNIFProperty().set(customerNIF);
	}
	

	public final StringProperty customerAddressProperty() {
		return this.customerAddress;
	}
	

	public final String getCustomerAddress() {
		return this.customerAddressProperty().get();
	}
	

	public final void setCustomerAddress(final String customerAddress) {
		this.customerAddressProperty().set(customerAddress);
	}
	

	public final StringProperty customerPhoneProperty() {
		return this.customerPhone;
	}
	

	public final String getCustomerPhone() {
		return this.customerPhoneProperty().get();
	}
	

	public final void setCustomerPhone(final String customerPhone) {
		this.customerPhoneProperty().set(customerPhone);
	}
	

	public final StringProperty conceptDescriptionProperty() {
		return this.conceptDescription;
	}
	

	public final String getConceptDescription() {
		return this.conceptDescriptionProperty().get();
	}
	

	public final void setConceptDescription(final String conceptDescription) {
		this.conceptDescriptionProperty().set(conceptDescription);
	}
	

	public final ListProperty<TaxBean> listTaxesProperty() {
		return this.listTaxes;
	}
	

	public final ObservableList<TaxBean> getListTaxes() {
		return this.listTaxesProperty().get();
	}
	

	public final void setListTaxes(final ObservableList<TaxBean> listTaxes) {
		this.listTaxesProperty().set(listTaxes);
	}
	

	public final ObjectProperty<TaxBean> taxSelectedProperty() {
		return this.taxSelected;
	}
	

	public final TaxBean getTaxSelected() {
		return this.taxSelectedProperty().get();
	}
	

	public final void setTaxSelected(final TaxBean taxSelected) {
		this.taxSelectedProperty().set(taxSelected);
	}
	

	public final BooleanProperty cashProperty() {
		return this.cash;
	}
	

	public final boolean isCash() {
		return this.cashProperty().get();
	}
	

	public final void setCash(final boolean cash) {
		this.cashProperty().set(cash);
	}
	

	public final BooleanProperty bankTransferProperty() {
		return this.bankTransfer;
	}
	

	public final boolean isBankTransfer() {
		return this.bankTransferProperty().get();
	}
	

	public final void setBankTransfer(final boolean bankTransfer) {
		this.bankTransferProperty().set(bankTransfer);
	}
	

	public final BooleanProperty creditProperty() {
		return this.credit;
	}
	

	public final boolean isCredit() {
		return this.creditProperty().get();
	}
	

	public final void setCredit(final boolean credit) {
		this.creditProperty().set(credit);
	}
	

	public final BooleanProperty sendedProperty() {
		return this.sended;
	}
	

	public final boolean isSended() {
		return this.sendedProperty().get();
	}
	

	public final void setSended(final boolean sended) {
		this.sendedProperty().set(sended);
	}
	

	public final BooleanProperty pendingProperty() {
		return this.pending;
	}
	

	public final boolean isPending() {
		return this.pendingProperty().get();
	}
	

	public final void setPending(final boolean pending) {
		this.pendingProperty().set(pending);
	}
	

	public final BooleanProperty AprobedProperty() {
		return this.Aprobed;
	}
	

	public final boolean isAprobed() {
		return this.AprobedProperty().get();
	}
	

	public final void setAprobed(final boolean Aprobed) {
		this.AprobedProperty().set(Aprobed);
	}
	

	public final StringProperty searchProductProperty() {
		return this.searchProduct;
	}
	

	public final String getSearchProduct() {
		return this.searchProductProperty().get();
	}
	

	public final void setSearchProduct(final String searchProduct) {
		this.searchProductProperty().set(searchProduct);
	}
	

	public final ObjectProperty<Image> imgProductProperty() {
		return this.imgProduct;
	}
	

	public final Image getImgProduct() {
		return this.imgProductProperty().get();
	}
	

	public final void setImgProduct(final Image imgProduct) {
		this.imgProductProperty().set(imgProduct);
	}
	

	public final StringProperty productDescriptionProperty() {
		return this.productDescription;
	}
	

	public final String getProductDescription() {
		return this.productDescriptionProperty().get();
	}
	

	public final void setProductDescription(final String productDescription) {
		this.productDescriptionProperty().set(productDescription);
	}
	

	public final StringProperty udsStockProperty() {
		return this.udsStock;
	}
	

	public final String getUdsStock() {
		return this.udsStockProperty().get();
	}
	

	public final void setUdsStock(final String udsStock) {
		this.udsStockProperty().set(udsStock);
	}
	

	public final StringProperty quantityProperty() {
		return this.quantity;
	}
	

	public final String getQuantity() {
		return this.quantityProperty().get();
	}
	

	public final void setQuantity(final String quantity) {
		this.quantityProperty().set(quantity);
	}
	

	public final ListProperty<ProductBean> listProductsProperty() {
		return this.listProducts;
	}
	

	public final ObservableList<ProductBean> getListProducts() {
		return this.listProductsProperty().get();
	}
	

	public final void setListProducts(final ObservableList<ProductBean> listProducts) {
		this.listProductsProperty().set(listProducts);
	}
	

	public final ObjectProperty<ProductBean> productSelectedProperty() {
		return this.productSelected;
	}
	

	public final ProductBean getProductSelected() {
		return this.productSelectedProperty().get();
	}
	

	public final void setProductSelected(final ProductBean productSelected) {
		this.productSelectedProperty().set(productSelected);
	}
	

	public final ListProperty<InvoiceDetailBean> listDetailsProperty() {
		return this.listDetails;
	}
	

	public final ObservableList<InvoiceDetailBean> getListDetails() {
		return this.listDetailsProperty().get();
	}
	

	public final void setListDetails(final ObservableList<InvoiceDetailBean> listDetails) {
		this.listDetailsProperty().set(listDetails);
	}
	

	public final ObjectProperty<InvoiceDetailBean> detailSelectedProperty() {
		return this.detailSelected;
	}
	

	public final InvoiceDetailBean getDetailSelected() {
		return this.detailSelectedProperty().get();
	}
	

	public final void setDetailSelected(final InvoiceDetailBean detailSelected) {
		this.detailSelectedProperty().set(detailSelected);
	}
	

	public final StringProperty priceProperty() {
		return this.price;
	}
	

	public final String getPrice() {
		return this.priceProperty().get();
	}
	

	public final void setPrice(final String price) {
		this.priceProperty().set(price);
	}
	

	public final StringProperty priceTaxIncludedProperty() {
		return this.priceTaxIncluded;
	}
	

	public final String getPriceTaxIncluded() {
		return this.priceTaxIncludedProperty().get();
	}
	

	public final void setPriceTaxIncluded(final String priceTaxIncluded) {
		this.priceTaxIncludedProperty().set(priceTaxIncluded);
	}
	

	public final StringProperty taxPercentageProperty() {
		return this.taxPercentage;
	}
	

	public final String getTaxPercentage() {
		return this.taxPercentageProperty().get();
	}
	

	public final void setTaxPercentage(final String taxPercentage) {
		this.taxPercentageProperty().set(taxPercentage);
	}
	

	public final StringProperty taxTotalProperty() {
		return this.taxTotal;
	}
	

	public final String getTaxTotal() {
		return this.taxTotalProperty().get();
	}
	

	public final void setTaxTotal(final String taxTotal) {
		this.taxTotalProperty().set(taxTotal);
	}


	public final ListProperty<PayMethodBean> payMethodsListProperty() {
		return this.payMethodsList;
	}
	


	public final ObservableList<PayMethodBean> getPayMethodsList() {
		return this.payMethodsListProperty().get();
	}
	


	public final void setPayMethodsList(final ObservableList<PayMethodBean> payMethodsList) {
		this.payMethodsListProperty().set(payMethodsList);
	}
	


	public final ObjectProperty<PayMethodBean> payMethodSelectedProperty() {
		return this.payMethodSelected;
	}
	


	public final PayMethodBean getPayMethodSelected() {
		return this.payMethodSelectedProperty().get();
	}
	


	public final void setPayMethodSelected(final PayMethodBean payMethodSelected) {
		this.payMethodSelectedProperty().set(payMethodSelected);
	}


	public final ListProperty<CustomerBean> listCustomersProperty() {
		return this.listCustomers;
	}
	


	public final ObservableList<CustomerBean> getListCustomers() {
		return this.listCustomersProperty().get();
	}
	


	public final void setListCustomers(final ObservableList<CustomerBean> listCustomers) {
		this.listCustomersProperty().set(listCustomers);
	}


	public final ListProperty<CompanyBean> listCompaniesProperty() {
		return this.listCompanies;
	}
	


	public final ObservableList<CompanyBean> getListCompanies() {
		return this.listCompaniesProperty().get();
	}
	


	public final void setListCompanies(final ObservableList<CompanyBean> listCompanies) {
		this.listCompaniesProperty().set(listCompanies);
	}
	
	
	
	
	
	

	
	
}

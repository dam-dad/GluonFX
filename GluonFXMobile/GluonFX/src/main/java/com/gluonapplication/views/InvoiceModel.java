package com.gluonapplication.views;

import com.model.beans.Company;
import com.model.beans.Customer;
import com.model.beans.Invoice;
import com.model.beans.InvoiceDetail;
import com.model.beans.PayMethod;
import com.model.beans.Product;
import com.model.beans.Tax;

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

/**
 * Clase de tipo modelo para invoice
 * @author moimah
 *
 */
public class InvoiceModel {


	private ListProperty<Invoice> listInvoices = new SimpleListProperty<>(); 	
	
	private ObjectProperty<Invoice> invoiceSelected = new SimpleObjectProperty<>(); 

	private StringProperty customerName = new SimpleStringProperty(); 

	private StringProperty customerNIF = new SimpleStringProperty(); 

	private StringProperty customerAddress = new SimpleStringProperty(); 

	private StringProperty customerPhone = new SimpleStringProperty(); 
	
	private StringProperty conceptDescription = new SimpleStringProperty(); 
	
	private StringProperty customerEmail = new SimpleStringProperty();
	
	private StringProperty customerCity = new SimpleStringProperty();
	
	private ListProperty<Tax> listTaxes = new SimpleListProperty<>();
	
	private ObjectProperty<Tax> taxSelected = new SimpleObjectProperty<>(); 
		
	private ListProperty<PayMethod> payMethodsList = new SimpleListProperty<>();
	
	private ObjectProperty<PayMethod> payMethodSelected = new SimpleObjectProperty<>();	

	private BooleanProperty cash = new SimpleBooleanProperty(); 

	private BooleanProperty bankTransfer = new SimpleBooleanProperty(); 

	private BooleanProperty credit = new SimpleBooleanProperty();  

	private BooleanProperty released = new SimpleBooleanProperty();  

	private BooleanProperty pending = new SimpleBooleanProperty(); 

	private BooleanProperty Aprobed = new SimpleBooleanProperty(); 

	private StringProperty searchProduct = new SimpleStringProperty(); 

	private ObjectProperty<Image> imgProduct = new SimpleObjectProperty<Image>();

	private StringProperty productDescription = new SimpleStringProperty(); 

	private StringProperty udsStock = new SimpleStringProperty(); 

	private StringProperty quantity = new SimpleStringProperty(); 
	
	private StringProperty priceUnit = new SimpleStringProperty();


	private ListProperty<Product> listProducts = new SimpleListProperty<>(); 
	
	private ObjectProperty<Product> productSelected = new SimpleObjectProperty<>(); 

	private ListProperty<InvoiceDetail> listDetails = new SimpleListProperty<>(); 
	
	private ObjectProperty<InvoiceDetail> detailSelected = new SimpleObjectProperty<>(); 

	private StringProperty price = new SimpleStringProperty(); 
	
	private StringProperty priceTaxIncluded = new SimpleStringProperty(); 
	
	private StringProperty taxPercentage = new SimpleStringProperty(); 
	
	private StringProperty taxTotal = new SimpleStringProperty();
	
	
	private ListProperty<Customer> listCustomers = new SimpleListProperty<>(); 
	
	private ListProperty<Company> listCompanies = new SimpleListProperty<>();

	public final ListProperty<Invoice> listInvoicesProperty() {
		return this.listInvoices;
	}
	

	public final ObservableList<Invoice> getListInvoices() {
		return this.listInvoicesProperty().get();
	}
	

	public final void setListInvoices(final ObservableList<Invoice> listInvoices) {
		this.listInvoicesProperty().set(listInvoices);
	}
	

	public final ObjectProperty<Invoice> invoiceSelectedProperty() {
		return this.invoiceSelected;
	}
	

	public final Invoice getInvoiceSelected() {
		return this.invoiceSelectedProperty().get();
	}
	

	public final void setInvoiceSelected(final Invoice invoiceSelected) {
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
	

	public final ListProperty<Tax> listTaxesProperty() {
		return this.listTaxes;
	}
	

	public final ObservableList<Tax> getListTaxes() {
		return this.listTaxesProperty().get();
	}
	

	public final void setListTaxes(final ObservableList<Tax> listTaxes) {
		this.listTaxesProperty().set(listTaxes);
	}
	

	public final ObjectProperty<Tax> taxSelectedProperty() {
		return this.taxSelected;
	}
	

	public final Tax getTaxSelected() {
		return this.taxSelectedProperty().get();
	}
	

	public final void setTaxSelected(final Tax taxSelected) {
		this.taxSelectedProperty().set(taxSelected);
	}
	

	public final ListProperty<PayMethod> payMethodsListProperty() {
		return this.payMethodsList;
	}
	

	public final ObservableList<PayMethod> getPayMethodsList() {
		return this.payMethodsListProperty().get();
	}
	

	public final void setPayMethodsList(final ObservableList<PayMethod> payMethodsList) {
		this.payMethodsListProperty().set(payMethodsList);
	}
	

	public final ObjectProperty<PayMethod> payMethodSelectedProperty() {
		return this.payMethodSelected;
	}
	

	public final PayMethod getPayMethodSelected() {
		return this.payMethodSelectedProperty().get();
	}
	

	public final void setPayMethodSelected(final PayMethod payMethodSelected) {
		this.payMethodSelectedProperty().set(payMethodSelected);
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
	

	public final BooleanProperty releasedProperty() {
		return this.released;
	}
	

	public final boolean isReleased() {
		return this.releasedProperty().get();
	}
	

	public final void setReleased(final boolean released) {
		this.releasedProperty().set(released);
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
	

	public final ListProperty<Product> listProductsProperty() {
		return this.listProducts;
	}
	

	public final ObservableList<Product> getListProducts() {
		return this.listProductsProperty().get();
	}
	

	public final void setListProducts(final ObservableList<Product> listProducts) {
		this.listProductsProperty().set(listProducts);
	}
	

	public final ObjectProperty<Product> productSelectedProperty() {
		return this.productSelected;
	}
	

	public final Product getProductSelected() {
		return this.productSelectedProperty().get();
	}
	

	public final void setProductSelected(final Product productSelected) {
		this.productSelectedProperty().set(productSelected);
	}
	

	public final ListProperty<InvoiceDetail> listDetailsProperty() {
		return this.listDetails;
	}
	

	public final ObservableList<InvoiceDetail> getListDetails() {
		return this.listDetailsProperty().get();
	}
	

	public final void setListDetails(final ObservableList<InvoiceDetail> listDetails) {
		this.listDetailsProperty().set(listDetails);
	}
	

	public final ObjectProperty<InvoiceDetail> detailSelectedProperty() {
		return this.detailSelected;
	}
	

	public final InvoiceDetail getDetailSelected() {
		return this.detailSelectedProperty().get();
	}
	

	public final void setDetailSelected(final InvoiceDetail detailSelected) {
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
	

	public final ListProperty<Customer> listCustomersProperty() {
		return this.listCustomers;
	}
	

	public final ObservableList<Customer> getListCustomers() {
		return this.listCustomersProperty().get();
	}
	

	public final void setListCustomers(final ObservableList<Customer> listCustomers) {
		this.listCustomersProperty().set(listCustomers);
	}
	

	public final ListProperty<Company> listCompaniesProperty() {
		return this.listCompanies;
	}
	

	public final ObservableList<Company> getListCompanies() {
		return this.listCompaniesProperty().get();
	}
	

	public final void setListCompanies(final ObservableList<Company> listCompanies) {
		this.listCompaniesProperty().set(listCompanies);
	}


	public final StringProperty customerEmailProperty() {
		return this.customerEmail;
	}
	


	public final String getCustomerEmail() {
		return this.customerEmailProperty().get();
	}
	


	public final void setCustomerEmail(final String customerEmail) {
		this.customerEmailProperty().set(customerEmail);
	}
	


	public final StringProperty priceUnitProperty() {
		return this.priceUnit;
	}
	


	public final String getPriceUnit() {
		return this.priceUnitProperty().get();
	}
	


	public final void setPriceUnit(final String priceUnit) {
		this.priceUnitProperty().set(priceUnit);
	}


	public final StringProperty customerCityProperty() {
		return this.customerCity;
	}
	


	public final String getCustomerCity() {
		return this.customerCityProperty().get();
	}
	


	public final void setCustomerCity(final String customerCity) {
		this.customerCityProperty().set(customerCity);
	}
	
	
	

	
	

	
}

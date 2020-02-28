package com.model.beans;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;



public class Invoice {

	
	private IntegerProperty id = new SimpleIntegerProperty();
	
	private ObjectProperty<Company> company = new SimpleObjectProperty<>();
	
	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
	
	private ObjectProperty<PayMethod> payMethod = new SimpleObjectProperty<>();
	
	private ObjectProperty<Tax> tax = new SimpleObjectProperty<>();
	
	private StringProperty invoiceNumber = new SimpleStringProperty();
	
	private StringProperty invoiceDate = new SimpleStringProperty();
	
	private IntegerProperty status = new SimpleIntegerProperty();
	
	private IntegerProperty conceptId = new SimpleIntegerProperty();
	
	private DoubleProperty price = new SimpleDoubleProperty();
	
	private DoubleProperty taxTotal = new SimpleDoubleProperty();

	private DoubleProperty priceTaxesIncluded = new SimpleDoubleProperty();

	private ListProperty<ConceptInvoice> conceptInvoices = new SimpleListProperty<>(); 

	private ListProperty<InvoiceDetail> invoiceDetails = new SimpleListProperty<>();
	

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

	public final ObjectProperty<Company> companyProperty() {
		return this.company;
	}
	

	public final Company getCompany() {
		return this.companyProperty().get();
	}
	

	public final void setCompany(final Company company) {
		this.companyProperty().set(company);
	}
	

	public final ObjectProperty<Customer> customerProperty() {
		return this.customer;
	}
	

	public final Customer getCustomer() {
		return this.customerProperty().get();
	}
	

	public final void setCustomer(final Customer customer) {
		this.customerProperty().set(customer);
	}
	

	public final ObjectProperty<PayMethod> payMethodProperty() {
		return this.payMethod;
	}
	

	public final PayMethod getPayMethod() {
		return this.payMethodProperty().get();
	}
	

	public final void setPayMethod(final PayMethod payMethod) {
		this.payMethodProperty().set(payMethod);
	}
	

	public final ObjectProperty<Tax> taxProperty() {
		return this.tax;
	}
	

	public final Tax getTax() {
		return this.taxProperty().get();
	}
	

	public final void setTax(final Tax tax) {
		this.taxProperty().set(tax);
	}
	

	public final StringProperty invoiceNumberProperty() {
		return this.invoiceNumber;
	}
	

	public final String getInvoiceNumber() {
		return this.invoiceNumberProperty().get();
	}
	

	public final void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumberProperty().set(invoiceNumber);
	}
	

	public final StringProperty invoiceDateProperty() {
		return this.invoiceDate;
	}
	

	public final String getInvoiceDate() {
		return this.invoiceDateProperty().get();
	}
	

	public final void setInvoiceDate(final String invoiceDate) {
		this.invoiceDateProperty().set(invoiceDate);
	}
	

	public final IntegerProperty statusProperty() {
		return this.status;
	}
	

	public final int getStatus() {
		return this.statusProperty().get();
	}
	

	public final void setStatus(final int status) {
		this.statusProperty().set(status);
	}
	

	public final IntegerProperty conceptIdProperty() {
		return this.conceptId;
	}
	

	public final int getConceptId() {
		return this.conceptIdProperty().get();
	}
	

	public final void setConceptId(final int conceptId) {
		this.conceptIdProperty().set(conceptId);
	}
	

	public final DoubleProperty priceProperty() {
		return this.price;
	}
	

	public final double getPrice() {
		return this.priceProperty().get();
	}
	

	public final void setPrice(final double price) {
		this.priceProperty().set(price);
	}
	

	public final DoubleProperty taxTotalProperty() {
		return this.taxTotal;
	}
	

	public final double getTaxTotal() {
		return this.taxTotalProperty().get();
	}
	

	public final void setTaxTotal(final double taxTotal) {
		this.taxTotalProperty().set(taxTotal);
	}
	

	public final DoubleProperty priceTaxesIncludedProperty() {
		return this.priceTaxesIncluded;
	}
	

	public final double getPriceTaxesIncluded() {
		return this.priceTaxesIncludedProperty().get();
	}
	

	public final void setPriceTaxesIncluded(final double priceTaxesIncluded) {
		this.priceTaxesIncludedProperty().set(priceTaxesIncluded);
	}
	

	public final ListProperty<ConceptInvoice> conceptInvoicesProperty() {
		return this.conceptInvoices;
	}
	

	public final ObservableList<ConceptInvoice> getConceptInvoices() {
		return this.conceptInvoicesProperty().get();
	}
	

	public final void setConceptInvoices(final ObservableList<ConceptInvoice> conceptInvoices) {
		this.conceptInvoicesProperty().set(conceptInvoices);
	}
	

	public final ListProperty<InvoiceDetail> invoiceDetailsProperty() {
		return this.invoiceDetails;
	}
	

	public final ObservableList<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetailsProperty().get();
	}
	

	public final void setInvoiceDetails(final ObservableList<InvoiceDetail> invoiceDetails) {
		this.invoiceDetailsProperty().set(invoiceDetails);
	}
	 
	
	
	
}

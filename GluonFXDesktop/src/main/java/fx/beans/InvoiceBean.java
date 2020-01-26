package fx.beans;

import java.time.LocalDate;
import java.util.Date;

import entities.Company;
import entities.Concept;
import entities.Customer;
import entities.Invoice;
import entities.InvoiceDetail;
import entities.PayMethod;
import entities.Tax;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceBean {
		
	private Invoice invoice; 
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private ObjectProperty<Company> company = new SimpleObjectProperty<Company>(); 
	private ObjectProperty<Concept> concept = new SimpleObjectProperty<Concept>(); 
	private ObjectProperty<Customer> customer = new SimpleObjectProperty<Customer>();
	private ObjectProperty<PayMethod> payMethod= new SimpleObjectProperty<PayMethod>();
	private ObjectProperty<Tax> tax = new SimpleObjectProperty<Tax>();		
	private StringProperty invoiceNumber = new SimpleStringProperty();	
	private ObjectProperty<LocalDate> invoiceDate = new SimpleObjectProperty<LocalDate>();	
	private IntegerProperty status = new SimpleIntegerProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private DoubleProperty taxTotal = new SimpleDoubleProperty();
	private DoubleProperty priceTaxesIncluded = new SimpleDoubleProperty();
	private ListProperty<InvoiceDetail> invoiceDetails = new SimpleListProperty<InvoiceDetail>();
	
	public InvoiceBean(Invoice i) {
		this.invoice = i; 
		
			company.set(invoice.getCompany());
			concept.set(invoice.getConcept());
			customer.set(invoice.getCustomer());
			payMethod.set(invoice.getPayMethod());
			tax.set(invoice.getTax());
			invoiceNumber.set(invoice.getInvoiceNumber());
			invoiceDate.set(localDateConverter(invoice.getInvoiceDate()));
			status.set(invoice.getStatus());
			price.set(invoice.getPrice());
			taxTotal.set(invoice.getTaxTotal());
			priceTaxesIncluded.set(invoice.getPriceTaxesIncluded());
			
		
			invoiceDetails.set(FXCollections.observableArrayList(invoice.getInvoiceDetails()));
			
	
		
	}
	
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	
	public LocalDate localDateConverter(Date date) {		
		LocalDate dateA= new java.sql.Date(date.getTime()).toLocalDate();
		return dateA; 
	}


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
	


	public final ObjectProperty<Concept> conceptProperty() {
		return this.concept;
	}
	


	public final Concept getConcept() {
		return this.conceptProperty().get();
	}
	


	public final void setConcept(final Concept concept) {
		this.conceptProperty().set(concept);
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
	


	public final ObjectProperty<LocalDate> invoiceDateProperty() {
		return this.invoiceDate;
	}
	


	public final LocalDate getInvoiceDate() {
		return this.invoiceDateProperty().get();
	}
	


	public final void setInvoiceDate(final LocalDate invoiceDate) {
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

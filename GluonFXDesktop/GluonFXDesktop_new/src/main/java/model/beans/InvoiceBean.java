package model.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entities.ConceptInvoice;
import model.entities.Invoice;
import model.entities.InvoiceDetail;

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
	private ObjectProperty<CompanyBean> company = new SimpleObjectProperty<CompanyBean>(); 
	private IntegerProperty conceptId = new SimpleIntegerProperty();
	private ObjectProperty<CustomerBean> customer = new SimpleObjectProperty<CustomerBean>();
	private ObjectProperty<PayMethodBean> payMethod= new SimpleObjectProperty<PayMethodBean>();
	private ObjectProperty<TaxBean> tax = new SimpleObjectProperty<TaxBean>();		
	private StringProperty invoiceNumber = new SimpleStringProperty();	
	private ObjectProperty<LocalDate> invoiceDate = new SimpleObjectProperty<LocalDate>();	
	private IntegerProperty status = new SimpleIntegerProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private DoubleProperty taxTotal = new SimpleDoubleProperty();
	private DoubleProperty priceTaxesIncluded = new SimpleDoubleProperty();
	private ListProperty<InvoiceDetailBean> invoiceDetails = new SimpleListProperty<InvoiceDetailBean>();
	private ListProperty<ConceptInvoiceBean> conceptInvoices = new SimpleListProperty<>();
	
	public InvoiceBean(Invoice invoice) {
		
			this.invoice = invoice; 			
			try {company.set(new CompanyBean(invoice.getCompany()));	}catch (Exception e) {}		
			try {conceptId.set(invoice.getConceptId());}catch (Exception e) {}
			try {customer.set(new CustomerBean(invoice.getCustomer()));}catch (Exception e) {}
			try {payMethod.set(new PayMethodBean(invoice.getPayMethod()));}catch (Exception e) {}
			try {tax.set(new TaxBean(invoice.getTax()));}catch (Exception e) {}
			
			
			invoiceNumber.set(invoice.getInvoiceNumber());
			invoiceDate.set(localDateConverter(invoice.getInvoiceDate()));
			status.set(invoice.getStatus());
			price.set(invoice.getPrice());
			taxTotal.set(invoice.getTaxTotal());
			priceTaxesIncluded.set(invoice.getPriceTaxesIncluded());
			
			
			List<InvoiceDetailBean> list = new ArrayList<InvoiceDetailBean>();
			for(InvoiceDetail i : invoice.getInvoiceDetails()) {
				list.add(new InvoiceDetailBean(i));
			}
			invoiceDetails.set(FXCollections.observableArrayList(list));
			
			
			List<ConceptInvoiceBean> listB = new ArrayList<ConceptInvoiceBean>();
			for(ConceptInvoice c: invoice.getConceptInvoices()) {
				listB.add(new ConceptInvoiceBean(c));
			}
			conceptInvoices.set(FXCollections.observableArrayList(listB));
	
		
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
		this.invoice.setId(id);
	}
	


	public final ObjectProperty<CompanyBean> companyProperty() {
		return this.company;
	}
	


	public final CompanyBean getCompany() {
		return this.companyProperty().get();
	}
	


	public final void setCompany(final CompanyBean company) {
		this.companyProperty().set(company);
		this.invoice.setCompany(company.getCompany());
	}
	

	public final IntegerProperty conceptIdProperty() {
		return this.conceptId;
	}
	


	public final int getConceptId() {
		return this.conceptIdProperty().get();
	}
	


	public final void setConceptId(final int conceptId) {
		this.conceptIdProperty().set(conceptId);
		this.invoice.setConceptId(conceptId);
	}
	

	public final ObjectProperty<CustomerBean> customerProperty() {
		return this.customer;
	}
	


	public final CustomerBean getCustomer() {
		return this.customerProperty().get();
	}
	


	public final void setCustomer(final CustomerBean customer) {
		this.customerProperty().set(customer);
		this.invoice.setCustomer(customer.getCustomer());
	}
	


	public final ObjectProperty<PayMethodBean> payMethodProperty() {
		return this.payMethod;
	}
	


	public final PayMethodBean getPayMethod() {
		return this.payMethodProperty().get();
	}
	


	public final void setPayMethod(final PayMethodBean payMethod) {
		this.payMethodProperty().set(payMethod);
		this.invoice.setPayMethod(payMethod.getPayMethod());
	}
	


	public final ObjectProperty<TaxBean> taxProperty() {
		return this.tax;
	}
	


	public final TaxBean getTax() {
		return this.taxProperty().get();
	}
	


	public final void setTax(final TaxBean tax) {
		this.taxProperty().set(tax);
		this.invoice.setTax(tax.getTax());
	}
	


	public final StringProperty invoiceNumberProperty() {
		return this.invoiceNumber;
	}
	


	public final String getInvoiceNumber() {
		return this.invoiceNumberProperty().get();
	}
	


	public final void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumberProperty().set(invoiceNumber);
		this.invoice.setInvoiceNumber(invoiceNumber);
	}
	


	public final ObjectProperty<LocalDate> invoiceDateProperty() {
		return this.invoiceDate;
		
	}
	


	public final LocalDate getInvoiceDate() {
		return this.invoiceDateProperty().get();
	}
	


	public final void setInvoiceDate(final LocalDate invoiceDate) {
		this.invoiceDateProperty().set(invoiceDate);		
		LocalDate da = invoiceDate;			
		Date dateA = java.sql.Date.valueOf(da);
		this.invoice.setInvoiceDate(dateA);	
	}
	


	public final IntegerProperty statusProperty() {
		return this.status;
	}
	


	public final int getStatus() {
		return this.statusProperty().get();
	}
	


	public final void setStatus(final int status) {
		this.statusProperty().set(status);
		this.invoice.setStatus(status);
	}
	


	public final DoubleProperty priceProperty() {
		return this.price;
	}
	


	public final double getPrice() {
		return this.priceProperty().get();
	}
	


	public final void setPrice(final double price) {
		this.priceProperty().set(price);
		this.invoice.setPrice(price);
	}
	


	public final DoubleProperty taxTotalProperty() {
		return this.taxTotal;
	}
	


	public final double getTaxTotal() {
		return this.taxTotalProperty().get();
	}
	


	public final void setTaxTotal(final double taxTotal) {
		this.taxTotalProperty().set(taxTotal);
		this.invoice.setTaxTotal(taxTotal);
	}
	


	public final DoubleProperty priceTaxesIncludedProperty() {
		return this.priceTaxesIncluded;
	}
	


	public final double getPriceTaxesIncluded() {
		return this.priceTaxesIncludedProperty().get();
	}
	


	public final void setPriceTaxesIncluded(final double priceTaxesIncluded) {
		this.priceTaxesIncludedProperty().set(priceTaxesIncluded);
		this.invoice.setPriceTaxesIncluded(priceTaxesIncluded);
	}
	


	public final ListProperty<InvoiceDetailBean> invoiceDetailsProperty() {
		return this.invoiceDetails;
	}
	


	public final ObservableList<InvoiceDetailBean> getInvoiceDetails() {
		return this.invoiceDetailsProperty().get();
	}
	


	public final void setInvoiceDetails(final ObservableList<InvoiceDetailBean> invoiceDetails) {
		this.invoiceDetailsProperty().set(invoiceDetails);
		
		List<InvoiceDetail> list = new ArrayList<InvoiceDetail>();  
		for(InvoiceDetailBean i : invoiceDetails) {
			list.add(i.getInvoiceDetail());
		}
		this.invoice.setInvoiceDetails(list);
	}


	public final ListProperty<ConceptInvoiceBean> conceptInvoicesProperty() {
		return this.conceptInvoices;
	}
	


	public final ObservableList<ConceptInvoiceBean> getConceptInvoices() {
		return this.conceptInvoicesProperty().get();
	}
	


	public final void setConceptInvoices(final ObservableList<ConceptInvoiceBean> conceptInvoices) {
		this.conceptInvoicesProperty().set(conceptInvoices);
		List<ConceptInvoice> list = new ArrayList<ConceptInvoice>();  
		for(ConceptInvoiceBean i : conceptInvoices) {
			list.add(i.getConcept());
		}
		this.invoice.setConceptInvoices(list);
	}
	


	
	

}

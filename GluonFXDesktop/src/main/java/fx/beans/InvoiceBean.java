package fx.beans;

import java.util.Date;

import entities.Invoice;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InvoiceBean {
	
	private Invoice invoice; 
	
	private IntegerProperty invoiceId = new SimpleIntegerProperty();
	private StringProperty invoiceNumber = new SimpleStringProperty();
	private StringProperty companyId = new SimpleStringProperty();
	private StringProperty customerId = new SimpleStringProperty();
	private ObjectProperty<Date> invoiceDate = new SimpleObjectProperty<Date>();
	private IntegerProperty status = new SimpleIntegerProperty();
	private IntegerProperty conceptId = new SimpleIntegerProperty();
	private IntegerProperty payMethod= new SimpleIntegerProperty();	
	private DoubleProperty price = new SimpleDoubleProperty();
	private StringProperty taxId = new SimpleStringProperty();
	private DoubleProperty taxTotal = new SimpleDoubleProperty();
	private DoubleProperty priceTaxesIncluded = new SimpleDoubleProperty();
	
	public InvoiceBean(Invoice i) {
		this.invoice = i; 
		invoiceId.set(invoice.getInvoiceId());
		invoiceNumber.set(invoice.getInvoiceNumber());
		companyId.set(invoice.getCompanyId());
		customerId.set(invoice.getCustomerId());
		invoiceDate.set(invoice.getInvoiceDate());
		status.set(invoice.getStatus());
		conceptId.set(invoice.getConceptId());
		payMethod.set(invoice.getPayMethodId());
		price.set(invoice.getPrice());
		taxId.set(invoice.getTaxId());
		taxTotal.set(invoice.getTaxTotal());
		priceTaxesIncluded.set(invoice.getPriceTaxesIncluded());	
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public final IntegerProperty invoiceIdProperty() {
		return this.invoiceId;
	}
	

	public final int getInvoiceId() {
		return this.invoiceIdProperty().get();
	}
	

	public final void setInvoiceId(final int invoiceId) {
		this.invoiceIdProperty().set(invoiceId);
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
	

	public final StringProperty companyIdProperty() {
		return this.companyId;
	}
	

	public final String getCompanyId() {
		return this.companyIdProperty().get();
	}
	

	public final void setCompanyId(final String companyId) {
		this.companyIdProperty().set(companyId);
	}
	

	public final StringProperty customerIdProperty() {
		return this.customerId;
	}
	

	public final String getCustomerId() {
		return this.customerIdProperty().get();
	}
	

	public final void setCustomerId(final String customerId) {
		this.customerIdProperty().set(customerId);
	}
	

	public final ObjectProperty<Date> invoiceDateProperty() {
		return this.invoiceDate;
	}
	

	public final Date getInvoiceDate() {
		return this.invoiceDateProperty().get();
	}
	

	public final void setInvoiceDate(final Date invoiceDate) {
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
	

	public final IntegerProperty payMethodProperty() {
		return this.payMethod;
	}
	

	public final int getPayMethod() {
		return this.payMethodProperty().get();
	}
	

	public final void setPayMethod(final int payMethod) {
		this.payMethodProperty().set(payMethod);
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
	

	public final StringProperty taxIdProperty() {
		return this.taxId;
	}
	

	public final String getTaxId() {
		return this.taxIdProperty().get();
	}
	

	public final void setTaxId(final String taxId) {
		this.taxIdProperty().set(taxId);
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
	
	
	
	

}

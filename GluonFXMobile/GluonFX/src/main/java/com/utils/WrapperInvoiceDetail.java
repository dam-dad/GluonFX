package com.utils;

import com.model.beans.Invoice;
import com.model.beans.InvoiceDetail;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Wrapper de objetos Invoice e InvoiceDetail
 * @author moimah
 *
 */
public class WrapperInvoiceDetail {

	private ObjectProperty<InvoiceDetail> invoiceDetail = new SimpleObjectProperty<>(); 
	private ObjectProperty<Invoice> invoice = new SimpleObjectProperty<>();
	
	
	public final ObjectProperty<InvoiceDetail> invoiceDetailProperty() {
		return this.invoiceDetail;
	}
	
	public final InvoiceDetail getInvoiceDetail() {
		return this.invoiceDetailProperty().get();
	}
	
	public final void setInvoiceDetail(final InvoiceDetail invoiceDetail) {
		this.invoiceDetailProperty().set(invoiceDetail);
	}
	
	public final ObjectProperty<Invoice> invoiceProperty() {
		return this.invoice;
	}
	
	public final Invoice getInvoice() {
		return this.invoiceProperty().get();
	}
	
	public final void setInvoice(final Invoice invoice) {
		this.invoiceProperty().set(invoice);
	}
	 
	
	
}

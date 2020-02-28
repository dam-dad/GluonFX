package com.utils;

import com.model.beans.ConceptInvoice;
import com.model.beans.Invoice;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Wrapper de objetos Invoice y ConceptInvoice
 * @author moimah
 *
 */
public class WrapperConceptInvoice {

	private ObjectProperty<ConceptInvoice> conceptInvoice = new SimpleObjectProperty<>(); 
	private ObjectProperty<Invoice> invoice = new SimpleObjectProperty<>();
	
	
	public final ObjectProperty<ConceptInvoice> conceptInvoiceProperty() {
		return this.conceptInvoice;
	}
	
	public final ConceptInvoice getConceptInvoice() {
		return this.conceptInvoiceProperty().get();
	}
	
	public final void setConceptInvoice(final ConceptInvoice conceptInvoice) {
		this.conceptInvoiceProperty().set(conceptInvoice);
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

package com.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Pojo de InvoicePDF,
 * Objeto que contiene un pdf en B64 y un invoiceId con el nombre del fichero
 * @author moimah
 *
 */
public class InvoicePDF {
	
	private StringProperty base64 = new SimpleStringProperty();
	
	private StringProperty invoiceId = new SimpleStringProperty();

	public final StringProperty base64Property() {
		return this.base64;
	}
	

	public final String getBase64() {
		return this.base64Property().get();
	}
	

	public final void setBase64(final String base64) {
		this.base64Property().set(base64);
	}


	public final StringProperty invoiceIdProperty() {
		return this.invoiceId;
	}
	


	public final String getInvoiceId() {
		return this.invoiceIdProperty().get();
	}
	


	public final void setInvoiceId(final String invoiceId) {
		this.invoiceIdProperty().set(invoiceId);
	}
	
	
	
	

}

package com.utils;

import com.model.beans.Customer;
import com.model.beans.Invoice;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WrapperCustomerInvoice {

	private ObjectProperty<Customer> customer = new SimpleObjectProperty<>();
	private ObjectProperty<Invoice> invoice  = new SimpleObjectProperty<>();
	
	
	public final ObjectProperty<Customer> customerProperty() {
		return this.customer;
	}
	
	public final Customer getCustomer() {
		return this.customerProperty().get();
	}
	
	public final void setCustomer(final Customer customer) {
		this.customerProperty().set(customer);
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

package com.moimah.hibernate.spring.utils;

import com.moimah.hibernate.spring.entities.Customer;
import com.moimah.hibernate.spring.entities.Invoice;


/**
 * Clase de tipo wrapper de Invoice y Customer
 * 
 * 
 * @author moimah
 *
 */
public class RequestWrapperCustomerInvoice {
	
	private Customer customer;
	private Invoice invoice;
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	} 
	
	
	
	
	

}

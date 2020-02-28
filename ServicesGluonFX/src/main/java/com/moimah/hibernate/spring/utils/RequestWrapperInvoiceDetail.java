package com.moimah.hibernate.spring.utils;

import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.hibernate.spring.entities.InvoiceDetail;

/**
 * Clase de tipo Wrapper de Invoice e InvoiceDetail
 * 
 * 
 * @author moimah
 *
 */
public class RequestWrapperInvoiceDetail {
	
	private InvoiceDetail invoiceDetail = new InvoiceDetail(); 
	private Invoice invoice = new Invoice();
	
	
	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}
	public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	} 
	
	
	
}

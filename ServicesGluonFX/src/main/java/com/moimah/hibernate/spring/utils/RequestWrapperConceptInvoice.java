package com.moimah.hibernate.spring.utils;

import com.moimah.hibernate.spring.entities.ConceptInvoice;
import com.moimah.hibernate.spring.entities.Invoice;

/**
 * Clase de tipo wrapper de Invoice y ConceptInvoice
 * 
 * 
 * @author moimah
 *
 */
public class RequestWrapperConceptInvoice {
	
	private ConceptInvoice conceptInvoice; 
	private Invoice invoice;
	
	
	public ConceptInvoice getConceptInvoice() {
		return conceptInvoice;
	}
	public void setConceptInvoice(ConceptInvoice conceptInvoice) {
		this.conceptInvoice = conceptInvoice;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	} 
	
	
	

}

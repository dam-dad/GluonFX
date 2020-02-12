package com.utils;


import java.util.Iterator;
import java.util.List;

import com.model.entities.Company;
import com.model.entities.ConceptInvoice;
import com.model.entities.Customer;
import com.model.entities.Invoice;
import com.model.entities.PayMethod;
import com.model.entities.Tax;


public class Main {

	public static void main(String[] args) {
		
		Service service = new Service();
		
		Invoice invoice = new Invoice();
		
		//int id, String invoice_number, int company_id, int customer_id, String invoice_date, int status,
		//int concept_id, int pay_method_id, double price,  int tax_id, double tax_total, double price_tax_included
		
		//int company_id, int customer_id, String invoice_date, int concept_id, int pay_method_id, int tax_id, int status
		
		invoice.setId(3);
		Company c = new Company();
		c.setId(1);
		invoice.setInvoiceNumber("2012060001");
		invoice.setCompany(c);
		invoice.setPrice(0.00);
		invoice.setTaxTotal(0.0);
		invoice.setPriceTaxesIncluded(0.01);
		Customer cus = new Customer();
		cus.setId(1);
		invoice.setCustomer(cus);
		invoice.setInvoiceDate("12-06-2012");
		invoice.setConceptId(3);
		PayMethod p = new PayMethod();
		p.setId(1);
		invoice.setPayMethod(p);
		Tax t = new Tax();
		t.setId(1);
		invoice.setTax(t);
		invoice.setStatus(2);
		
		service.updateInvoice(invoice);
		
		
	}
}

package com.moimah.hibernate.spring.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moimah.hibernate.spring.daos.InvoiceDao;
import com.moimah.hibernate.spring.entities.Company;
import com.moimah.hibernate.spring.entities.ConceptInvoice;
import com.moimah.hibernate.spring.entities.Customer;
import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.hibernate.spring.entities.PayMethod;
import com.moimah.hibernate.spring.entities.Tax;


@Controller
public class InvoiceController {

	@Autowired
	private InvoiceDao invoiceDao; // Inyectamos el DAO dentro del Controller
	
	
	/*
	 * 	
	 * 
	 * http://localhost:9002/createInvoice?company_id=1&customer_id=1&invoice_date=12-06-2012&concept_id=1&pay_method_id=1&tax_id=1&status=0
	 * 
	 */
	@RequestMapping(value = "/createInvoice")
	@ResponseBody
	public String create(int company_id, int customer_id, String invoice_date, int concept_id, int pay_method_id, int tax_id, int status) {

		try {

			Invoice invoice = new Invoice(company_id,customer_id,invoice_date,concept_id,pay_method_id,tax_id, status);			
					
			invoiceDao.create(invoice);

			return "Factura creada correctamente";

		} catch (Exception e) {

			return "Error en la creación de la factura";
		}
	}
	
	
	/*
	 * 
	 * Elimina una factura, localizándolo por su Id
	 * 
	 * http://localhost:9002/deleteInvoice?id=0
	 * 
	 */	
	@RequestMapping(value = "/deleteInvoice")
	@ResponseBody
	public String delete(int id) {

		try {

			Invoice invoice = new Invoice();


			invoice.setId(id);

			invoiceDao.delete(invoice);

			return "Factura eliminada correctamente";

		} catch (Exception e) {

			return "Error en la eliminación de la factura";
		}

	}
	
	/*
	 * 
	 *  
	 * http://localhost:9002/updateInvoice?id=17&invoice_number=2012060003&company_id=1&customer_id=1&invoice_date=12-06-2012&status=0&concept_id=1&pay_method_id=1&price=0&tax_id=1&tax_total=0&price_tax_included=0
	 * 
	 */
	@RequestMapping(value = "/updateInvoice")
	@ResponseBody	
	public String update(int id, String invoice_number, int company_id, int customer_id, String invoice_date, int status, int concept_id, int pay_method_id, double price,  int tax_id, double tax_total, double price_tax_included) {
		
		try {
			
			Invoice invoice = new Invoice();
								
				invoice.setId(id);	
				invoice.setInvoiceNumber(invoice_number);
			
				Company company = new Company();
				company.setId(company_id);
				invoice.setCompany(company);				
		
				Customer customer = new Customer();
				customer.setId(customer_id);				
				invoice.setCustomer(customer);						
			
				Date date = new SimpleDateFormat("dd-MM-yyyy").parse(invoice_date);  
				invoice.setInvoiceDate(date);			
			
				invoice.setStatus(status);
			
				ConceptInvoice c = new ConceptInvoice();
				c.setId(concept_id);
				Set<ConceptInvoice> list = new HashSet<ConceptInvoice>();
				list.add(c);
				invoice.setConceptInvoices(list);
			
						
				PayMethod p = new PayMethod();
				p.setId(pay_method_id);				
				invoice.setPayMethod(p);
			
				invoice.setPrice(price);							
			
				Tax t = new Tax(); 
				t.setId(tax_id);
				invoice.setTax(t);
				
				invoice.setTaxTotal(tax_total);
				invoice.setPriceTaxesIncluded(price_tax_included);
			
			
		
			
			invoiceDao.update(invoice);
			
			return "Factura actualizada correctamente";
			
		} catch (Exception e) {
			
			return "Error al actualizar la factura"; 
			
		}

		
	}
	
	
	/*
	 * Devuelve una lista de todas las facturas
	 * http://localhost:9002/allInvoice
	 */
	
	@RequestMapping(value = "/allInvoice")
	@ResponseBody	
	public String  all() {
		
		
		List<Invoice> list = invoiceDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/*
	 * Devuelve una factura por si id
	 * http://localhost:9002/invoiceById?id=1
	 */	
	@RequestMapping(value = "/invoiceById")
	@ResponseBody	
	public String byId(int id) {
		
		Invoice	invoice = invoiceDao.getInvoiceById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(invoice);
				 
		
		return  json;
	}
		
}

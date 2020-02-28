package com.moimah.hibernate.spring.controller;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moimah.hibernate.spring.daos.InvoiceDao;
import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.pdf.CodingPDF;
import com.moimah.pdf.PDF;
import com.moimah.pdf.B64Util;

/**
 * 
 * Clase de tipo controller de entidad invoice
 * 
 * 
 * @author moimah
 *
 */
@Controller
public class InvoiceController {

	@Autowired
	private InvoiceDao invoiceDao; // Inyectamos el DAO dentro del Controller
	
	
	/**
	 * Inserta una nuevo invoice en la bbdd
	 * comprueba que este no exista y lo inserta
	 * @param invoice nuevo invoice a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/invoice", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String insert(@RequestBody Invoice Invoice) {

		try {				
			
			Invoice exist = invoiceDao.getInvoiceById(Invoice.getId());
			
			if(exist == null){
				invoiceDao.update(Invoice); //I dont know why but persist doesnt work
			}	

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	
	
	/**
	 * Elimina un invoice existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/invoice/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id")int id) {

		try {

			Invoice Invoice = new Invoice();
			Invoice.setId(id);

			invoiceDao.delete(Invoice);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	
	/**
	 * Actualiza un invoice existente en la bbdd
	 * comprueba que  exista y lo modifica
	 * @param invoice nuevo invoice a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "invoice", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody Invoice invoice) {
		
		try {				
			
			//check if product exist
			Invoice exist = invoiceDao.getInvoiceById(invoice.getId());
			
			if(exist != null){
				invoiceDao.update(invoice);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	/**
	 * Realiza una consulta de todos los invoice de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los invoice de la bbdd
	 */
	@RequestMapping(value = "/invoice", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody	
	public String  all() {
		
		
		List<Invoice> list = invoiceDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .setDateFormat("dd/MM/yyyy")
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/**
	 * Realiza una consulta de un invoice determinado a través de su id
	 * devuelve un Json con la lista de objetos	
	 * @param id de invoice a buscar
	 * @return json del invoice encontrado
	 */
	@RequestMapping(value = "/invoice/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id")int id) {
		
		Invoice Invoice = invoiceDao.getInvoiceById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .setDateFormat("dd/MM/yyyy")
				  .create();
				String json = gson.toJson(Invoice);
				 
		
		return  json;
	}
	
	
	
	/**
	 * Realiza una consulta de un invoice determinado a través de su id
	 * genera un pdf del invoice encontrada, lo convierte a un objeto
	 * codigPDF que contiene 2 parametros, id de invoice y pdf en B64
	 * devuelve un Json con objeto generado
	 * @param id de invoice a buscar
	 * @return json del invoice encontrado
	 */
	@RequestMapping(value = "/pdf/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String pdfById(@PathVariable("id")int id) {
		
		
		Invoice invoice = invoiceDao.getInvoiceById(id);
		CodingPDF codingPDF = null; 
		File file = null;
		
		if (invoice != null) {
			
			try {
				
				
			file = PDF.generarPdf(invoice);				
			String b64 = B64Util.encoder(file.getAbsolutePath());
			
			codingPDF = new CodingPDF(b64, invoice.getInvoiceNumber());
				

			} catch (Exception e) {

				System.out.println("Error");
			}
						
			}
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()				  
				  .create();
				String json = gson.toJson(codingPDF);
		
				return json;
		
	}
	
	
	
	
	
	
		
}

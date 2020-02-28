package com.moimah.hibernate.spring.controller;



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

import com.moimah.hibernate.spring.daos.InvoiceDetailDao;
import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.hibernate.spring.entities.InvoiceDetail;
import com.moimah.hibernate.spring.utils.RequestWrapperInvoiceDetail;

/**
 * 
 * Clase de tipo controller de entidad InvoiceDetail
 * 
 * 
 * @author moimah
 *
 */
@Controller
public class InvoiceDetailController {

	@Autowired
	private InvoiceDetailDao invoiceDetailDao; // Inyectamos el DAO dentro del Controller
	
	
	
	/**
	 * Inserta un nuevo invoiceDetail en la bbdd,
	 * recibe un wrapper de invoice e invoiceDetail
	 * comprueba que  no exista, asocia invoice e invoiceDetil
	 * y lo almacena en la bbdd
	 * @param wrapper de invoiceDetail
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/invoicedetail", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody RequestWrapperInvoiceDetail wrapper) {

		try {	
			
			Invoice invoice = wrapper.getInvoice();
			InvoiceDetail invoiceDetail = wrapper.getInvoiceDetail(); 

			invoiceDetail.setInvoice(invoice);
			
			InvoiceDetail exist = invoiceDetailDao.getInvoiceDetailById(invoiceDetail.getId());
			
			if(exist == null){
				
				invoiceDetailDao.update(invoiceDetail); //I dont know why but persist doesnt work
			}	

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	
	
	/**
	 * Elimina un invoiceDetail existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/invoicedetail/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id")int id) {

		try {

			InvoiceDetail invoiceDetail = new InvoiceDetail();
			invoiceDetail.setId(id);

			invoiceDetailDao.delete(invoiceDetail);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	/**
	 * Actualiza un invoiceDetail existente en la bbdd,
	 * recibe un wrapper de invoice e invoiceDetail
	 * comprueba que  exista, asocia invoice e invoiceDetil
	 * y lo modifica en la bbdd
	 * @param wrapper de invoiceDetail
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "invoicedetail", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody RequestWrapperInvoiceDetail wrapper) {
		
		try {			
			
			Invoice invoice = wrapper.getInvoice();
			InvoiceDetail invoiceDetail = wrapper.getInvoiceDetail(); 

			invoiceDetail.setInvoice(invoice);
			
			InvoiceDetail exist = invoiceDetailDao.getInvoiceDetailById(invoiceDetail.getId());
			
			
			if(exist != null){
				invoiceDetailDao.update(invoiceDetail);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	/**
	 * Realiza una consulta de todos los invoiceDetail de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los invoiceDetail de la bbdd
	 */
	@RequestMapping(value = "/invoicedetail", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody	
	public String  all() {
		
		
		List<InvoiceDetail> list = invoiceDetailDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/**
	 * Realiza una consulta de un invoiceDetail determinado a través de su id
	 * devuelve un Json con el objeto encontrado
	 * @param id de invoiceDetail a buscar
	 * @return json del invoiceDetail encontrado
	 */
	@RequestMapping(value = "/invoicedetail/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id")int id) {
		
		InvoiceDetail invoiceDetail = invoiceDetailDao.getInvoiceDetailById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(invoiceDetail);
				 
		
		return  json;
	}
	
		
}
package com.moimah.hibernate.spring.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moimah.hibernate.spring.daos.ConceptInvoiceDao;
import com.moimah.hibernate.spring.entities.ConceptInvoice;
import com.moimah.hibernate.spring.entities.Invoice;


@Controller
public class ConceptInvoiceController {

	@Autowired
	private ConceptInvoiceDao conceptInvoiceDao; // Inyectamos el DAO dentro del Controller
	
	
	
	/*
	 * Crea un nuevo concepto
	 * http://localhost:9002/createConceptInvoice?invoice_id=1&description=esto es una prueba&price=2000
	 */
	@RequestMapping(value = "/createConceptInvoice", method = RequestMethod.POST)
	@ResponseBody
	public String create(int invoice_id, String description, double price) {

		try {
			Invoice i = new Invoice();
			i.setId(invoice_id);
			ConceptInvoice concept = new ConceptInvoice(i, description, price);			
			conceptInvoiceDao.create(concept);

			return "Concepto creado correctamente";

		} catch (Exception e) {

			return "Error en la creación del concepto";
		}
	}
	
	/*
	 * Actualiza un nuevo concepto
	 * http://localhost:9002/updateConceptInvoice?id=1&invoice_id=1&description=esto es una prueba&price=2000
	 */
	@RequestMapping(value = "/updateConceptInvoice", method = RequestMethod.POST)
	@ResponseBody
	public String update(int id, int invoice_id, String description, double price) {

		try {	
			Invoice invoice = new Invoice();
			invoice.setId(invoice_id);
			
			ConceptInvoice concept = new ConceptInvoice();	
			concept.setId(id);
			concept.setInvoice(invoice);
			concept.setDescription(description);
			concept.setPrice(price);
			conceptInvoiceDao.update(concept);

			return "Concepto creado actualizado";

		} catch (Exception e) {

			return "Error en la creación del concepto";
		}
	}
	
	
	/*
	 * ELimina una concepto por su id
	 * http://localhost:9002/deleteConceptInvoice?id=2
	 */	
	@RequestMapping(value = "/deleteConceptInvoice", method = RequestMethod.POST)
	@ResponseBody
	public String delete(int id) {

		try {

			ConceptInvoice conceptInvoice = new ConceptInvoice();
			conceptInvoice.setId(id);

			conceptInvoiceDao.delete(conceptInvoice);

			return "Concepto eliminado correctamente";

		} catch (Exception e) {

			return "Error en la eliminación del concepto";
		}

	}
	
	
	/*
	 * Devuelve una lista de todOS los conceptos
	 * http://localhost:9002/allconceptInvoice
	 */	
	@RequestMapping(value = "/allConceptInvoice")
	@ResponseBody	
	public String  all() {
		
		
		List<ConceptInvoice> list = conceptInvoiceDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/*
	 * Devuelve un concepto por si id
	 * http://localhost:9002/conceptInvoiceById?id=1
	 */	
	@RequestMapping(value = "/conceptInvoiceById")
	@ResponseBody	
	public String byId(int id) {
		
		ConceptInvoice conceptInvoice = conceptInvoiceDao.getConceptInvoiceById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(conceptInvoice);
				 
		
		return  json;
	}
	
	
	
	
}

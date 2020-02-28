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
import com.moimah.hibernate.spring.daos.ConceptInvoiceDao;
import com.moimah.hibernate.spring.entities.ConceptInvoice;
import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.hibernate.spring.utils.RequestWrapperConceptInvoice;


/**
 * 
 * Clase de tipo controller de entidad ConceptInvoice
 * 
 * 
 * @author moimah
 *
 */
@Controller
public class ConceptInvoiceController {

	@Autowired
	private ConceptInvoiceDao conceptInvoiceDao; // Inyectamos el DAO dentro del Controller
	
	
	/**
	 * Inserta un nuevo conceptInvoice en la bbdd,
	 * recibe un wrapper de invoice e conceptInvoice
	 * comprueba que  no exista, asocia invoice e invoiceDetil
	 * y lo almacena en la bbdd
	 * @param wrapper de conceptInvoice
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/conceptinvoice", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody RequestWrapperConceptInvoice wrapper) {

		try {	
			
			Invoice invoice = wrapper.getInvoice();
			ConceptInvoice conceptInvoice = wrapper.getConceptInvoice(); 

			conceptInvoice.setInvoice(invoice);
						
			ConceptInvoice exist = conceptInvoiceDao.getConceptInvoiceById(conceptInvoice.getId());
			
			if(exist == null){
				
				conceptInvoiceDao.update(conceptInvoice); //I dont know why but persist doesnt work
			}	

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	
	
	/**
	 * Elimina un conceptInvoice existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/conceptinvoice/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id")int id) {

		try {

			ConceptInvoice conceptInvoice = new ConceptInvoice();
			conceptInvoice.setId(id);

			conceptInvoiceDao.delete(conceptInvoice);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	/**
	 * Actualiza un conceptInvoice existente en la bbdd,
	 * recibe un wrapper de invoice e conceptInvoice
	 * comprueba que  exista, asocia invoice e invoiceDetil
	 * y lo modifica en la bbdd
	 * @param wrapper de conceptInvoice
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "conceptinvoice", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody RequestWrapperConceptInvoice wrapper) {
		
		try {			
			
			Invoice invoice = wrapper.getInvoice();
			ConceptInvoice conceptInvoice = wrapper.getConceptInvoice(); 

			conceptInvoice.setInvoice(invoice);		
			ConceptInvoice exist = conceptInvoiceDao.getConceptInvoiceById(conceptInvoice.getId());
			
			
			if(exist != null){
				conceptInvoiceDao.update(conceptInvoice);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	/**
	 * Realiza una consulta de todos los conceptInvoice de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los conceptInvoice de la bbdd
	 */
	@RequestMapping(value = "/conceptinvoice", method = RequestMethod.GET, produces = "application/json")
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
	
	
	/**
	 * Realiza una consulta de un conceptInvoice determinado a través de su id
	 * devuelve un Json con el objeto encontrado
	 * @param id de conceptInvoice a buscar
	 * @return json del conceptInvoice encontrado
	 */
	@RequestMapping(value = "/conceptinvoice/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id")int id) {
		
		ConceptInvoice conceptInvoice = conceptInvoiceDao.getConceptInvoiceById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(conceptInvoice);
				 
		
		return  json;
	}
	
	
	
	
}

package com.moimah.hibernate.spring.controller;


import java.util.Collections;
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
import com.moimah.hibernate.spring.daos.CustomerDao;
import com.moimah.hibernate.spring.daos.InvoiceDao;
import com.moimah.hibernate.spring.entities.Customer;
import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.hibernate.spring.utils.RequestWrapperCustomerInvoice;

/**
 * 
 * Clase de tipo controller de entidad Customer
 * 
 * 
 * @author moimah
 *
 */
@Controller
public class CustomerController {

	@Autowired
	private CustomerDao customerDao; // Inyectamos el DAO dentro del Controller
	
	@Autowired
	private InvoiceDao invoiceDao = new InvoiceDao(); //Dao auxiliar para asociar customer a invoice
	

	/**
	 * Inserta una nuevo customer en la bbdd
	 * comprueba que este no exista y lo inserta
	 * @param customer nuevo customer a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/customer", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody Customer customer) {

		try {	
			
			
			Customer exist = customerDao.getCustomerById(customer.getId());
			
			if(exist == null){
				customerDao.update(customer); //I dont know why but persist doesnt work
			}	

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	
	/**
	 * Elimina un customer existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id")int id) {

		try {

			Customer customer = new Customer();
			customer.setId(id);

			customerDao.delete(customer);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	
	/**
	 * Actualiza un customer existente en la bbdd
	 * comprueba que  exista y lo modifica
	 * @param customer nuevo customer a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "customer", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody Customer customer) {
		
		try {				
			
			//check if product exist
			Customer exist = customerDao.getCustomerById(customer.getId());
			
			if(exist != null){
				customerDao.update(customer);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	
	/**
	 * Realiza una consulta de todos los customer de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los customer de la bbdd
	 */
	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody	
	public String  all() {
		
		
		List<Customer> list = customerDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	
	/**
	 * Realiza una consulta de un customer determinado a través de su id
	 * devuelve un Json con la lista de objetos	
	 * @param id de customer a buscar
	 * @return json del customer encontrado
	 */
	@RequestMapping(value = "/customer/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id")int id) {
		
		Customer customer = customerDao.getCustomerById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(customer);
				 
		
		return  json;
	}
	
	
	/**
	 * Inserta una nuevo customer en la bbdd
	 * comprueba que este no exista y lo inserta
	 * y le asocia la factura que viene en el Wrapper
	 * @param wrapper customerInvoice a crear y asociar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/customerInvoice", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody RequestWrapperCustomerInvoice wapper) {

		try {	
			
			System.out.println("HOLA TIOOO");
			Customer customer = wapper.getCustomer();	
			Invoice invoice = wapper.getInvoice();
					
			
			System.out.println(customer.getAddress());
			System.out.println(invoice.getInvoiceNumber());

			customerDao.update(customer);

			List<Customer> list = customerDao.getAll();
			Collections.reverse(list);

			for (Customer c : list) {

				if (c.getCustomerId() == customer.getCustomerId()) {
					invoice.setCustomer(c);
					invoiceDao.update(invoice);
					System.out.println("HECHO");
					break;
				}
			}
					
				
			
			
			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	
	
}

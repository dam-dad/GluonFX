package com.moimah.hibernate.spring.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moimah.hibernate.spring.daos.CustomerDao;
import com.moimah.hibernate.spring.entities.Customer;


@Controller
public class CustomerController {

	@Autowired
	private CustomerDao customerDao; // Inyectamos el DAO dentro del Controller
	

	/*
	 * Crea una nuevo cliente
	 * http://localhost:9002/createCustomer?customer_id=73828292&name=Manuel&address=calle%20Puente&city=la%20laguna&country=gibraltar&email=manuel@gay.com&phone=696969
	 */
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	@ResponseBody
	public String create(String customer_id, String name, String address, String city, String country, String email, String phone) {

		try {

			Customer customer = new Customer(customer_id, name, address, city, country, email, phone, null, null, null);
			
			customerDao.create(customer);

			return "Cliente creado correctamente";

		} catch (Exception e) {

			return "Error en la creación del cliente";
		}
	}
	
	
	/*
	 * Actualiza una cliente
	 * http://localhost:9002/updateCustomer?id=3&customer_id=555555&name=cansado&address=cama&city=la%20laguna&country=cataluna&email=yo@ami.com&phone=463622
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	@ResponseBody
	public String update(int id, String customer_id, String name, String address, String city, String country, String email, String phone) {

		try {

			Customer customer = new Customer();
			customer.setId(id);
			customer.setCustomerId(customer_id);
			customer.setName(name);
			customer.setAddress(address);
			customer.setCity(city);
			customer.setCountry(country);
			customer.setEmail(email);
			customer.setPhone(phone);
			
			customerDao.update(customer);

			return "cliente actualizado";

		} catch (Exception e) {

			return "Error en la actualización del cliente";
		}
	}
	
	
	
	/*
	 * ELimina una cliente por su id
	 * http://localhost:9002/deleteCustomer?id=2
	 */
	
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	@ResponseBody
	public String delete(int id) {

		try {

			Customer customer = new Customer();
			customer.setId(id);

			customerDao.delete(customer);

			return "Cliente eliminado correctamente";

		} catch (Exception e) {

			return "Error en la eliminación del cliente";
		}

	}
	
	
	
	/*
	 * Devuelve una lista de todOS los clientes
	 * http://localhost:9002/allCustomer
	 */	
	@RequestMapping(value = "/allCustomer")
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
	
	
	/*
	 * Devuelve un cliente por si id
	 * http://localhost:9002//customerById?id=1
	 */	
	@RequestMapping(value = "/customerById")
	@ResponseBody	
	public String byId(int id) {
		
		Customer customer = customerDao.getCustomerById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(customer);
				 
		
		return  json;
	}
	
	
	
	
	
}

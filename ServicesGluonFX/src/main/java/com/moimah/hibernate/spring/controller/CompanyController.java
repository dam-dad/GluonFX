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
import com.moimah.hibernate.spring.daos.CompanyDao;
import com.moimah.hibernate.spring.entities.Company;


/**
 * 
 * Clase de tipo controller de entidad Company
 * 
 * 
 * @author moimah
 *
 */
@Controller
public class CompanyController {

	@Autowired
	private CompanyDao companyDao; // Inyectamos el DAO dentro del Controller
	
	
	/**
	 * Inserta una nuevo company en la bbdd
	 * comprueba que este no exista y lo inserta
	 * @param company nuevo company a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/company", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody Company company) {

		try {	
			
			
			Company exist = companyDao.getCompanyById(company.getId());
			
			if(exist == null){
				companyDao.update(company); //I dont know why but persist doesnt work
			}	

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	
	/**
	 * Elimina un company existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id")int id) {

		try {

			Company company = new Company();
			company.setId(id);

			companyDao.delete(company);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	/**
	 * Actualiza un company existente en la bbdd
	 * comprueba que  exista y lo modifica
	 * @param company nuevo company a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "company", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody Company company) {
		
		try {				
			
			//check if product exist
			Company exist = companyDao.getCompanyById(company.getId());
			
			if(exist != null){
				companyDao.update(company);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	/**
	 * Realiza una consulta de todos los company de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los company de la bbdd
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody	
	public String  all() {
		
		
		List<Company> list = companyDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	
	/**
	 * Realiza una consulta de un company determinado a través de su id
	 * devuelve un Json con la lista de objetos	
	 * @param id de company a buscar
	 * @return json del company encontrado
	 */
	@RequestMapping(value = "/company/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id")int id) {
		
		Company company = companyDao.getCompanyById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(company);
				 
		
		return  json;
	}
	
}
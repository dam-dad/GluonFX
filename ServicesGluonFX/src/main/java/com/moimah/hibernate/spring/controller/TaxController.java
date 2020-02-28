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

import com.moimah.hibernate.spring.daos.TaxDao;

import com.moimah.hibernate.spring.entities.Tax;


/**
 * 
 * Clase de tipo controller de entidad Tax
 * 
 * 
 * @author moimah
 *
 */
@Controller
public class TaxController {

	@Autowired
	private TaxDao taxDao; // Inyectamos el DAO dentro del Controller
	
	
	/**
	 * Inserta una nuevo tax en la bbdd
	 * comprueba que este no exista y lo inserta
	 * @param tax nuevo tax a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/tax", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody Tax tax) {

		try {	
		
		
			Tax exist = taxDao.getTaxById(tax.getId());
			
			if(exist == null){
				taxDao.update(tax); //I dont know why but persist doesnt work
			}	

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	
	/**
	 * Elimina un tax existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/tax/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") int id) {


		try {

			Tax tax = new Tax();
			tax.setId(id);

			taxDao.delete(tax);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	/**
	 * Actualiza un tax existente en la bbdd
	 * comprueba que  exista y lo modifica
	 * @param tax nuevo tax a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/tax", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody Tax tax) {
		
		try {				
			
			//check if product exist
			Tax exist = taxDao.getTaxById(tax.getId());
			
			if(exist != null){
				taxDao.update(tax);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	/**
	 * Realiza una consulta de todos los tax de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los tax de la bbdd
	 */
	@RequestMapping(value = "/tax", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody		
	public String  all() {
		
		
		List<Tax> list = taxDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/**
	 * Realiza una consulta de un tax determinado a través de su id
	 * devuelve un Json con la lista de objetos	
	 * @param id de tax a buscar
	 * @return json del tax encontrado
	 */
	@RequestMapping(value = "/tax/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id")int id) {
		
		Tax	t = taxDao.getTaxById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(t);
				 
		
		return  json;
	}
		
}

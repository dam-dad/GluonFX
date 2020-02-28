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

import com.moimah.hibernate.spring.daos.PayMethodDao;
import com.moimah.hibernate.spring.entities.PayMethod;


/**
 * 
 * Clase de tipo controller de entidad PayMethod
 * 
 * 
 * @author moimah
 *
 */
@Controller
public class PayMethodController {

	@Autowired
	private PayMethodDao payMethodDao; // Inyectamos el DAO dentro del Controller
	
	
	/**
	 * Inserta una nuevo payMethod en la bbdd
	 * comprueba que este no exista y lo inserta
	 * @param payMethod nuevo payMethod a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/paymethod", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody PayMethod payMethod) {

		try {	
			
			
			PayMethod exist = payMethodDao.getPayMethodById(payMethod.getId());
			
			if(exist == null){
				payMethodDao.update(payMethod); //I dont know why but persist doesnt work
			}	

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}
	}
	
	/**
	 * Elimina un payMethod existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/paymethod/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id")int id) {

		try {

			PayMethod payMethod = new PayMethod();
			payMethod.setId(id);

			payMethodDao.delete(payMethod);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	/**
	 * Actualiza un payMethod existente en la bbdd
	 * comprueba que  exista y lo modifica
	 * @param payMethod nuevo payMethod a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "paymethod", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody PayMethod payMethod) {
		
		try {				
			
			//check if product exist
			PayMethod exist = payMethodDao.getPayMethodById(payMethod.getId());
			
			if(exist != null){
				payMethodDao.update(payMethod);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	/**
	 * Realiza una consulta de todos los payMethod de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los payMethod de la bbdd
	 */
	@RequestMapping(value = "/paymethod", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody	
	public String  all() {
		
		
		List<PayMethod> list = payMethodDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/**
	 * Realiza una consulta de un payMethod determinado a través de su id
	 * devuelve un Json con la lista de objetos	
	 * @param id de payMethod a buscar
	 * @return json del payMethod encontrado
	 */
	@RequestMapping(value = "/paymethod/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id")int id) {
		
		PayMethod payMethod = payMethodDao.getPayMethodById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(payMethod);
				 
		
		return  json;
	}
		
}

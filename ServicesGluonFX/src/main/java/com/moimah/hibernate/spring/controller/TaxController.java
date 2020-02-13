package com.moimah.hibernate.spring.controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.moimah.hibernate.spring.daos.TaxDao;

import com.moimah.hibernate.spring.entities.Tax;

@Controller
public class TaxController {

	@Autowired
	private TaxDao taxDao; // Inyectamos el DAO dentro del Controller
	
	
	/*
	 * 
	 * Crea un nuevo estudiante con un Id autogenerado, y con los datos recibidos
	 * por la URL 
	 * 
	 * http://localhost:9002/createTax?tax_id=IJO&percentage=19&description=impuesto para ijos de puta
	 * 
	 */
	@RequestMapping(value = "/createTax")
	@ResponseBody
	public String create(String tax_id,double percentage, String description) {

		try {

			Tax tax = new Tax(tax_id, percentage, description, null, null, null);
			
			taxDao.create(tax);

			return "Tasa creada correctamente";

		} catch (Exception e) {

			return "Error en la creación de la tasa";
		}
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * http://localhost:9002/deleteTax?id=17
	 * 
	 */	
	@RequestMapping(value = "/deleteTax")
	@ResponseBody
	public String delete(int id) {

		try {

			Tax tax = new Tax();
			tax.setId(id);

			taxDao.delete(tax);

			return "Tasa eliminada correctamente";

		} catch (Exception e) {

			return "Error en la eliminación de la tasa";
		}

	}
	/*
	 * 
	 *  
	 * http://localhost:9002/updateTax?id=17&tax_id=pepe&percentage=17&description=hola
	 * 
	 */
	@RequestMapping(value = "/updateTax")
	@ResponseBody	
	public String update(int id,String tax_id,double percentage, String description) {
		
		try {
			
			Tax t = new Tax();
								
			t.setId(id);
			
			t.setTaxId(tax_id);
			
			t.setPercentage(percentage);
			
			t.setDescription(description);

			taxDao.update(t);
			
			return "Tasa actualizada correctamente";
			
		} catch (Exception e) {
			
			return "Error al actualizar la tasa"; 
			
		}

		
	}
	
	
	/*
	 * Devuelve una lista de todas las facturas
	 * http://localhost:9002/allTax
	 */
	
	@RequestMapping(value = "/allTax")
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
	
	
	/*
	 * Devuelve una factura por si id
	 * http://localhost:9002/taxById?id=1
	 */	
	@RequestMapping(value = "/taxById")
	@ResponseBody	
	public String byId(int id) {
		
		Tax	t = taxDao.getTaxById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(t);
				 
		
		return  json;
	}
		
}

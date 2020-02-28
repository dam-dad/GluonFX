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
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.moimah.hibernate.spring.daos.ProductDao;
import com.moimah.hibernate.spring.entities.Product;
import com.moimah.hibernate.spring.utils.ProductPNG;
import com.moimah.hibernate.spring.utils.RequestWrapperProductPNG;

/**
 * 
 * Clase de tipo controller de entidad Product
 * 
 * 
 * @author moimah
 *
 */

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao; // Inyectamos el DAO dentro del Controller
	
	
	/**
	 * Inserta una nuevo product en la bbdd
	 * comprueba que este no exista y lo inserta
	 * @param product nuevo product a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/product", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String insert(@RequestBody Product product) {
		
		try {	
			
			//check if product exist
			Product exist = productDao.getProductById(product.getId());
			
			if(exist == null){
				productDao.create(product); //I dont know why but persist doesnt work
			}	
						
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	/**
	 * Elimina un product existente en la bbdd a través de su Id
	 * @param id
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") int id) {

		try {

			Product product = new Product();
			product.setId(id);

			productDao.delete(product);

			return "Ok";

		} catch (Exception e) {

			return "Error";
		}

	}
	
	
	
	/**
	 * Actualiza un product existente en la bbdd
	 * comprueba que  exista y lo modifica
	 * @param product nuevo product a insertar
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/product", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String update(@RequestBody Product product) {
		
		try {				
			
			//check if product exist
			Product exist = productDao.getProductById(product.getId());
			
			if(exist != null){
				productDao.update(product);
			}
					
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}

		
	}
	
	
	
	/**
	 * Recibe un Wrapper de png y product y lo envia a un php alojado en otro servidor,
	 * mediante unirest, se realiza verificacion de si contiene id e imagen antes de ser
	 * enviado. El servidor se encarga de interpretar si es un nuevo producto o existe,  
	 * se aloja y asocia la imagen al producto insertado/actualizado
	 * @param wrapperProductPNG wrapper de producto y objeto productPNG
	 * @return mensaje de confirmacion
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/png", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
	public String pngUpload(@RequestBody RequestWrapperProductPNG wrapperProductPNG) {
		
		Product product = null;
		ProductPNG productPNG = null;
	
		try {					
			
			
			try {
				product = wrapperProductPNG.getProduct();
				productPNG = wrapperProductPNG.getProductPNG();
			} catch (Exception e) {				
			}
			
			try {
				
				if(productPNG != null ) {
				
					if(product.getId() != 0) {
						Unirest.post("https://www.moimah.com/gluonfx/uploads/add.php")	
						.field("id", product.getId())
						.field("product_id", product.getProductId())								
						.field("name", product.getName())
						.field("description", product.getDescription())
						.field("price", product.getPrice())
						.field("stock", product.getStock())
						.field("image", productPNG.getB64())
						.asString();
					}else {
						
						Unirest.post("https://www.moimah.com/gluonfx/uploads/add.php")						
						.field("product_id", product.getProductId())								
						.field("name", product.getName())
						.field("description", product.getDescription())
						.field("price", product.getPrice())
						.field("stock", product.getStock())
						.field("image", productPNG.getB64())
						.asString();
					}
					
				}else {
					
					if(product.getId() != 0) {
						Unirest.post("https://www.moimah.com/gluonfx/uploads/add.php")	
						.field("id", product.getId())
						.field("product_id", product.getProductId())								
						.field("name", product.getName())
						.field("description", product.getDescription())
						.field("price", product.getPrice())
						.field("stock", product.getStock())						
						.asString();
						
					}else {
						
						Unirest.post("https://www.moimah.com/gluonfx/uploads/add.php")						
						.field("product_id", product.getProductId())								
						.field("name", product.getName())
						.field("description", product.getDescription())
						.field("price", product.getPrice())
						.field("stock", product.getStock())						
						.asString();
						
					}
				}
				
				
				
			} catch (UnirestException e) {
				e.printStackTrace();
			}
					
			
			System.out.println("Uploading image");
			
			return "Ok";
			
		} catch (Exception e) {
			
			return "Error"; 
			
		}
		
		
		
		
	}	
	
	
	
	
		
	/**
	 * Realiza una consulta de todos los product de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los product de la bbdd
	 */
	@RequestMapping(value = "/product", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody	
	public String  all() {
		
		
		List<Product> list = productDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		
				
		return  json;
	}	
	
	
	/**
	 * Realiza una consulta de todos los product de la bbdd
	 * devuelve un Json con la lista de objetos	
	 * @return json lista con todos los product de la bbdd
	 */
	@RequestMapping(value = "/product/{id}",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody	
	public String byId(@PathVariable("id") int id) {
		
		Product	p = productDao.getProductById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(p);
				 
		
		return  json;
	}
		
}


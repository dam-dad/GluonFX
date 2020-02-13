package com.moimah.hibernate.spring.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.moimah.hibernate.spring.daos.ProductDao;
import com.moimah.hibernate.spring.entities.Product;


@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao; // Inyectamos el DAO dentro del Controller
	
	
	/*
	 * 
	 * Crea un nuevo estudiante con un Id autogenerado, y con los datos recibidos
	 * por la URL 
	 * 
	 * http://localhost:9002/createProduct?product_id=12341&name=condon&description=esto me describe&price=20&stock=2000&url=https://www.google.com/search?q=panda&rlz=1C1GCEA_enES867ES867&sxsrf=ACYBGNS-LPbeZ4GipBssl02Sqvj5azKuVA:1581418771416&source=lnms&tbm=isch&sa=X&ved=2ahUKEwieyvGArMnnAhUO3IUKHcPXCcEQ_AUoAXoECAoQAw&biw=1920&bih=920#imgrc=uykVn9cn1hCugM
	 * 
	 */
	@RequestMapping(value = "/createProduct")
	@ResponseBody
	public String create(String product_id, String name, String description, double price, int stock, String url) {

		try {

			Product product = new Product(product_id, name, description, price, stock, url, null, null, null);
			
			productDao.create(product);

			return "Producto creado correctamente";

		} catch (Exception e) {

			return "Error en la creación del producto";
		}
	}
	
	
	/*
	 * 
	 * Elimina un estudiante, localizándolo por su Id
	 * 
	 * http://localhost:9002/deleteProduct?id=1
	 * 
	 */	
	@RequestMapping(value = "/deleteProduct")
	@ResponseBody
	public String delete(int id) {

		try {

			Product product = new Product();
			product.setId(id);

			productDao.delete(product);

			return "Producto eliminado correctamente";

		} catch (Exception e) {

			return "Error en la eliminación del producto";
		}

	}
	/*
	 * Actualiza el nombre de estudiante, localizándolo por su id
	 *  
	 * http://localhost:9002/updateProduct?id=17&product_id=12341&name=condon&description=esto me describe&price=20&stock=2000&url=https://www.google.com/search?q=panda&rlz=1C1GCEA_enES867ES867&sxsrf=ACYBGNS-LPbeZ4GipBssl02Sqvj5azKuVA:1581418771416&source=lnms&tbm=isch&sa=X&ved=2ahUKEwieyvGArMnnAhUO3IUKHcPXCcEQ_AUoAXoECAoQAw&biw=1920&bih=920#imgrc=uykVn9cn1hCugM
	 * 
	 */
	@RequestMapping(value = "/updateProduct")
	@ResponseBody	
	public String update(int id,String product_id, String name, String description, double price, int stock, String url) {
		
		try {
			
			Product p = new Product();
			p.setId(id);
			p.setProductId(product_id);
			p.setName(name);
			p.setDescription(description);
			p.setPrice(price);
			p.setStock(stock);
			p.setUrl(url);
			
			
		
			
			productDao.update(p);
			
			return "Producto actualizado correctamente";
			
		} catch (Exception e) {
			
			return "Error al actualizar el producto"; 
			
		}

		
	}
	
	
	/*
	 * Devuelve una lista de todas las facturas
	 * http://localhost:9002/allProduct
	 */
	
	@RequestMapping(value = "/allProduct")
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
	
	
	/*
	 * Devuelve una factura por si id
	 * http://localhost:9002/productById?id=1
	 */	
	@RequestMapping(value = "/prodcutById")
	@ResponseBody	
	public String byId(int id) {
		
		Product	p = productDao.getProductById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(p);
				 
		
		return  json;
	}
		
}


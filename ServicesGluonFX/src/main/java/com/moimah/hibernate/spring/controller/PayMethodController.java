package com.moimah.hibernate.spring.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.moimah.hibernate.spring.daos.PayMethodDao;
import com.moimah.hibernate.spring.entities.PayMethod;


@Controller
public class PayMethodController {

	@Autowired
	private PayMethodDao payDao; // Inyectamos el DAO dentro del Controller
	
	
	/*
	 * 
	 * 
	 *  
	 * 
	 * http://localhost:9002/createPayMethod?description=dime algo
	 * 
	 */
	@RequestMapping(value = "/createPayMethod")
	@ResponseBody
	public String create(String description) {

		try {

			PayMethod payMethod = new PayMethod(description, null);
			
			payDao.create(payMethod);

			return "Metodo de pago creado correctamente";

		} catch (Exception e) {

			return "Error en la creación del metodo de pago";
		}
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * http://localhost:9002/deletePayMethod?id=1
	 * 
	 */	
	@RequestMapping(value = "/deletePayMethod")
	@ResponseBody
	public String delete(int id) {

		try {

			PayMethod payMethod = new PayMethod();
			payMethod.setId(id);

			payDao.delete(payMethod);

			return "Metodo de pago eliminado correctamente";

		} catch (Exception e) {

			return "Error en la eliminación del metodo de pago";
		}

	}
	/*
	 * 
	 *  
	 * http://localhost:9002/updatePayMethod?id=1&description=dime algo
	 * 
	 */
	@RequestMapping(value = "/updatePayMethod")
	@ResponseBody	
	public String update(int id, String description) {
		
		try {
			
			PayMethod pay = new PayMethod();
			
			pay.setId(id);
			pay.setDescription(description);
			
			
			payDao.update(pay);
			
			return "Metodo de pago actualizado correctamente";
			
		} catch (Exception e) {
			
			return "Error al actualizar el metodo de pago"; 
			
		}

		
	}
	
	
	/*
	 * Devuelve una lista de todas las facturas
	 * http://localhost:9002/allPayMethod
	 */
	
	@RequestMapping(value = "/allPayMethod")
	@ResponseBody	
	public String  all() {
		
		
		List<PayMethod> list = payDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/*
	 * Devuelve una factura por si id
	 * http://localhost:9002/payMethodById?id=1
	 */	
	@RequestMapping(value = "/payMethodById")
	@ResponseBody	
	public String byId(int id) {
		
		PayMethod	pay = payDao.getPayMethodById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(pay);
				 
		
		return  json;
	}
		
}

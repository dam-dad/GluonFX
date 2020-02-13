package com.moimah.hibernate.spring.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.moimah.hibernate.spring.daos.InvoiceDetailDao;
import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.hibernate.spring.entities.InvoiceDetail;
import com.moimah.hibernate.spring.entities.Product;


@Controller
public class InvoiceDetailController {

	@Autowired
	private InvoiceDetailDao invoiceDetailDao; // Inyectamos el DAO dentro del Controller
	
	
	/*
	 * 
	 *
	 * 
	 * http://localhost:9002/createInvoiceDetail?invoice_id=1&product_id=1&quantity=10
	 * 
	 */
	@RequestMapping(value = "/createInvoiceDetail")
	@ResponseBody
	public String create(int invoice_id, int product_id,double quantity) {

		try {
			Invoice i = new Invoice();
			i.setId(invoice_id);
			Product p = new Product();
			p.setId(product_id);
			InvoiceDetail invoiceDetail = new InvoiceDetail(i, p, quantity, 0.0, 0.0);
			
			invoiceDetailDao.create(invoiceDetail);

			return "Detalle creado correctamente";

		} catch (Exception e) {

			return "Error en la creación del detalle";
		}
	}
	
	
	/*
	 * 
	 *
	 * 
	 * http://localhost:9002/deleteInvoiceDetail?id=17
	 * 
	 */	
	@RequestMapping(value = "/deleteInvoiceDetail")
	@ResponseBody
	public String delete(int id) {

		try {

			InvoiceDetail invoiceDetail = new InvoiceDetail();


			invoiceDetail.setId(id);

			invoiceDetailDao.delete(invoiceDetail);

			return "Detalle eliminado correctamente";

		} catch (Exception e) {

			return "Error en la eliminación del detalle";
		}

	}
	
	/*
	 * 
	 *  
	 * http://localhost:9002/updateInvoiceDetail?id=17&invoice_id=1&product_id=1&quantity=10&price_ud=9.9
	 * 
	 */
	@RequestMapping(value = "/updateInvoiceDetail")
	@ResponseBody	
	public String update(int id, int invoice_id, int product_id,double quantity,double price_ud) {
		
		try {
			
			InvoiceDetail d = new InvoiceDetail();
								
			d.setId(id);
			Invoice i = new Invoice();
			i.setId(invoice_id);
			d.setInvoice(i);
			Product p= new Product();
			p.setId(product_id);
			d.setProduct(p);
			d.setQuantity(quantity);
			d.setPriceUnit(price_ud);
			
			
		
			
			invoiceDetailDao.update(d);
			
			return "Detalle actualizado correctamente";
			
		} catch (Exception e) {
			
			return "Error al actualizar el detalle"; 
			
		}

		
	}
	
	
	/*
	 * Devuelve una lista de todas las facturas
	 * http://localhost:9002/allInvoiceDetail
	 */
	
	@RequestMapping(value = "/allInvoiceDetail")
	@ResponseBody	
	public String  all() {
		
		
		List<InvoiceDetail> list = invoiceDetailDao.getAll();	
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(list);				 		
		

		return  json;
	}	
	
	
	/*
	 * Devuelve una factura por si id
	 * http://localhost:9002/invoiceDetailById?id=1
	 */	
	@RequestMapping(value = "/invoiceDetailById")
	@ResponseBody	
	public String byId(int id) {
		
		InvoiceDetail i = invoiceDetailDao.getInvoiceDetailById(id);
		
		Gson gson = new GsonBuilder()
				  .excludeFieldsWithoutExposeAnnotation()
				  .serializeNulls()
				  .create();
				String json = gson.toJson(i);
				 
		
		return  json;
	}
		
}
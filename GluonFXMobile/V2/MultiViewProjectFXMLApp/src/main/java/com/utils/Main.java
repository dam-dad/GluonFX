package com.utils;


import java.util.Iterator;
import java.util.List;

import com.model.entities.Company;
import com.model.entities.ConceptInvoice;
import com.model.entities.Customer;
import com.model.entities.Invoice;
import com.model.entities.InvoiceDetail;
import com.model.entities.PayMethod;
import com.model.entities.Product;
import com.model.entities.Tax;


public class Main {

	public static void main(String[] args) {
		//nt invoice?id, int product?id,double quantit
		Service service = new Service();
		
//		PayMethod payMethod = new PayMethod();
//		payMethod.setId(4);
//		payMethod.setDescription("Bitcoin");
//		
//		List<PayMethod> list = service.getAllPayMethods();
//		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getDescription());
//		}
//		
//		
		
	
//		
//		List<Product> list = service.getAllProducts();
//		
//		System.out.println(service.getProductbyId(3).getPrice());
//		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getPrice());
//		}
//		System.out.println("\n");
		
//		
//		Tax tax = new Tax();
//		tax.setId(3);
//		tax.setDescription("Impuesto a morosos");
//		tax.setPercentage(9.0);
//		tax.setTaxId("MORO");
//		
//		service.updateTax(tax);
//		service.deleteTax(tax);
		
		List<Tax> list = service.getAllTaxes();
		
		System.out.println(service.getTaxbyId(2).getPercentage());
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDescription());
		}
		System.out.println("\n");
		
	}
}

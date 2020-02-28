package com.moimah.pdf;

import java.util.ArrayList;
import java.util.List;

import com.moimah.hibernate.spring.entities.Invoice;
import com.moimah.hibernate.spring.entities.InvoiceDetail;


/**
 * Clase en la cual pasamos por parametro una factura y devolvemos una lista de
 * objetos CreatorPdf que son los detalles de la factura que enviamos.
 * 
 * 
 * @author blackbacil
 *
 */
public class FacturaDataProvider {

	/**
	 * Metodo que recibe una factura y devuelve una lista de creatorPDF
	 * @param invoice
	 * @return lista de creator pdf
	 */
	public static List<CreatorPdf> getPdf(Invoice invoice) {
		

		List<CreatorPdf> productos = new ArrayList<CreatorPdf>();

		List<InvoiceDetail> list = new ArrayList<InvoiceDetail>(invoice.getInvoiceDetails());

		for (int i = 0; i < invoice.getInvoiceDetails().size(); i++) {
			// creamos el objeto CreatorPdf usando un constructor
			productos.add(new CreatorPdf(list.get(i)));

		}
		return productos;
	}

}

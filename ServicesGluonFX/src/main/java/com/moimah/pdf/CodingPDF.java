package com.moimah.pdf;

import com.google.gson.annotations.Expose;

/**
 * Clase tipo POJO que contiene fichero PDF codificado en base64
 * y el nombre de la factura/fichero 
 * 
 * 
 * @author moimah
 *
 */
public class CodingPDF {
		
	@Expose
	private String base64;
	@Expose
	private String invoiceId; 
	
	public CodingPDF() {		
	}

	

	public CodingPDF(String base64, String invoiceId) {
		super();
		this.base64 = base64;
		this.invoiceId = invoiceId;
	}


	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	
	

}

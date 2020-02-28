package com.moimah.pdf;

import com.moimah.hibernate.spring.entities.InvoiceDetail;

/**
 * Clase que contiene los detalle a introducir en el reporte PDF
 * 
 * 
 * @author blackbacil
 *
 */
public class CreatorPdf {
	
	private String descripcion;
	private Double unidades;
	private Double precio;
	private Double total;
	private InvoiceDetail mainInvoiceDetail;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}


	/**
	 * Constructor simple
	 */
	public CreatorPdf() {
		
	}
	
	/**
	 * Constructor con factura
	 * @param mainInvoiceDetail factura a generar
	 */
	public CreatorPdf(InvoiceDetail mainInvoiceDetail) {
		this.mainInvoiceDetail= mainInvoiceDetail;
		this.total=mainInvoiceDetail.getPrice();
		this.precio= mainInvoiceDetail.getPriceUnit();
		this.descripcion=mainInvoiceDetail.getProduct().getName();
		this.unidades=mainInvoiceDetail.getQuantity();
				
	}

	/**
	 * Constructor con parametros
	 * @param descripcion
	 * @param unidades
	 * @param precio
	 */
	public CreatorPdf(String descripcion, Double unidades, Double precio) {
		super();
		this.descripcion = descripcion;
		this.unidades = unidades;
		this.precio = precio;
	}

	/**
	 * Constructor con mas parametros
	 * @param descripcion
	 * @param unidades
	 * @param precio
	 * @param iva
	 * @param total
	 * @param precioSin
	 * @param impuestos
	 */
	public CreatorPdf(String descripcion, Double unidades, Double precio, Double iva, Double total, Double precioSin,
			Double impuestos) {
		super();
		this.descripcion = descripcion;
		this.unidades = unidades;
		this.precio = precio;
		this.total = total;

	}

	public InvoiceDetail getMainInvoiceDetail() {
		return mainInvoiceDetail;
	}

	public void setMainInvoiceDetail(InvoiceDetail mainInvoiceDetail) {
		this.mainInvoiceDetail = mainInvoiceDetail;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getUnidades() {
		return unidades;
	}

	public void setUnidades(Double unidades) {
		this.unidades = unidades;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
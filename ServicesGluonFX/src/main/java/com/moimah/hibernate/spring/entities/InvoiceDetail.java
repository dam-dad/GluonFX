package com.moimah.hibernate.spring.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.gson.annotations.Expose;

import net.bytebuddy.build.ToStringPlugin.Exclude;

/**
 * Clase tipo Entity de invoice_detail
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "invoice_detail", catalog = "7057507_administration_db", uniqueConstraints = @UniqueConstraint(columnNames = "product_id"))
public class InvoiceDetail implements java.io.Serializable {
	
	@Expose
	private Integer id;
	@Exclude
	private Invoice invoice;
	@Expose
	private Product product;
	@Expose
	private Double quantity;
	@Expose
	private Double price;
	@Expose
	private Double priceUnit;
	
	public InvoiceDetail() {
	}

	public InvoiceDetail(Invoice invoice, Product product, Double quantity, Double price, Double priceUnit) {
		this.invoice = invoice;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.priceUnit = priceUnit;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id")
	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;		
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", unique = true)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "quantity",columnDefinition="double(16,2)")
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price", columnDefinition="double(16,2)")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "price_unit", columnDefinition="double(16,2)")
	public Double getPriceUnit() {
		return this.priceUnit;
	}

	public void setPriceUnit(Double priceUnit) {
		this.priceUnit = priceUnit;
	}

//	public int getInvoice_id() {
//		return invoice_id;
//	}

	

}

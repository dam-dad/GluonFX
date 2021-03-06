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

import com.google.gson.annotations.Expose;

import net.bytebuddy.build.ToStringPlugin.Exclude;

/**
 * Clase tipo Entity de concept_invoice
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "concept_invoice", catalog = "7057507_administration_db")
public class ConceptInvoice implements java.io.Serializable {
	
	@Expose
	private Integer id;
	@Exclude
	private Invoice invoice;
	@Expose
	private String description;
	@Expose
	private Double price;

	public ConceptInvoice() {
	}

	public ConceptInvoice(Invoice invoice, String description, Double price) {
		this.invoice = invoice;
		this.description = description;
		this.price = price;
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

	@Column(name = "description", length = 600)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", columnDefinition="double(16,2)")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}

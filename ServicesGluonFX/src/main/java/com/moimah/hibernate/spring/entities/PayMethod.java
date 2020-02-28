package com.moimah.hibernate.spring.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import net.bytebuddy.build.ToStringPlugin.Exclude;

/**
 * Clase tipo Entity de pay_method
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "pay_method", catalog = "7057507_administration_db")
public class PayMethod implements java.io.Serializable {
	
	@Expose
	private Integer id;
	@Expose
	private String description;
	
	private Set<Invoice> invoices = new HashSet<Invoice>(0);

	public PayMethod() {
	}

	public PayMethod(String description, Set<Invoice> invoices) {
		this.description = description;
		this.invoices = invoices;
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

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "payMethod")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}

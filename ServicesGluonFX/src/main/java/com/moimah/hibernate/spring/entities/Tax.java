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

/**
 * Clase tipo Entity de tax
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "tax", catalog = "7057507_administration_db")
public class Tax implements java.io.Serializable {
	
	@Expose
	private Integer id;
	@Expose
	private String taxId;
	@Expose
	private Double percentage;
	@Expose
	private String description;
	
	private Set<Budget> budgets = new HashSet<Budget>(0);	
	private Set<WorkOrder> workOrders = new HashSet<WorkOrder>(0);	
	private Set<Invoice> invoices = new HashSet<Invoice>(0);

	public Tax() {
	}

	public Tax(String taxId, Double percentage, String description, Set<Budget> budgets, Set<WorkOrder> workOrders,
			Set<Invoice> invoices) {
		this.taxId = taxId;
		this.percentage = percentage;
		this.description = description;
		this.budgets = budgets;
		this.workOrders = workOrders;
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

	@Column(name = "tax_id", length = 30)
	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@Column(name = "percentage", columnDefinition="double(16,2)")
	public Double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public Set<Budget> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(Set<Budget> budgets) {
		this.budgets = budgets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public Set<WorkOrder> getWorkOrders() {
		return this.workOrders;
	}

	public void setWorkOrders(Set<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}

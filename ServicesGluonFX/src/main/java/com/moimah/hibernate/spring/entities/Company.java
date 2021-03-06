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
 * Clase tipo Entity de company
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "company", catalog = "7057507_administration_db")
public class Company implements java.io.Serializable {
	
	@Expose
	private Integer id;
	@Expose
	private String companyId;
	@Expose
	private String name;
	@Expose
	private String address;
	@Expose
	private String city;
	@Expose
	private String country;
	@Expose
	private String email;
	@Expose
	private String phone;	
	
	private Set<Budget> budgets = new HashSet<Budget>(0);
	private Set<Invoice> invoices = new HashSet<Invoice>(0);	
	private Set<WorkOrder> workOrders = new HashSet<WorkOrder>(0);

	public Company() {
	}

	public Company(String companyId, String name, String address, String city, String country, String email,
			String phone, Set<Budget> budgets, Set<Invoice> invoices, Set<WorkOrder> workOrders) {
		this.companyId = companyId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.budgets = budgets;
		this.invoices = invoices;
		this.workOrders = workOrders;
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

	@Column(name = "company_id")
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "name", length = 500)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country")
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Budget> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(Set<Budget> budgets) {
		this.budgets = budgets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<WorkOrder> getWorkOrders() {
		return this.workOrders;
	}

	public void setWorkOrders(Set<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}

}

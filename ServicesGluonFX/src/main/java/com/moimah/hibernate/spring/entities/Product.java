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
 * Clase tipo Entity de product
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "product", catalog = "7057507_administration_db")
public class Product implements java.io.Serializable {
	
	@Expose
	private Integer id;
	@Expose
	private String productId;
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private Double price;
	@Expose
	private Integer stock;
	@Expose
	private String url;
	@Exclude
	private Set<WorkOrderDetail> workOrderDetails = new HashSet<WorkOrderDetail>(0);
	@Exclude
	private Set<BudgetDetail> budgetDetails = new HashSet<BudgetDetail>(0);
	@Exclude
	private Set<InvoiceDetail> invoiceDetails = new HashSet<InvoiceDetail>(0);

	public Product() {
	}

	public Product(String productId, String name, String description, Double price, Integer stock, String url,
			Set<WorkOrderDetail> workOrderDetails, Set<BudgetDetail> budgetDetails, Set<InvoiceDetail> invoiceDetails) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.url = url;
		this.workOrderDetails = workOrderDetails;
		this.budgetDetails = budgetDetails;
		this.invoiceDetails = invoiceDetails;
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

	@Column(name = "product_id", length = 30)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price",columnDefinition="double(16,2)")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "stock")
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<WorkOrderDetail> getWorkOrderDetails() {
		return this.workOrderDetails;
	}

	public void setWorkOrderDetails(Set<WorkOrderDetail> workOrderDetails) {
		this.workOrderDetails = workOrderDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<BudgetDetail> getBudgetDetails() {
		return this.budgetDetails;
	}

	public void setBudgetDetails(Set<BudgetDetail> budgetDetails) {
		this.budgetDetails = budgetDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public Set<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

}

package com.moimah.hibernate.spring.entities;


import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase tipo Entity de work_order
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "work_order", catalog = "7057507_administration_db")
public class WorkOrder implements java.io.Serializable {

	private Integer id;
	private Company company;
	private Customer customer;
	private Tax tax;
	private String workOrderNumber;
	private Date workOrderDate;
	private Integer status;
	private Integer conceptId;
	private Double price;
	private Double taxTotal;
	private Double priceTaxesIncluded;
	private Set<ConceptWorkOrder> conceptWorkOrders = new HashSet<ConceptWorkOrder>(0);
	private Set<WorkOrderDetail> workOrderDetails = new HashSet<WorkOrderDetail>(0);

	public WorkOrder() {
	}

	public WorkOrder(Company company, Customer customer, Tax tax, String workOrderNumber, Date workOrderDate,
			Integer status, Integer conceptId, Double price, Double taxTotal, Double priceTaxesIncluded,
			Set<ConceptWorkOrder> conceptWorkOrders, Set<WorkOrderDetail> workOrderDetails) {
		this.company = company;
		this.customer = customer;
		this.tax = tax;
		this.workOrderNumber = workOrderNumber;
		this.workOrderDate = workOrderDate;
		this.status = status;
		this.conceptId = conceptId;
		this.price = price;
		this.taxTotal = taxTotal;
		this.priceTaxesIncluded = priceTaxesIncluded;
		this.conceptWorkOrders = conceptWorkOrders;
		this.workOrderDetails = workOrderDetails;
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
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_id")
	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	@Column(name = "work_order_number")
	public String getWorkOrderNumber() {
		return this.workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "work_order_date", length = 10)
	public Date getWorkOrderDate() {
		return this.workOrderDate;
	}

	public void setWorkOrderDate(Date workOrderDate) {
		this.workOrderDate = workOrderDate;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "concept_id")
	public Integer getConceptId() {
		return this.conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}

	@Column(name = "price", columnDefinition="double(16,2)")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "tax_total", columnDefinition="double(16,2)")
	public Double getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	@Column(name = "price_taxes_included", columnDefinition="double(16,2)")
	public Double getPriceTaxesIncluded() {
		return this.priceTaxesIncluded;
	}

	public void setPriceTaxesIncluded(Double priceTaxesIncluded) {
		this.priceTaxesIncluded = priceTaxesIncluded;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workOrder")
	public Set<ConceptWorkOrder> getConceptWorkOrders() {
		return this.conceptWorkOrders;
	}

	public void setConceptWorkOrders(Set<ConceptWorkOrder> conceptWorkOrders) {
		this.conceptWorkOrders = conceptWorkOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workOrder")
	public Set<WorkOrderDetail> getWorkOrderDetails() {
		return this.workOrderDetails;
	}

	public void setWorkOrderDetails(Set<WorkOrderDetail> workOrderDetails) {
		this.workOrderDetails = workOrderDetails;
	}

}

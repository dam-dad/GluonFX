package com.moimah.hibernate.spring.entities;

import java.util.Date;
import java.util.HashSet;
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
 * Clase tipo Entity de prespuesto
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "budget", catalog = "7057507_administration_db")
public class Budget implements java.io.Serializable {

	private Integer id;
	private Company company;
	private Customer customer;
	private Tax tax;
	private String budgetNumber;
	private Date budgetDate;
	private Integer status;
	private Integer conceptId;
	private Double price;
	private Double taxTotal;
	private Double priceTaxesIncluded;
	private Set<ConceptBudget> conceptBudgets = new HashSet<ConceptBudget>(0);
	private Set<BudgetDetail> budgetDetails = new HashSet<BudgetDetail>(0);

	public Budget() {
	}

	public Budget(Company company, Customer customer, Tax tax, String budgetNumber, Date budgetDate, Integer status,
			Integer conceptId, Double price, Double taxTotal, Double priceTaxesIncluded,
			Set<ConceptBudget> conceptBudgets, Set<BudgetDetail> budgetDetails) {
		this.company = company;
		this.customer = customer;
		this.tax = tax;
		this.budgetNumber = budgetNumber;
		this.budgetDate = budgetDate;
		this.status = status;
		this.conceptId = conceptId;
		this.price = price;
		this.taxTotal = taxTotal;
		this.priceTaxesIncluded = priceTaxesIncluded;
		this.conceptBudgets = conceptBudgets;
		this.budgetDetails = budgetDetails;
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

	@Column(name = "budget_number")
	public String getBudgetNumber() {
		return this.budgetNumber;
	}

	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "budget_date", length = 10)
	public Date getBudgetDate() {
		return this.budgetDate;
	}

	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "budget")
	public Set<ConceptBudget> getConceptBudgets() {
		return this.conceptBudgets;
	}

	public void setConceptBudgets(Set<ConceptBudget> conceptBudgets) {
		this.conceptBudgets = conceptBudgets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "budget")
	public Set<BudgetDetail> getBudgetDetails() {
		return this.budgetDetails;
	}

	public void setBudgetDetails(Set<BudgetDetail> budgetDetails) {
		this.budgetDetails = budgetDetails;
	}

}

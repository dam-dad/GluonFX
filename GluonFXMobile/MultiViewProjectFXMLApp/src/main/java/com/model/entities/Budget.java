package com.model.entities;
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Budget generated by hbm2java
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
	private List<ConceptBudget> conceptBudgets = new ArrayList<ConceptBudget>(0);
	private List<BudgetDetail> budgetDetails = new ArrayList<BudgetDetail>(0);

	
	//JavaFX properties
	private IntegerProperty id_prop = new SimpleIntegerProperty(); 
	private ObjectProperty<Company> company_prop =  new SimpleObjectProperty<>(); 
	private ObjectProperty<Customer> customer_prop = new SimpleObjectProperty<>(); 
	private ObjectProperty<Tax> tax_prop = new SimpleObjectProperty<>(); 
	private StringProperty budgetNumber_prop = new SimpleStringProperty(); 
	private ObjectProperty<LocalDate> budgetDate_prop = new SimpleObjectProperty<>(); 
	private IntegerProperty status_prop = new SimpleIntegerProperty();
	private IntegerProperty conceptId_prop = new SimpleIntegerProperty(); 
	private DoubleProperty price_prop = new SimpleDoubleProperty(); 
	private DoubleProperty taxTotal_prop = new SimpleDoubleProperty(); 
	private DoubleProperty priceTaxesIncluded_prop = new SimpleDoubleProperty(); 
	private ListProperty<ConceptBudget> conceptBudgets_prop = new SimpleListProperty<>(); 
	private ListProperty<BudgetDetail> budgetDetails_prop = new SimpleListProperty<>(); 
	
	

	public Budget() {
	}


	
	
	public Budget(Integer id, Company company, Customer customer, Tax tax, String budgetNumber, Date budgetDate,
			Integer status, Integer conceptId, Double price, Double taxTotal, Double priceTaxesIncluded,
			List<ConceptBudget> conceptBudgets, List<BudgetDetail> budgetDetails, IntegerProperty id_prop,
			ObjectProperty<Company> company_prop, ObjectProperty<Customer> customer_prop, ObjectProperty<Tax> tax_prop,
			StringProperty budgetNumber_prop, ObjectProperty<LocalDate> budgetDate_prop, IntegerProperty status_prop,
			IntegerProperty conceptId_prop, DoubleProperty price_prop, DoubleProperty taxTotal_prop,
			DoubleProperty priceTaxesIncluded_prop, ListProperty<ConceptBudget> conceptBudgets_prop,
			ListProperty<BudgetDetail> budgetDetails_prop) {
		super();
		this.id = id;
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
		
		try {this.id_prop.set(id);} catch (Exception e) {}
		try {this.company_prop.set(company);} catch (Exception e) {}
		try {this.customer_prop.set(customer);} catch (Exception e) {}
		try {this.tax_prop.set(tax);} catch (Exception e) {}
		try {this.budgetNumber_prop.set(budgetNumber);} catch (Exception e) {}
		try {this.budgetDate_prop.set(localDateToDateConverter(budgetDate));} catch (Exception e) {}
		try {this.status_prop.set(status);} catch (Exception e) {}
		try {this.conceptId_prop.set(conceptId);} catch (Exception e) {}
		try {this.price_prop.set(price);} catch (Exception e) {}
		try {this.taxTotal_prop.set(taxTotal);} catch (Exception e) {}
		try {this.priceTaxesIncluded_prop.set(priceTaxesIncluded);} catch (Exception e) {}
		try {this.conceptBudgets_prop.set(FXCollections.observableArrayList(conceptBudgets));} catch (Exception e) {}
		try {this.budgetDetails_prop.set(FXCollections.observableArrayList(budgetDetails));} catch (Exception e) {}
			
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.id_prop.set(id);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
		this.company_prop.set(company);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.customer_prop.set(customer);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_id")
	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
		this.tax_prop.set(tax);
	}

	@Column(name = "budget_number")
	public String getBudgetNumber() {
		return this.budgetNumber;
	}

	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
		this.budgetNumber_prop.set(budgetNumber);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "budget_date", length = 10)
	public Date getBudgetDate() {
		return this.budgetDate;
	}

	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
		this.budgetDate_prop.set(localDateToDateConverter(budgetDate));
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
		this.status_prop.set(status);
	}

	@Column(name = "concept_id")
	public Integer getConceptId() {
		return this.conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
		this.conceptId_prop.set(conceptId);
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
		this.price_prop.set(price);
	}

	@Column(name = "tax_total", precision = 22, scale = 0)
	public Double getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
		this.taxTotal_prop.set(taxTotal);
	}

	@Column(name = "price_taxes_included", precision = 22, scale = 0)
	public Double getPriceTaxesIncluded() {
		return this.priceTaxesIncluded;
	}

	public void setPriceTaxesIncluded(Double priceTaxesIncluded) {
		this.priceTaxesIncluded = priceTaxesIncluded;
		this.priceTaxesIncluded_prop.set(priceTaxesIncluded);
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "budget")
	public List<ConceptBudget> getConceptBudgets() {
		return this.conceptBudgets;
	}

	public void setConceptBudgets(List<ConceptBudget> conceptBudgets) {
		this.conceptBudgets = conceptBudgets;
		this.conceptBudgets_prop.set(FXCollections.observableArrayList(conceptBudgets));
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "budget")
	public List<BudgetDetail> getBudgetDetails() {
		return this.budgetDetails;
	}

	public void setBudgetDetails(List<BudgetDetail> budgetDetails) {
		this.budgetDetails = budgetDetails;
		this.budgetDetails_prop.set(FXCollections.observableArrayList(budgetDetails));
	}

	// Converters
	public LocalDate localDateToDateConverter(Date date) {
		LocalDate dateA = new java.sql.Date(date.getTime()).toLocalDate();
		return dateA;
	}
	
	
	//JavaFX Properties
	
	public final IntegerProperty id_propProperty() {
		return this.id_prop;
	}
	

	public final ObjectProperty<Company> company_propProperty() {
		return this.company_prop;
	}
	

	public final ObjectProperty<Customer> customer_propProperty() {
		return this.customer_prop;
	}
	

	public final ObjectProperty<Tax> tax_propProperty() {
		return this.tax_prop;
	}
	

	public final StringProperty budgetNumber_propProperty() {
		return this.budgetNumber_prop;
	}
	

	
	public final ObjectProperty<LocalDate> budgetDate_propProperty() {
		return this.budgetDate_prop;
	}
	

	
	public final IntegerProperty status_propProperty() {
		return this.status_prop;
	}
	
	
	public final IntegerProperty conceptId_propProperty() {
		return this.conceptId_prop;
	}
	

	
	public final DoubleProperty price_propProperty() {
		return this.price_prop;
	}
	


	public final DoubleProperty taxTotal_propProperty() {
		return this.taxTotal_prop;
	}
	
	
	public final DoubleProperty priceTaxesIncluded_propProperty() {
		return this.priceTaxesIncluded_prop;
	}
	


	public final ListProperty<ConceptBudget> conceptBudgets_propProperty() {
		return this.conceptBudgets_prop;
	}
	

	public final ListProperty<BudgetDetail> budgetDetails_propProperty() {
		return this.budgetDetails_prop;
	}
	
	

}
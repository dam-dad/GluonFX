package com.moimah.hibernate.spring.entities;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.Expose;
import com.moimah.hibernate.spring.utils.CustomDateDeserialize;

import net.bytebuddy.build.ToStringPlugin.Exclude;

/**
 * Clase tipo Entity de invoice
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "invoice", catalog = "7057507_administration_db")
public class Invoice implements java.io.Serializable {
	
	@Expose
	private Integer id;		
	@Expose
	private Company company;	
	@Expose
	private Customer customer;	
	@Expose
	private PayMethod payMethod;	
	@Expose
	private Tax tax;	
	@Expose
	private String invoiceNumber;
	@Expose
	private Date invoiceDate;
	@Expose
	private Integer status;
	@Expose
	private Integer conceptId;
	@Expose
	private Double price;
	@Expose
	private Double taxTotal;
	@Expose
	private Double priceTaxesIncluded;	
	@Expose
	private Set<ConceptInvoice> conceptInvoices = new HashSet<ConceptInvoice>(0);	
	@Expose
	private Set<InvoiceDetail> invoiceDetails = new HashSet<InvoiceDetail>(0);

	public Invoice() {
	}

	public Invoice(Company company, Customer customer, PayMethod payMethod, Tax tax, String invoiceNumber,
			Date invoiceDate, Integer status, Integer conceptId, Double price, Double taxTotal,
			Double priceTaxesIncluded, Set<ConceptInvoice> conceptInvoices, Set<InvoiceDetail> invoiceDetails) {
		this.company = company;
		this.customer = customer;
		this.payMethod = payMethod;
		this.tax = tax;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.status = status;
		this.conceptId = conceptId;
		this.price = price;
		this.taxTotal = taxTotal;
		this.priceTaxesIncluded = priceTaxesIncluded;
		this.conceptInvoices = conceptInvoices;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_method_id")
	public PayMethod getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tax_id")
	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	@Column(name = "invoice_number")
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date", length = 10)
	@JsonDeserialize(using= CustomDateDeserialize.class)	
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	@Column(name = "price",columnDefinition="double(16,2) default 0")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "tax_total",columnDefinition="double(16,2) default 0")
	public Double getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	@Column(name = "price_taxes_included",columnDefinition="double(16,2) default 0")
	public Double getPriceTaxesIncluded() {
		return this.priceTaxesIncluded;
	}

	public void setPriceTaxesIncluded(Double priceTaxesIncluded) {
		this.priceTaxesIncluded = priceTaxesIncluded;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = CascadeType.ALL)
	public Set<ConceptInvoice> getConceptInvoices() {
		return this.conceptInvoices;
	}

	public void setConceptInvoices(Set<ConceptInvoice> conceptInvoices) {
		this.conceptInvoices = conceptInvoices;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
	public Set<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

}

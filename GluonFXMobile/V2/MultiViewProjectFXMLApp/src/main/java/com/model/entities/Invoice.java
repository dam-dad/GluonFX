package com.model.entities;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Invoice {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("company")
	@Expose
	private Company company;
	@SerializedName("customer")
	@Expose
	private Customer customer;
	@SerializedName("payMethod")
	@Expose
	private PayMethod payMethod;
	@SerializedName("tax")
	@Expose
	private Tax tax;
	@SerializedName("invoiceNumber")
	@Expose
	private String invoiceNumber;
	@SerializedName("invoiceDate")
	@Expose
	private String invoiceDate;
	@SerializedName("status")
	@Expose
	private Integer status;
	@SerializedName("conceptId")
	@Expose
	private Integer conceptId;
	@SerializedName("price")
	@Expose
	private Double price;
	@SerializedName("taxTotal")
	@Expose
	private Double taxTotal;
	@SerializedName("priceTaxesIncluded")
	@Expose
	private Double priceTaxesIncluded;
	@SerializedName("conceptInvoices")
	@Expose
	private List<ConceptInvoice> conceptInvoices = null;
	@SerializedName("invoiceDetails")
	@Expose
	private List<InvoiceDetail> invoiceDetails = null;
	
	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public Company getCompany() {
	return company;
	}

	public void setCompany(Company company) {
	this.company = company;
	}

	public Customer getCustomer() {
	return customer;
	}

	public void setCustomer(Customer customer) {
	this.customer = customer;
	}

	public PayMethod getPayMethod() {
	return payMethod;
	}

	public void setPayMethod(PayMethod payMethod) {
	this.payMethod = payMethod;
	}

	public Tax getTax() {
	return tax;
	}

	public void setTax(Tax tax) {
	this.tax = tax;
	}

	public String getInvoiceNumber() {
	return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceDate() {
	return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
	this.invoiceDate = invoiceDate;
	}

	public Integer getStatus() {
	return status;
	}

	public void setStatus(Integer status) {
	this.status = status;
	}

	public Integer getConceptId() {
	return conceptId;
	}

	public void setConceptId(Integer conceptId) {
	this.conceptId = conceptId;
	}

	public Double getPrice() {
	return price;
	}

	public void setPrice(Double price) {
	this.price = price;
	}

	public Double getTaxTotal() {
	return taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
	this.taxTotal = taxTotal;
	}

	public Double getPriceTaxesIncluded() {
	return priceTaxesIncluded;
	}

	public void setPriceTaxesIncluded(Double priceTaxesIncluded) {
	this.priceTaxesIncluded = priceTaxesIncluded;
	}

	public List<ConceptInvoice> getConceptInvoices() {
	return conceptInvoices;
	}

	public void setConceptInvoices(List<ConceptInvoice> conceptInvoices) {
	this.conceptInvoices = conceptInvoices;
	}

	public List<InvoiceDetail> getInvoiceDetails() {
	return invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
	this.invoiceDetails = invoiceDetails;
	}

	}
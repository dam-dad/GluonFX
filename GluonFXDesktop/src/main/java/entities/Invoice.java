package entities;
// Generated 19 ene. 2020 16:41:53 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Table(name = "invoice", catalog = "7057507_administration_db")
public class Invoice implements java.io.Serializable {

	private int invoiceId;
	private String companyId;
	private String customerId;
	private Date invoiceDate;
	private Integer conceptId;
	private Integer payMethodId;
	private Double price;
	private String taxId;
	private Double taxTotal;
	private Double priceTaxesIncluded;

	public Invoice() {
	}

	public Invoice(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Invoice(int invoiceId, String companyId, String customerId, Date invoiceDate, Integer conceptId,
			Integer payMethodId, Double price, String taxId, Double taxTotal, Double priceTaxesIncluded) {
		this.invoiceId = invoiceId;
		this.companyId = companyId;
		this.customerId = customerId;
		this.invoiceDate = invoiceDate;
		this.conceptId = conceptId;
		this.payMethodId = payMethodId;
		this.price = price;
		this.taxId = taxId;
		this.taxTotal = taxTotal;
		this.priceTaxesIncluded = priceTaxesIncluded;
	}

	@Id

	@Column(name = "invoice_id", unique = true, nullable = false)
	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Column(name = "company_id")
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "customer_id")
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date", length = 10)
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Column(name = "concept_id")
	public Integer getConceptId() {
		return this.conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}

	@Column(name = "pay_method_id")
	public Integer getPayMethodId() {
		return this.payMethodId;
	}

	public void setPayMethodId(Integer payMethodId) {
		this.payMethodId = payMethodId;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "tax_id", length = 30)
	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	@Column(name = "tax_total", precision = 22, scale = 0)
	public Double getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	@Column(name = "price_taxes_included", precision = 22, scale = 0)
	public Double getPriceTaxesIncluded() {
		return this.priceTaxesIncluded;
	}

	public void setPriceTaxesIncluded(Double priceTaxesIncluded) {
		this.priceTaxesIncluded = priceTaxesIncluded;
	}

}

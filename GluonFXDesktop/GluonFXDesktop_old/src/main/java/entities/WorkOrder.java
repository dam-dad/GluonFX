package entities;
// Generated 25 ene. 2020 22:19:24 by Hibernate Tools 5.2.12.Final

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

/**
 * WorkOrder generated by hbm2java
 */
@Entity
@Table(name = "work_order", catalog = "7057507_administration_db")
public class WorkOrder implements java.io.Serializable {

	private Integer id;
	private Company company;
	private Concept concept;
	private Customer customer;
	private Tax tax;
	private String workOrderNumber;
	private Date workOrderDate;
	private Integer status;
	private Double price;
	private Double taxTotal;
	private Double priceTaxesIncluded;
	private List<WorkOrderDetail> workOrderDetails = new ArrayList<WorkOrderDetail>(0);

	public WorkOrder() {
	}

	public WorkOrder(Company company, Concept concept, Customer customer, Tax tax, String workOrderNumber,
			Date workOrderDate, Integer status, Double price, Double taxTotal, Double priceTaxesIncluded,
			List<WorkOrderDetail> workOrderDetails) {
		this.company = company;
		this.concept = concept;
		this.customer = customer;
		this.tax = tax;
		this.workOrderNumber = workOrderNumber;
		this.workOrderDate = workOrderDate;
		this.status = status;
		this.price = price;
		this.taxTotal = taxTotal;
		this.priceTaxesIncluded = priceTaxesIncluded;
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
	@JoinColumn(name = "concept_id")
	public Concept getConcept() {
		return this.concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
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

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workOrder")
	public List<WorkOrderDetail> getWorkOrderDetails() {
		return this.workOrderDetails;
	}

	public void setWorkOrderDetails(List<WorkOrderDetail> workOrderDetails) {
		this.workOrderDetails = workOrderDetails;
	}

}

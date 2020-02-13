package entities;
// Generated 25 ene. 2020 22:19:24 by Hibernate Tools 5.2.12.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tax generated by hbm2java
 */
@Entity
@Table(name = "tax", catalog = "7057507_administration_db")
public class Tax implements java.io.Serializable {

	private Integer id;
	private String taxId;
	private Double percentage;
	private String description;
	private List<Budget> budgets = new ArrayList<Budget>(0);
	private List<WorkOrder> workOrders = new ArrayList<WorkOrder>(0);
	private List<Invoice> invoices = new ArrayList<Invoice>(0);

	public Tax() {
	}

	public Tax(String taxId, Double percentage, String description, List<Budget> budgets, List<WorkOrder> workOrders,
			List<Invoice> invoices) {
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

	@Column(name = "percentage", precision = 22, scale = 0)
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
	public List<Budget> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public List<WorkOrder> getWorkOrders() {
		return this.workOrders;
	}

	public void setWorkOrders(List<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public List<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {			
		return String.valueOf(getPercentage());
	}
}
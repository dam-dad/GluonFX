package entities;
<<<<<<< HEAD
// Generated 24 ene. 2020 9:53:08 by Hibernate Tools 5.2.12.Final
=======
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Tax generated by hbm2java
 */
@Entity
@Table(name = "tax", catalog = "7057507_administration_db")
public class Tax implements java.io.Serializable {

	private String taxId;
	private Double percentage;
	private String description;
<<<<<<< HEAD
=======
	private List<Budget> budgets = new ArrayList<Budget>(0);
	private List<WorkOrder> workOrders = new ArrayList<WorkOrder>(0);
	private List<Invoice> invoices = new ArrayList<Invoice>(0);
	
	private IntegerProperty id_prop = new SimpleIntegerProperty();
	private StringProperty taxId_prop = new SimpleStringProperty(); 
	private DoubleProperty percentage_prop = new SimpleDoubleProperty(); 
	private StringProperty description_prop = new SimpleStringProperty(); 
	private ListProperty<Budget> budgets_prop = new SimpleListProperty<>();
	private ListProperty<WorkOrder> workOrders_prop = new SimpleListProperty<>();
	private ListProperty<Invoice> invoices_prop = new SimpleListProperty<>();
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

	public Tax() {
	}

<<<<<<< HEAD
	public Tax(String taxId) {
		this.taxId = taxId;
	}

	public Tax(String taxId, Double percentage, String description) {
		this.taxId = taxId;
		this.percentage = percentage;
		this.description = description;
	}

=======
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
	@Id

<<<<<<< HEAD
	@Column(name = "tax_id", unique = true, nullable = false, length = 30)
=======
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.id_prop.set(id);
	}

	@Column(name = "tax_id", length = 30)
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
		this.taxId_prop.set(taxId);
	}

	@Column(name = "percentage", precision = 22, scale = 0)
	public Double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
		this.percentage_prop.set(percentage);
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.description_prop.set(description);
	}

<<<<<<< HEAD
=======
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public List<Budget> getBudgets() {
		return this.budgets;
	}

	public void setBudgets(List<Budget> budgets) {
		this.budgets = budgets;
		this.budgets_prop.set(FXCollections.observableArrayList(budgets));
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public List<WorkOrder> getWorkOrders() {
		return this.workOrders;
	}

	public void setWorkOrders(List<WorkOrder> workOrders) {
		this.workOrders = workOrders;
		this.workOrders_prop.set(FXCollections.observableArrayList(workOrders));
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tax")
	public List<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
		this.invoices_prop.set(FXCollections.observableArrayList(invoices));
	}
	
	
	//JavaFX properties

	public final IntegerProperty id_propProperty() {
		return this.id_prop;
	}
	

	public final StringProperty taxId_propProperty() {
		return this.taxId_prop;
	}
		
	public final DoubleProperty percentage_propProperty() {
		return this.percentage_prop;
	}
	
	public final StringProperty description_propProperty() {
		return this.description_prop;
	}
	

	public final ListProperty<Budget> budgets_propProperty() {
		return this.budgets_prop;
	}
	
	
	public final ListProperty<WorkOrder> workOrders_propProperty() {
		return this.workOrders_prop;
	}
	
	public final ListProperty<Invoice> invoices_propProperty() {
		return this.invoices_prop;
	}
	
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
}

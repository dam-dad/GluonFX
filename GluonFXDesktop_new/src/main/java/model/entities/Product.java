package model.entities;
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final

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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "7057507_administration_db")
public class Product implements java.io.Serializable {

	private Integer id;
	private String productId;
	private String name;
	private String description;
	private Double price;
	private Integer stock;
	private String url;
	private List<WorkOrderDetail> workOrderDetails = new ArrayList<WorkOrderDetail>(0);
	private List<BudgetDetail> budgetDetails = new ArrayList<BudgetDetail>(0);
	private List<InvoiceDetail> invoiceDetails = new ArrayList<InvoiceDetail>(0);
	
	private IntegerProperty id_prop = new SimpleIntegerProperty();
	private StringProperty productId_prop = new SimpleStringProperty(); 
	private StringProperty name_prop = new SimpleStringProperty();
	private StringProperty description_prop = new SimpleStringProperty();
	private DoubleProperty price_prop = new SimpleDoubleProperty();
	private IntegerProperty stock_pro = new SimpleIntegerProperty();
	private StringProperty url_prop  = new SimpleStringProperty();
	private ListProperty<WorkOrderDetail> workOrderDetails_prop = new SimpleListProperty<>(); 
	private ListProperty<BudgetDetail> budgetDetails_prop = new SimpleListProperty<>();
	private ListProperty<InvoiceDetail> invoiceDetails_prop = new SimpleListProperty<>(); 
	
	
	public Product() {
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

	@Column(name = "product_id", length = 30)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
		this.productId_prop.set(productId);
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		this.name_prop.set(name);
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.description_prop.set(description);
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
		this.price_prop.set(price);
	}

	@Column(name = "stock")
	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
		this.price_prop.set(stock);
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
		this.url_prop.set(url);
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public 	List<WorkOrderDetail> getWorkOrderDetails() {
		return this.workOrderDetails;
	}

	public void setWorkOrderDetails(List<WorkOrderDetail> workOrderDetails) {
		this.workOrderDetails = workOrderDetails;
		this.workOrderDetails_prop.set(FXCollections.observableArrayList(workOrderDetails));
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<BudgetDetail> getBudgetDetails() {
		return this.budgetDetails;
	}

	public void setBudgetDetails(List<BudgetDetail> budgetDetails) {
		this.budgetDetails = budgetDetails;
		this.budgetDetails_prop.set(FXCollections.observableArrayList(budgetDetails));
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	public List<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
		this.invoiceDetails_prop.set(FXCollections.observableArrayList(invoiceDetails));
	}


	//JavaFX properties
	
	public final IntegerProperty id_propProperty() {
		return this.id_prop;
	}
	
	public final StringProperty productId_propProperty() {
		return this.productId_prop;
	}

	public final StringProperty name_propProperty() {
		return this.name_prop;
	}
	

	public final StringProperty description_propProperty() {
		return this.description_prop;
	}
	
	
	public final DoubleProperty price_propProperty() {
		return this.price_prop;
	}
	

	public final IntegerProperty stock_proProperty() {
		return this.stock_pro;
	}
	

	public final StringProperty url_propProperty() {
		return this.url_prop;
	}
	
	
	public final ListProperty<WorkOrderDetail> workOrderDetails_propProperty() {
		return this.workOrderDetails_prop;
	}
	

	public final ListProperty<BudgetDetail> budgetDetails_propProperty() {
		return this.budgetDetails_prop;
	}
	

	public final ListProperty<InvoiceDetail> invoiceDetails_propProperty() {
		return this.invoiceDetails_prop;
	}
	
}
	
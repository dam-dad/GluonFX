package model.entities;
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * WorkOrderDetail generated by hbm2java
 */
@Entity
@Table(name = "work_order_detail", catalog = "7057507_administration_db", uniqueConstraints = @UniqueConstraint(columnNames = "product_id"))
public class WorkOrderDetail implements java.io.Serializable {

	private Integer id;
	private Product product;
	private WorkOrder workOrder;
	private Double quantity;
	private Double price;
	private Double priceUnit;
	
	private IntegerProperty id_prop = new SimpleIntegerProperty(); 
	private ObjectProperty<Product> product_prop = new SimpleObjectProperty<>(); 
	private ObjectProperty<WorkOrder> workOrder_prop = new SimpleObjectProperty<>();
	private DoubleProperty quantity_prop = new SimpleDoubleProperty();
	private DoubleProperty price_prop = new SimpleDoubleProperty(); 
	private DoubleProperty priceUnit_prop = new SimpleDoubleProperty();

	public WorkOrderDetail() {
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
	@JoinColumn(name = "product_id", unique = true)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.product_prop.set(product);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "work_order_id")
	public WorkOrder getWorkOrder() {
		return this.workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
		this.workOrder_prop.set(workOrder);
	}

	@Column(name = "quantity", precision = 22, scale = 0)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
		this.quantity_prop.set(quantity);
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
		this.price_prop.set(price);
	}

	@Column(name = "price_unit", precision = 22, scale = 0)
	public Double getPriceUnit() {
		return this.priceUnit;
	}

	public void setPriceUnit(Double priceUnit) {
		this.priceUnit = priceUnit;
		this.price_prop.set(priceUnit);
	}


	public final IntegerProperty id_propProperty() {
		return this.id_prop;
	}
	

	public final ObjectProperty<Product> product_propProperty() {
		return this.product_prop;
	}
	

	public final ObjectProperty<WorkOrder> workOrder_propProperty() {
		return this.workOrder_prop;
	}
	
	
	public final DoubleProperty quantity_propProperty() {
		return this.quantity_prop;
	}
	

	public final DoubleProperty price_propProperty() {
		return this.price_prop;
	}
	


	public final DoubleProperty priceUnit_propProperty() {
		return this.priceUnit_prop;
	}
	
	

}

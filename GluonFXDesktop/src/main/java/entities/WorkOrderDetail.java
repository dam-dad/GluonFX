package entities;
<<<<<<< HEAD
// Generated 24 ene. 2020 9:53:08 by Hibernate Tools 5.2.12.Final
=======
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

	private WorkOrderDetailId id;
	private Integer workOrderId;
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

<<<<<<< HEAD
	public WorkOrderDetail(WorkOrderDetailId id) {
		this.id = id;
	}

	public WorkOrderDetail(WorkOrderDetailId id, Integer workOrderId, Double quantity, Double price, Double priceUnit) {
		this.id = id;
		this.workOrderId = workOrderId;
		this.quantity = quantity;
		this.price = price;
		this.priceUnit = priceUnit;
	}

	@EmbeddedId
=======
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

	@AttributeOverrides({
			@AttributeOverride(name = "orderDetailId", column = @Column(name = "order_detail_id", nullable = false)),
			@AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false, length = 30)) })
	public WorkOrderDetailId getId() {
		return this.id;
	}

	public void setId(WorkOrderDetailId id) {
		this.id = id;
		this.id_prop.set(id);
	}

<<<<<<< HEAD
	@Column(name = "work_order_id")
	public Integer getWorkOrderId() {
		return this.workOrderId;
	}

	public void setWorkOrderId(Integer workOrderId) {
		this.workOrderId = workOrderId;
=======
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
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
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

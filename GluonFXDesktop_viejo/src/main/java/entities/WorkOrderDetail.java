package entities;
<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/WorkOrderDetail.java
// Generated 25 ene. 2020 22:19:24 by Hibernate Tools 5.2.12.Final
=======
<<<<<<< HEAD
// Generated 24 ene. 2020 9:53:08 by Hibernate Tools 5.2.12.Final
=======
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/WorkOrderDetail.java

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WorkOrderDetail generated by hbm2java
 */
@Entity
@Table(name = "work_order_detail", catalog = "7057507_administration_db")
public class WorkOrderDetail implements java.io.Serializable {

	private WorkOrderDetailId id;
	private Integer workOrderId;
	private Double quantity;
	private Double price;
	private Double priceUnit;

	public WorkOrderDetail() {
	}

<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/WorkOrderDetail.java
	public WorkOrderDetail(Product product, WorkOrder workOrder, Double quantity, Double price, Double priceUnit) {
		this.product = product;
		this.workOrder = workOrder;
=======
<<<<<<< HEAD
	public WorkOrderDetail(WorkOrderDetailId id) {
		this.id = id;
	}

	public WorkOrderDetail(WorkOrderDetailId id, Integer workOrderId, Double quantity, Double price, Double priceUnit) {
		this.id = id;
		this.workOrderId = workOrderId;
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/WorkOrderDetail.java
		this.quantity = quantity;
		this.price = price;
		this.priceUnit = priceUnit;
	}

<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/WorkOrderDetail.java
=======
	@EmbeddedId
=======
	
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/WorkOrderDetail.java
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
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "work_order_id")
	public WorkOrder getWorkOrder() {
		return this.workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/WorkOrderDetail.java
=======
		this.workOrder_prop.set(workOrder);
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/WorkOrderDetail.java
	}

	@Column(name = "quantity", precision = 22, scale = 0)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "price_unit", precision = 22, scale = 0)
	public Double getPriceUnit() {
		return this.priceUnit;
	}

	public void setPriceUnit(Double priceUnit) {
		this.priceUnit = priceUnit;
	}

}

package fx.beans;

import entities.WorkOrderDetail;
import entities.WorkOrderDetailId;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WorkOrderDetailBean {
	
	WorkOrderDetail workOrderDetail;
	
	private ObjectProperty<WorkOrderDetailId> id = new SimpleObjectProperty<WorkOrderDetailId>(); 
	private IntegerProperty workOrderId = new SimpleIntegerProperty();
	private DoubleProperty quantity = new SimpleDoubleProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private DoubleProperty priceUnit = new SimpleDoubleProperty();
	
	
	public WorkOrderDetailBean(WorkOrderDetail w) {
		this.workOrderDetail = w;
		id.set(workOrderDetail.getId());
		workOrderId.set(workOrderDetail.getWorkOrderId());
		quantity.set(workOrderDetail.getQuantity());
		price.set(workOrderDetail.getPrice());
		priceUnit.set(workOrderDetail.getPriceUnit());
	}
	
	public WorkOrderDetail getWorkOrderDetail() {
		return workOrderDetail;
	}

	public final ObjectProperty<WorkOrderDetailId> idProperty() {
		return this.id;
	}
	

	public final WorkOrderDetailId getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final WorkOrderDetailId id) {
		this.idProperty().set(id);
	}
	

	public final IntegerProperty workOrderIdProperty() {
		return this.workOrderId;
	}
	

	public final int getWorkOrderId() {
		return this.workOrderIdProperty().get();
	}
	

	public final void setWorkOrderId(final int workOrderId) {
		this.workOrderIdProperty().set(workOrderId);
	}
	

	public final DoubleProperty quantityProperty() {
		return this.quantity;
	}
	

	public final double getQuantity() {
		return this.quantityProperty().get();
	}
	

	public final void setQuantity(final double quantity) {
		this.quantityProperty().set(quantity);
	}
	

	public final DoubleProperty priceProperty() {
		return this.price;
	}
	

	public final double getPrice() {
		return this.priceProperty().get();
	}
	

	public final void setPrice(final double price) {
		this.priceProperty().set(price);
	}
	

	public final DoubleProperty priceUnitProperty() {
		return this.priceUnit;
	}
	

	public final double getPriceUnit() {
		return this.priceUnitProperty().get();
	}
	

	public final void setPriceUnit(final double priceUnit) {
		this.priceUnitProperty().set(priceUnit);
	}
	
	
	

}

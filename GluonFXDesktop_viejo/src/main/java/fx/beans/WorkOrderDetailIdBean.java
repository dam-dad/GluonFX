package fx.beans;


import entities.WorkOrderDetailId;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkOrderDetailIdBean {
	
	WorkOrderDetailId wdi; 
	
	private IntegerProperty workOrderDetailId = new SimpleIntegerProperty();
	private StringProperty productId = new SimpleStringProperty(); 
	
	
	public WorkOrderDetailIdBean(WorkOrderDetailId w) {
		this.wdi = w; 
		workOrderDetailId.set(wdi.getWorkOrderDetailId());
		productId.set(wdi.getProductId());
	}
	
	public WorkOrderDetailId getWdi() {
		return wdi;
	}

	public final IntegerProperty workOrderDetailIdProperty() {
		return this.workOrderDetailId;
	}
	

	public final int getWorkOrderDetailId() {
		return this.workOrderDetailIdProperty().get();
	}
	

	public final void setWorkOrderDetailId(final int workOrderDetailId) {
		this.workOrderDetailIdProperty().set(workOrderDetailId);
	}
	

	public final StringProperty productIdProperty() {
		return this.productId;
	}
	

	public final String getProductId() {
		return this.productIdProperty().get();
	}
	

	public final void setProductId(final String productId) {
		this.productIdProperty().set(productId);
	}
	
	
	
	

}

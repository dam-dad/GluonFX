package fx.beans;

import java.util.Date;

import entities.WorkOrder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkOrderBean {
	
	private WorkOrder workOrder; 	
	
	private IntegerProperty workOrderId = new SimpleIntegerProperty();
	private StringProperty workOrderNumber = new SimpleStringProperty();
	private StringProperty companyId = new SimpleStringProperty();
	private StringProperty customerId = new SimpleStringProperty();
	private ObjectProperty<Date> workOrderDate = new SimpleObjectProperty<Date>();
	private IntegerProperty status = new SimpleIntegerProperty();
	private IntegerProperty conceptId = new SimpleIntegerProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private StringProperty taxId = new SimpleStringProperty();
	private DoubleProperty taxTotal = new SimpleDoubleProperty();
	private DoubleProperty priceTaxesIncluded = new SimpleDoubleProperty();
	
	public WorkOrderBean(WorkOrder w) {
		this.workOrder = w;		
		workOrderId.set(workOrder.getWorkOrderId());
		workOrderNumber.set(workOrder.getWorkOrderNumber());
		companyId.set(workOrder.getCompanyId());
		customerId.set(workOrder.getCustomerId());
		workOrderDate.set(workOrder.getWorkOrderDate());
		status.set(workOrder.getStatus());
		conceptId.set(workOrder.getConceptId());
		price.set(workOrder.getPrice());
		taxId.set(workOrder.getTaxId());
		taxTotal.set(workOrder.getTaxTotal());
		priceTaxesIncluded.set(workOrder.getPriceTaxesIncluded());	
	}
	
	public WorkOrder getWorkOrder() {
		return workOrder;
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
	

	public final StringProperty workOrderNumberProperty() {
		return this.workOrderNumber;
	}
	

	public final String getWorkOrderNumber() {
		return this.workOrderNumberProperty().get();
	}
	

	public final void setWorkOrderNumber(final String workOrderNumber) {
		this.workOrderNumberProperty().set(workOrderNumber);
	}
	

	public final StringProperty companyIdProperty() {
		return this.companyId;
	}
	

	public final String getCompanyId() {
		return this.companyIdProperty().get();
	}
	

	public final void setCompanyId(final String companyId) {
		this.companyIdProperty().set(companyId);
	}
	

	public final StringProperty customerIdProperty() {
		return this.customerId;
	}
	

	public final String getCustomerId() {
		return this.customerIdProperty().get();
	}
	

	public final void setCustomerId(final String customerId) {
		this.customerIdProperty().set(customerId);
	}
	

	public final ObjectProperty<Date> workOrderDateProperty() {
		return this.workOrderDate;
	}
	

	public final Date getWorkOrderDate() {
		return this.workOrderDateProperty().get();
	}
	

	public final void setWorkOrderDate(final Date workOrderDate) {
		this.workOrderDateProperty().set(workOrderDate);
	}
	

	public final IntegerProperty statusProperty() {
		return this.status;
	}
	

	public final int getStatus() {
		return this.statusProperty().get();
	}
	

	public final void setStatus(final int status) {
		this.statusProperty().set(status);
	}
	

	public final IntegerProperty conceptIdProperty() {
		return this.conceptId;
	}
	

	public final int getConceptId() {
		return this.conceptIdProperty().get();
	}
	

	public final void setConceptId(final int conceptId) {
		this.conceptIdProperty().set(conceptId);
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
	

	public final StringProperty taxIdProperty() {
		return this.taxId;
	}
	

	public final String getTaxId() {
		return this.taxIdProperty().get();
	}
	

	public final void setTaxId(final String taxId) {
		this.taxIdProperty().set(taxId);
	}
	

	public final DoubleProperty taxTotalProperty() {
		return this.taxTotal;
	}
	

	public final double getTaxTotal() {
		return this.taxTotalProperty().get();
	}
	

	public final void setTaxTotal(final double taxTotal) {
		this.taxTotalProperty().set(taxTotal);
	}
	

	public final DoubleProperty priceTaxesIncludedProperty() {
		return this.priceTaxesIncluded;
	}
	

	public final double getPriceTaxesIncluded() {
		return this.priceTaxesIncludedProperty().get();
	}
	

	public final void setPriceTaxesIncluded(final double priceTaxesIncluded) {
		this.priceTaxesIncludedProperty().set(priceTaxesIncluded);
	}
	
	
	

}

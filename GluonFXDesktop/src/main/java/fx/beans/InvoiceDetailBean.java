package fx.beans;

import entities.InvoiceDetail;
import entities.InvoiceDetailId;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class InvoiceDetailBean {
	
	private InvoiceDetail invoiceDetail;
	
	private ObjectProperty<InvoiceDetailId> id = new SimpleObjectProperty<InvoiceDetailId>(); 	
	private IntegerProperty invoiceId = new SimpleIntegerProperty(); 
	private DoubleProperty quantity = new SimpleDoubleProperty(); 
	private DoubleProperty price = new SimpleDoubleProperty(); 
	private DoubleProperty priceUnit = new SimpleDoubleProperty(); 
	
	public InvoiceDetailBean(InvoiceDetail i) {	
		this.invoiceDetail = i;
		id.set(invoiceDetail.getId());
		invoiceId.set(invoiceDetail.getInvoiceId());
		quantity.set(invoiceDetail.getQuantity());
		price.set(invoiceDetail.getPrice());
		priceUnit.set(invoiceDetail.getPriceUnit());
	}
	
	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}

	public final ObjectProperty<InvoiceDetailId> idProperty() {
		return this.id;
	}
	

	public final InvoiceDetailId getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final InvoiceDetailId id) {
		this.idProperty().set(id);
	}
	

	public final IntegerProperty invoiceIdProperty() {
		return this.invoiceId;
	}
	

	public final int getInvoiceId() {
		return this.invoiceIdProperty().get();
	}
	

	public final void setInvoiceId(final int invoiceId) {
		this.invoiceIdProperty().set(invoiceId);
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

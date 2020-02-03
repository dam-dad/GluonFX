package fx.beans;


import entities.InvoiceDetailId;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InvoiceDetailIdBean {
	
	private InvoiceDetailId idi;
	
	private IntegerProperty invoiceDetailId = new SimpleIntegerProperty(); 
	private StringProperty productId = new SimpleStringProperty(); 
	
	
	public InvoiceDetailIdBean(InvoiceDetailId i) {
		this.idi = i; 
		invoiceDetailId.set(idi.getInvoiceDetailId());
		productId.set(idi.getProductId());
	}
	
	public InvoiceDetailId getIdi() {
		return idi;
	}

	public final IntegerProperty invoiceDetailIdProperty() {
		return this.invoiceDetailId;
	}
	

	public final int getInvoiceDetailId() {
		return this.invoiceDetailIdProperty().get();
	}
	

	public final void setInvoiceDetailId(final int invoiceDetailId) {
		this.invoiceDetailIdProperty().set(invoiceDetailId);
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

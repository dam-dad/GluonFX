package fx.beans;

import entities.Invoice;
import entities.InvoiceDetail;
import entities.Product;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class InvoiceDetailBean {
	
	private InvoiceDetail invoiceDetail;
	
	
	private IntegerProperty id = new SimpleIntegerProperty(); 
	private ObjectProperty<Invoice> invoice = new SimpleObjectProperty<Invoice>(); 
	private ObjectProperty<Product> product = new SimpleObjectProperty<>();
	private DoubleProperty quantity = new SimpleDoubleProperty(); 
	private DoubleProperty price = new SimpleDoubleProperty(); 
	private DoubleProperty priceUnit = new SimpleDoubleProperty(); 
	
	public InvoiceDetailBean(InvoiceDetail i) {	
		this.invoiceDetail = i;
		
		id.set(invoiceDetail.getId());
	
		try {id.set(invoiceDetail.getId());} catch (Exception e) {}
		try {invoice.set(invoiceDetail.getInvoice());} catch (Exception e) {}
		try {product.set(invoiceDetail.getProduct());} catch (Exception e) {}
		try {quantity.set(invoiceDetail.getQuantity());} catch (Exception e) {}
		try {price.set(invoiceDetail.getPrice());} catch (Exception e) {}
		try {priceUnit.set(invoiceDetail.getPriceUnit());} catch (Exception e) {}
					
	}
	
	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

	public final ObjectProperty<Invoice> invoiceProperty() {
		return this.invoice;
	}
	

	public final Invoice getInvoice() {
		return this.invoiceProperty().get();
	}
	

	public final void setInvoice(final Invoice invoice) {
		this.invoiceProperty().set(invoice);
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

	public final ObjectProperty<Product> productProperty() {
		return this.product;
	}
	

	public final Product getProduct() {
		return this.productProperty().get();
	}
	

	public final void setProduct(final Product product) {
		this.productProperty().set(product);
	}
	
	
	
	


	
}

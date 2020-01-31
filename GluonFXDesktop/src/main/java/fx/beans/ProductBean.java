package fx.beans;

import java.util.ArrayList;

import entities.InvoiceDetail;
import entities.Product;

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

public class ProductBean {

	
	private Product product; 
	
	private IntegerProperty id = new SimpleIntegerProperty(); 
	private StringProperty productId = new SimpleStringProperty(); 
	private StringProperty name = new SimpleStringProperty();
	private StringProperty description = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty(); 
	private IntegerProperty stock = new SimpleIntegerProperty(); 
	private StringProperty url = new SimpleStringProperty();
	private ListProperty<InvoiceDetail> invoiceDetails = new SimpleListProperty<InvoiceDetail>();
	
	
	public ProductBean(Product p) {
		this.product = p;
		try {id.set(product.getId());}catch (Exception e) {}
		try {productId.set(product.getProductId());}catch (Exception e) {}
		try {name.set(product.getName());}catch (Exception e) {}
		try {description.set(product.getDescription());}catch (Exception e) {}
		try {price.set(product.getPrice());}catch (Exception e) {}
		try {stock.set(product.getStock());}catch (Exception e) {}
		try {url.set(product.getUrl());}catch (Exception e) {}
		try {invoiceDetails.set(FXCollections.observableArrayList(product.getInvoiceDetails()));}catch (Exception e) {}
			
		
	}
	
	public Product getProduct() {
		return product;
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
		this.product.setId(id);
	}
	

	public final StringProperty productIdProperty() {
		return this.productId;
	}
	

	public final String getProductId() {
		return this.productIdProperty().get();
	}
	

	public final void setProductId(final String productId) {
		this.productIdProperty().set(productId);
		this.product.setProductId(productId);
	}
	

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
		this.product.setName(name);
	}
	

	public final StringProperty descriptionProperty() {
		return this.description;
	}
	

	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	

	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
		this.product.setDescription(description);
	}
	

	public final DoubleProperty priceProperty() {
		return this.price;
	}
	

	public final double getPrice() {
		return this.priceProperty().get();
	}
	

	public final void setPrice(final double price) {
		this.priceProperty().set(price);
		this.product.setPrice(price);
	}
	

	public final IntegerProperty stockProperty() {
		return this.stock;
	}
	

	public final int getStock() {
		return this.stockProperty().get();
	}
	

	public final void setStock(final int stock) {
		this.stockProperty().set(stock);
		this.product.setStock(stock);
	}
	

	public final StringProperty urlProperty() {
		return this.url;
	}
	

	public final String getUrl() {
		return this.urlProperty().get();
	}
	

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
		this.product.setUrl(url);
	}
	

	public final ListProperty<InvoiceDetail> invoiceDetailsProperty() {
		return this.invoiceDetails;
	}
	

	public final ObservableList<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetailsProperty().get();
	}
	

	public final void setInvoiceDetails(final ObservableList<InvoiceDetail> invoiceDetails) {
		this.invoiceDetailsProperty().set(invoiceDetails);
		this.product.setInvoiceDetails(invoiceDetails);
	}
	

	@Override
	public String toString() {
		return getName();
	}


}

package fx.beans;

import entities.Product;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductBean {
	
	private Product product; 
	
	private StringProperty productId = new SimpleStringProperty(); 
	private StringProperty name = new SimpleStringProperty();
	private StringProperty description = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty(); 
	private IntegerProperty stock = new SimpleIntegerProperty(); 
	private StringProperty url = new SimpleStringProperty();
	
	
	public ProductBean(Product p) {
		this.product = p;
		productId.set(product.getProductId());
		name.set(product.getName());
		description.set(product.getDescription());
		price.set(product.getPrice());
		stock.set(product.getStock());
		url.set(product.getUrl());
	}
	
	public Product getProduct() {
		return product;
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
	

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	

	public final StringProperty descriptionProperty() {
		return this.description;
	}
	

	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	

	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
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
	

	public final IntegerProperty stockProperty() {
		return this.stock;
	}
	

	public final int getStock() {
		return this.stockProperty().get();
	}
	

	public final void setStock(final int stock) {
		this.stockProperty().set(stock);
	}
	

	public final StringProperty urlProperty() {
		return this.url;
	}
	

	public final String getUrl() {
		return this.urlProperty().get();
	}
	

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}
	
	
	

}

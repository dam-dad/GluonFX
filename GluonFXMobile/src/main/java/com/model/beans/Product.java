package com.model.beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlElement;


public class Product {
	
	private  IntegerProperty id = new SimpleIntegerProperty();

	private  StringProperty productId = new SimpleStringProperty();

	private  StringProperty name = new SimpleStringProperty();

	private  StringProperty description = new SimpleStringProperty();

	private  DoubleProperty price = new SimpleDoubleProperty();

	private  IntegerProperty stock = new SimpleIntegerProperty();

	private  StringProperty url = new SimpleStringProperty();	
	
	
	public Product() {
		
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
	
	@XmlElement(name = "stock")
	public final int getStock() {
		return this.stockProperty().get();
	}
	

	public final void setStock(final int stock) {
		this.stockProperty().set(stock);
	}
	

	public final StringProperty urlProperty() {
		return this.url;
	}
	
	@XmlElement(name = "url")
	public final String getUrl() {
		return this.urlProperty().get();
	}
	

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}
	
	
	@Override
	public String toString() {
		return getName();
	}
	
	
	
}

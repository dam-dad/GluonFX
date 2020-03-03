package com.model.beans;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class InvoiceDetail {
	
	
	private IntegerProperty id = new SimpleIntegerProperty();
	
	private ObjectProperty<Product> product = new SimpleObjectProperty<>(); 

	private DoubleProperty quantity = new SimpleDoubleProperty(); 

	private DoubleProperty price = new SimpleDoubleProperty(); 

	private DoubleProperty priceUnit = new SimpleDoubleProperty();

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
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

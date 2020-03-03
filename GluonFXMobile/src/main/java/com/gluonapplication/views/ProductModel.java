package com.gluonapplication.views;

import com.model.beans.Product;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * Clase de tipo modelo para product
 * @author moimah
 *
 */
public class ProductModel {
	
	private ListProperty<Product> listProducts = new SimpleListProperty<>();	
	private ObjectProperty<Product> productSelected = new SimpleObjectProperty<>();
	private StringProperty productId = new SimpleStringProperty(); 
	private StringProperty productName = new SimpleStringProperty(); 
	private StringProperty priceUnit = new SimpleStringProperty();
	private StringProperty stock = new SimpleStringProperty(); 
	private StringProperty description = new SimpleStringProperty();
	private ObjectProperty<Image> imgProduct = new SimpleObjectProperty<>();
	
	public final ListProperty<Product> listProductsProperty() {
		return this.listProducts;
	}
	
	public final ObservableList<Product> getListProducts() {
		return this.listProductsProperty().get();
	}
	
	public final void setListProducts(final ObservableList<Product> listProducts) {
		this.listProductsProperty().set(listProducts);
	}
	
	public final ObjectProperty<Product> productSelectedProperty() {
		return this.productSelected;
	}
	
	public final Product getProductSelected() {
		return this.productSelectedProperty().get();
	}
	
	public final void setProductSelected(final Product productSelected) {
		this.productSelectedProperty().set(productSelected);
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
	
	public final StringProperty productNameProperty() {
		return this.productName;
	}
	
	public final String getProductName() {
		return this.productNameProperty().get();
	}
	
	public final void setProductName(final String productName) {
		this.productNameProperty().set(productName);
	}
	
	public final StringProperty priceUnitProperty() {
		return this.priceUnit;
	}
	
	public final String getPriceUnit() {
		return this.priceUnitProperty().get();
	}
	
	public final void setPriceUnit(final String priceUnit) {
		this.priceUnitProperty().set(priceUnit);
	}
	
	public final StringProperty stockProperty() {
		return this.stock;
	}
	
	public final String getStock() {
		return this.stockProperty().get();
	}
	
	public final void setStock(final String stock) {
		this.stockProperty().set(stock);
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
	
	public final ObjectProperty<Image> imgProductProperty() {
		return this.imgProduct;
	}
	
	public final Image getImgProduct() {
		return this.imgProductProperty().get();
	}
	
	public final void setImgProduct(final Image imgProduct) {
		this.imgProductProperty().set(imgProduct);
	}
	
	
	
	
	
	

}

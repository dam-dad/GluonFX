package com.utils;

import com.model.beans.Product;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Wrapper de objetos product y productPNG
 * @author moimah
 *
 */
public class WrapperProductPNG {
	
	private ObjectProperty<Product> product = new SimpleObjectProperty<>();
	private ObjectProperty<ProductPNG> productPNG = new SimpleObjectProperty<>();
	
	
	public final ObjectProperty<Product> productProperty() {
		return this.product;
	}
	
	public final Product getProduct() {
		return this.productProperty().get();
	}
	
	public final void setProduct(final Product product) {
		this.productProperty().set(product);
	}
	
	public final ObjectProperty<ProductPNG> productPNGProperty() {
		return this.productPNG;
	}
	
	public final ProductPNG getProductPNG() {
		return this.productPNGProperty().get();
	}
	
	public final void setProductPNG(final ProductPNG productPNG) {
		this.productPNGProperty().set(productPNG);
	}
	
	
	
	

}

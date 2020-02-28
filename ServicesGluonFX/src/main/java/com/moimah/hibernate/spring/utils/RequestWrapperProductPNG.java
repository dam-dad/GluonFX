package com.moimah.hibernate.spring.utils;

import com.moimah.hibernate.spring.entities.Product;

/**
 * Clase de tipo Wrapper de product y productPNG
 * 
 * 
 * @author moimah
 *
 */
public class RequestWrapperProductPNG {
	
	private Product product;
	private ProductPNG productPNG;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductPNG getProductPNG() {
		return productPNG;
	}
	public void setProductPNG(ProductPNG productPNG) {
		this.productPNG = productPNG;
	}
	
	

}

package com.model.entities;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvoiceDetail implements Serializable {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("product")
	@Expose
	private Product product;
	@SerializedName("quantity")
	@Expose
	private Double quantity;
	@SerializedName("price")
	@Expose
	private Double price;
	@SerializedName("priceUnit")
	@Expose
	private Double priceUnit;
	

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public Product getProduct() {
	return product;
	}

	public void setProduct(Product product) {
	this.product = product;
	}

	public Double getQuantity() {
	return quantity;
	}

	public void setQuantity(Double quantity) {
	this.quantity = quantity;
	}

	public Double getPrice() {
	return price;
	}

	public void setPrice(Double price) {
	this.price = price;
	}

	public Double getPriceUnit() {
	return priceUnit;
	}

	public void setPriceUnit(Double priceUnit) {
	this.priceUnit = priceUnit;
	}

	}
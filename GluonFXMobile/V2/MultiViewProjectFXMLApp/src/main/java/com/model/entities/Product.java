package com.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("productId")
	@Expose
	private String productId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("price")
	@Expose
	private Double price;
	@SerializedName("stock")
	@Expose
	private Integer stock;
	@SerializedName("url")
	@Expose
	private String url;


	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public String getProductId() {
	return productId;
	}

	public void setProductId(String productId) {
	this.productId = productId;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public Double getPrice() {
	return price;
	}

	public void setPrice(Double price) {
	this.price = price;
	}

	public Integer getStock() {
	return stock;
	}

	public void setStock(Integer stock) {
	this.stock = stock;
	}

	public String getUrl() {
	return url;
	}

	public void setUrl(String url) {
	this.url = url;
	}

	}
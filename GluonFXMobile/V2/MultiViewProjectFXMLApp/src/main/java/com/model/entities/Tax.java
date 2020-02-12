package com.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tax {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("taxId")
	@Expose
	private String taxId;
	@SerializedName("percentage")
	@Expose
	private Double percentage;
	@SerializedName("description")
	@Expose
	private String description;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
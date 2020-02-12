package com.model.entities;

import com.google.gson.annotations.Expose;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tax {

	@Expose
	private IntegerProperty id = new SimpleIntegerProperty();
	@Expose
	private StringProperty taxId = new SimpleStringProperty();
	@Expose
	private DoubleProperty percentage = new SimpleDoubleProperty();
	@Expose
	private StringProperty description = new SimpleStringProperty();
	public final IntegerProperty idProperty() {
		return this.id;
	}
	
	public final int getId() {
		return this.idProperty().get();
	}
	
	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	public final StringProperty taxIdProperty() {
		return this.taxId;
	}
	
	public final String getTaxId() {
		return this.taxIdProperty().get();
	}
	
	public final void setTaxId(final String taxId) {
		this.taxIdProperty().set(taxId);
	}
	
	public final DoubleProperty percentageProperty() {
		return this.percentage;
	}
	
	public final double getPercentage() {
		return this.percentageProperty().get();
	}
	
	public final void setPercentage(final double percentage) {
		this.percentageProperty().set(percentage);
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
	
	
	
	
}

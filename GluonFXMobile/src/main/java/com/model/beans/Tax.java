package com.model.beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tax {

	
	private IntegerProperty id = new SimpleIntegerProperty();
		
	private StringProperty taxId = new SimpleStringProperty();

	private DoubleProperty percentage = new SimpleDoubleProperty();

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
	
	@Override
	public String toString() {
		return getTaxId();
	}
}

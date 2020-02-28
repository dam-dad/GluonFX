package com.gluonapplication.views;

import com.model.beans.Tax;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * Clase de tipo modelo para tax
 * @author moimah
 *
 */
public class TaxModel {

	private ListProperty<Tax> listTaxes = new SimpleListProperty<>();
	private ObjectProperty<Tax> taxSelected = new SimpleObjectProperty<>();
	private StringProperty taxId = new SimpleStringProperty();
	private StringProperty percentage = new SimpleStringProperty();
	private StringProperty description = new SimpleStringProperty();
	
	
	public final ListProperty<Tax> listTaxesProperty() {
		return this.listTaxes;
	}
	
	public final ObservableList<Tax> getListTaxes() {
		return this.listTaxesProperty().get();
	}
	
	public final void setListTaxes(final ObservableList<Tax> listTaxes) {
		this.listTaxesProperty().set(listTaxes);
	}
	
	public final ObjectProperty<Tax> taxSelectedProperty() {
		return this.taxSelected;
	}
	
	public final Tax getTaxSelected() {
		return this.taxSelectedProperty().get();
	}
	
	public final void setTaxSelected(final Tax taxSelected) {
		this.taxSelectedProperty().set(taxSelected);
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
	
	public final StringProperty percentageProperty() {
		return this.percentage;
	}
	
	public final String getPercentage() {
		return this.percentageProperty().get();
	}
	
	public final void setPercentage(final String percentage) {
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

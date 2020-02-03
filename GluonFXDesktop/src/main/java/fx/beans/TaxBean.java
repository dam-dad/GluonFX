package fx.beans;

import entities.Tax;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaxBean {
	
	private Tax tax; 
	
	private StringProperty taxId = new SimpleStringProperty(); 
	private DoubleProperty percentage = new SimpleDoubleProperty(); 
	private StringProperty description = new SimpleStringProperty(); 
	
	public TaxBean(Tax t) {
		this.tax = t; 
		taxId.set(tax.getTaxId());
		percentage.set(tax.getPercentage());
		description.set(tax.getDescription());
	}
	
	public Tax getTax() {
		return tax;
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

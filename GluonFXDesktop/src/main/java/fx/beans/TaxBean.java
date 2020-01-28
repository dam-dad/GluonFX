package fx.beans;


import entities.Invoice;
import entities.Tax;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaxBean {
	
	
	private Tax tax; 
	private IntegerProperty id = new SimpleIntegerProperty(); 
	private StringProperty taxId = new SimpleStringProperty(); 
	private DoubleProperty percentage = new SimpleDoubleProperty(); 
	private StringProperty description = new SimpleStringProperty(); 
	private ListProperty<Invoice> invoices = new SimpleListProperty<Invoice>(); 
	
	public TaxBean(Tax t) {
		this.tax = t; 
		id.set(tax.getId());
		taxId.set(tax.getTaxId());
		percentage.set(tax.getPercentage());
		description.set(tax.getDescription());
		
		invoices.set(FXCollections.observableArrayList(tax.getInvoices()));		
		
		
	}
	
	public Tax getTax() {
		return tax;
	}

	

	@Override
	public String toString() {		
		return getTaxId(); 
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
		this.tax.setId(id);
	}
	

	public final StringProperty taxIdProperty() {
		return this.taxId;
	}
	

	public final String getTaxId() {
		return this.taxIdProperty().get();
	}
	

	public final void setTaxId(final String taxId) {
		this.taxIdProperty().set(taxId);
		this.tax.setTaxId(taxId);
	}
	

	public final DoubleProperty percentageProperty() {
		return this.percentage;
	}
	

	public final double getPercentage() {
		return this.percentageProperty().get();
	}
	

	public final void setPercentage(final double percentage) {
		this.percentageProperty().set(percentage);
		this.tax.setPercentage(percentage);
	}
	

	public final StringProperty descriptionProperty() {
		return this.description;
	}
	

	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	

	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
		this.tax.setDescription(description);
	}
	

	public final ListProperty<Invoice> invoicesProperty() {
		return this.invoices;
	}
	

	public final ObservableList<Invoice> getInvoices() {
		return this.invoicesProperty().get();
	}
	

	public final void setInvoices(final ObservableList<Invoice> invoices) {
		this.invoicesProperty().set(invoices);
		this.tax.setInvoices(invoices);
	}
	
	

}

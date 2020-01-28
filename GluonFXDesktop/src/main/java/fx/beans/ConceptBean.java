package fx.beans;


import entities.Company;
import entities.Concept;
import entities.Invoice;
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

public class ConceptBean {

	
	private Concept concept;
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty description = new SimpleStringProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private ListProperty<Invoice> invoices = new SimpleListProperty<Invoice>();
	
	public ConceptBean(Concept c) {
		this.concept = c; 
		id.set(concept.getId());
		description.set(concept.getDescription());
		price.set(concept.getPrice());
				
		invoices.set(FXCollections.observableArrayList(concept.getInvoices()));
		
		
	
	}
	
	
	public Concept getConcept() {
		return concept;
	}


	public final IntegerProperty idProperty() {
		return this.id;
	}
	


	public final int getId() {
		return this.idProperty().get();
	}
	


	public final void setId(final int id) {
		this.idProperty().set(id);
		concept.setId(id);
	}
	


	public final StringProperty descriptionProperty() {
		return this.description;		
	}
	


	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	


	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
		concept.setDescription(description);
	}
	


	public final DoubleProperty priceProperty() {
		return this.price;
	}
	


	public final double getPrice() {
		return this.priceProperty().get();
	}
	


	public final void setPrice(final double price) {
		this.priceProperty().set(price);
		this.concept.setPrice(price);
	}
	


	public final ListProperty<Invoice> invoicesProperty() {
		return this.invoices;
	}
	


	public final ObservableList<Invoice> getInvoices() {
		return this.invoicesProperty().get();
	}
	


	public final void setInvoices(final ObservableList<Invoice> invoices) {
		this.invoicesProperty().set(invoices);
		this.concept.setInvoices(invoices);
	}
	
	
	
}

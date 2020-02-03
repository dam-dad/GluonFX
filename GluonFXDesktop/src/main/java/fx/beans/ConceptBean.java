package fx.beans;

import entities.Concept;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ConceptBean {
	
	private Concept concept;
	
	private IntegerProperty conceptId = new SimpleIntegerProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	
	public ConceptBean(Concept c) {
		this.concept = c; 
		conceptId.set(concept.getConceptId());
		price.set(concept.getPrice());
	}
	
	
	public Concept getConcept() {
		return concept;
	}


	public final IntegerProperty conceptIdProperty() {
		return this.conceptId;
	}
	


	public final int getConceptId() {
		return this.conceptIdProperty().get();
	}
	


	public final void setConceptId(final int conceptId) {
		this.conceptIdProperty().set(conceptId);
	}
	


	public final DoubleProperty priceProperty() {
		return this.price;
	}
	


	public final double getPrice() {
		return this.priceProperty().get();
	}
	


	public final void setPrice(final double price) {
		this.priceProperty().set(price);
	}
	
	
	
	

}

package fx.beans;

<<<<<<< HEAD:GluonFXDesktop/src/main/java/fx/beans/ConceptBean.java
import entities.Concept;
=======

import entities.ConceptInvoice;
import entities.Invoice;

>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003:GluonFXDesktop/src/main/java/fx/beans/ConceptInvoiceBean.java
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

<<<<<<< HEAD:GluonFXDesktop/src/main/java/fx/beans/ConceptBean.java
public class ConceptBean {
=======
public class ConceptInvoiceBean {

>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003:GluonFXDesktop/src/main/java/fx/beans/ConceptInvoiceBean.java
	
	private ConceptInvoice concept;

	
	private IntegerProperty conceptId = new SimpleIntegerProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	
	
	public ConceptInvoiceBean(ConceptInvoice c) {
				
		this.concept = c; 
		conceptId.set(concept.getConceptId());
		price.set(concept.getPrice());
<<<<<<< HEAD:GluonFXDesktop/src/main/java/fx/beans/ConceptBean.java
=======
			
	
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003:GluonFXDesktop/src/main/java/fx/beans/ConceptInvoiceBean.java
	}
	
	
	public ConceptInvoice getConcept() {
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
	
<<<<<<< HEAD:GluonFXDesktop/src/main/java/fx/beans/ConceptBean.java
	
	
=======


		
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003:GluonFXDesktop/src/main/java/fx/beans/ConceptInvoiceBean.java
	

}

package fx.beans;

import entities.PayMethod;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PayMethodBean {
	
	private PayMethod payMethod; 
	
	private IntegerProperty payMethodId = new SimpleIntegerProperty(); 
	private StringProperty description = new SimpleStringProperty(); 
	
	public PayMethodBean(PayMethod p) {
		this.payMethod = p;
		payMethodId.set(payMethod.getPayMethodId());
		description.set(payMethod.getDescription());		
	}
	
	public PayMethod getPayMethod() {
		return payMethod;
	}

	public final IntegerProperty payMethodIdProperty() {
		return this.payMethodId;
	}
	

	public final int getPayMethodId() {
		return this.payMethodIdProperty().get();
	}
	

	public final void setPayMethodId(final int payMethodId) {
		this.payMethodIdProperty().set(payMethodId);
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

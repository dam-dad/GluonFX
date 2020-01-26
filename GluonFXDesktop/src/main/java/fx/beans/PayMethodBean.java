package fx.beans;


import entities.Invoice;
import entities.PayMethod;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PayMethodBean {
	
	private PayMethod payMethod; 
	

	private IntegerProperty id = new SimpleIntegerProperty(); 
	private StringProperty description = new SimpleStringProperty(); 
	private ListProperty<Invoice> invoices = new SimpleListProperty<Invoice>();
	
	public PayMethodBean(PayMethod p) {
		this.payMethod = p;
		id.set(payMethod.getId());
		description.set(payMethod.getDescription());
				
		invoices.set(FXCollections.observableArrayList(payMethod.getInvoices()));
		
	}
	
	public PayMethod getPayMethod() {
		return payMethod;
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
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
	

	public final ListProperty<Invoice> invoicesProperty() {
		return this.invoices;
	}
	

	public final ObservableList<Invoice> getInvoices() {
		return this.invoicesProperty().get();
	}
	

	public final void setInvoices(final ObservableList<Invoice> invoices) {
		this.invoicesProperty().set(invoices);
	}
	

}

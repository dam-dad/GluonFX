package com.gluonapplication.views;

import com.model.entities.Invoice;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class PrimaryViewModel {

	private ListProperty<Invoice> listInvoices = new SimpleListProperty<>(); 
    private ObjectProperty<Invoice> invoice = new SimpleObjectProperty<>();
    
//    @FXML
//  private StringProperty customerName = new SimpleStringProperty(); 
//  private StringProperty Address = new SimpleStringProperty(); 
//  private StringProperty customerName = new SimpleStringProperty(); 
//  private StringProperty customerName = new SimpleStringProperty(); 
//  private StringProperty customerName = new SimpleStringProperty(); 
//  private StringProperty customerName = new SimpleStringProperty(); 
//
//  @FXML
//  private TextField txtNIF;
//
//  @FXML
//  private TextField txtAddress;
//
//  @FXML
//  private TextField txtPhone;
//
//  @FXML
//  private Button btnSearchCustomer;
//
//  @FXML
//  private TextArea txtConcept;

    
  //JavFX getters & setters

  	public final ListProperty<Invoice> listInvoicesProperty() {
  		return this.listInvoices;
  	}
  	

  	public final ObservableList<Invoice> getListInvoices() {
  		return this.listInvoicesProperty().get();
  	}
  	

  	public final void setListInvoices(final ObservableList<Invoice> listInvoices) {
  		this.listInvoicesProperty().set(listInvoices);
  	}
  	

  	public final ObjectProperty<Invoice> invoiceProperty() {
  		return this.invoice;
  	}
  	

  	public final Invoice getInvoice() {
  		return this.invoiceProperty().get();
  	}
  	

  	public final void setInvoice(final Invoice invoice) {
  		this.invoiceProperty().set(invoice);
  	}
}

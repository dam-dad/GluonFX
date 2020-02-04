package model.beans;

import model.entities.Customer;
import model.entities.Invoice;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerBean {


	private Customer customer; 
	
	private IntegerProperty id = new SimpleIntegerProperty(); 
	private StringProperty customerId = new SimpleStringProperty(); 
	private StringProperty name = new SimpleStringProperty(); 
	private StringProperty address = new SimpleStringProperty(); 
	private StringProperty city = new SimpleStringProperty(); 
	private StringProperty country = new SimpleStringProperty(); 
	private StringProperty email = new SimpleStringProperty(); 
	private StringProperty phone = new SimpleStringProperty(); 
	private ListProperty<Invoice> invoices = new SimpleListProperty<Invoice>(); 
	
	public CustomerBean(Customer c) {		
		this.customer = c; 
		
		try {id.set(customer.getId());}catch (Exception e) {}
		try {customerId.set(customer.getCustomerId());}catch (Exception e) {}
		try {name.set(customer.getName());}catch (Exception e) {}
		try {address.set(customer.getAddress());}catch (Exception e) {}
		try {city.set(customer.getCity());}catch (Exception e) {}
		try {country.set(customer.getCountry());}catch (Exception e) {}
		try {email.set(customer.getEmail());}catch (Exception e) {}
		try {phone.set(customer.getPhone());	}catch (Exception e) {}			
		try {invoices.set(FXCollections.observableArrayList(customer.getInvoices()));}catch (Exception e) {}
				

	}
	
	public Customer getCustomer() {
		return customer;
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
		this.customer.setId(id);
	}
	

	public final StringProperty customerIdProperty() {
		return this.customerId;
	}
	

	public final String getCustomerId() {
		return this.customerIdProperty().get();
	}
	

	public final void setCustomerId(final String customerId) {
		this.customerIdProperty().set(customerId);
		this.customer.setCustomerId(customerId);
	}
	

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
		this.customer.setName(name);
	}
	

	public final StringProperty addressProperty() {
		return this.address;
	}
	

	public final String getAddress() {
		return this.addressProperty().get();
	}
	

	public final void setAddress(final String address) {
		this.addressProperty().set(address);
		this.customer.setAddress(address);
	}
	

	public final StringProperty cityProperty() {
		return this.city;
	}
	

	public final String getCity() {
		return this.cityProperty().get();
	}
	

	public final void setCity(final String city) {
		this.cityProperty().set(city);
		this.customer.setCity(city);
	}
	

	public final StringProperty countryProperty() {
		return this.country;
	}
	

	public final String getCountry() {
		return this.countryProperty().get();
	}
	

	public final void setCountry(final String country) {
		this.countryProperty().set(country);
		this.customer.setCountry(country);	}
	

	public final StringProperty emailProperty() {
		return this.email;
	}
	

	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
		this.customer.setEmail(email);
	}
	

	public final StringProperty phoneProperty() {
		return this.phone;
	}
	

	public final String getPhone() {
		return this.phoneProperty().get();
	}
	

	public final void setPhone(final String phone) {
		this.phoneProperty().set(phone);
		this.customer.setPhone(phone);
	}
	

	public final ListProperty<Invoice> invoicesProperty() {
		return this.invoices;
	}
	

	public final ObservableList<Invoice> getInvoices() {
		return this.invoicesProperty().get();
	}
	

	public final void setInvoices(final ObservableList<Invoice> invoices) {
		this.invoicesProperty().set(invoices);
		this.customer.setInvoices(invoices);
	}
	

	@Override
	public String toString() {		
		return getName();
	}

	

}

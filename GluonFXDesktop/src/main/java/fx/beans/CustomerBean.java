package fx.beans;

import entities.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerBean {

	private Customer customer; 
	
	private StringProperty customerId = new SimpleStringProperty(); 
	private StringProperty name = new SimpleStringProperty(); 
	private StringProperty address = new SimpleStringProperty(); 
	private StringProperty city = new SimpleStringProperty(); 
	private StringProperty country = new SimpleStringProperty(); 
	private StringProperty email = new SimpleStringProperty(); 
	private StringProperty phone = new SimpleStringProperty(); 
	
	public CustomerBean(Customer c) {		
		this.customer = c; 
		customerId.set(customer.getCustomerId());
		name.set(customer.getName());
		address.set(customer.getAddress());
		city.set(customer.getCity());
		country.set(customer.getCountry());
		email.set(customer.getEmail());
		phone.set(customer.getPhone());	
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public final StringProperty customerIdProperty() {
		return this.customerId;
	}
	

	public final String getCustomerId() {
		return this.customerIdProperty().get();
	}
	

	public final void setCustomerId(final String customerId) {
		this.customerIdProperty().set(customerId);
	}
	

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	

	public final StringProperty addressProperty() {
		return this.address;
	}
	

	public final String getAddress() {
		return this.addressProperty().get();
	}
	

	public final void setAddress(final String address) {
		this.addressProperty().set(address);
	}
	

	public final StringProperty cityProperty() {
		return this.city;
	}
	

	public final String getCity() {
		return this.cityProperty().get();
	}
	

	public final void setCity(final String city) {
		this.cityProperty().set(city);
	}
	

	public final StringProperty countryProperty() {
		return this.country;
	}
	

	public final String getCountry() {
		return this.countryProperty().get();
	}
	

	public final void setCountry(final String country) {
		this.countryProperty().set(country);
	}
	

	public final StringProperty emailProperty() {
		return this.email;
	}
	

	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	

	public final StringProperty phoneProperty() {
		return this.phone;
	}
	

	public final String getPhone() {
		return this.phoneProperty().get();
	}
	

	public final void setPhone(final String phone) {
		this.phoneProperty().set(phone);
	}
	
	

}

package com.model.beans;



import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Company {

	private IntegerProperty id = new SimpleIntegerProperty();

	private StringProperty companyId = new SimpleStringProperty();
	
	private StringProperty name = new SimpleStringProperty();
	
	private StringProperty address = new SimpleStringProperty();
	
	private StringProperty city = new SimpleStringProperty();
	
	private StringProperty country = new SimpleStringProperty();
	
	private StringProperty email = new SimpleStringProperty();
	
	private StringProperty phone = new SimpleStringProperty();
	
	
	public final IntegerProperty idProperty() {
		return this.id;
	}
	


	public final int getId() {
		return this.idProperty().get();
	}
	


	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	


	public final StringProperty companyIdProperty() {
		return this.companyId;
	}
	


	public final String getCompanyId() {
		return this.companyIdProperty().get();
	}
	


	public final void setCompanyId(final String companyId) {
		this.companyIdProperty().set(companyId);
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
	
	@Override
	public String toString() {
		return getName();
	}
	
}

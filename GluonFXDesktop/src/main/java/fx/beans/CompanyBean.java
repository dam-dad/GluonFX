package fx.beans;

import entities.Company;
<<<<<<< HEAD
=======
import entities.Invoice;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompanyBean {
	
	
	
	private Company company;
	
	private StringProperty companyId = new SimpleStringProperty(); 
	private StringProperty name = new SimpleStringProperty(); 
	private StringProperty address = new SimpleStringProperty(); 
	private StringProperty city = new SimpleStringProperty(); 
	private StringProperty country = new SimpleStringProperty(); 
	private StringProperty email = new SimpleStringProperty(); 
	private StringProperty phone = new SimpleStringProperty(); 
	
	
	public CompanyBean(Company c) {
		this.company = c; 
		companyId.set(company.getCompanyId());
		name.set(company.getName());
		address.set(company.getAddress());
		city.set(company.getCity());
		country.set(company.getCountry());
		email.set(company.getEmail());
		phone.set(company.getPhone());		
	}
	
	
	public Company getCompany() {
		return company;
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
	
	

}

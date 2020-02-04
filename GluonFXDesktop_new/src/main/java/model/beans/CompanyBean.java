package model.beans;

import java.util.List;

import model.entities.Company;
import model.entities.Invoice;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompanyBean {
	
	
	private Company company;
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty companyId = new SimpleStringProperty(); 
	private StringProperty name = new SimpleStringProperty(); 
	private StringProperty address = new SimpleStringProperty(); 
	private StringProperty city = new SimpleStringProperty(); 
	private StringProperty country = new SimpleStringProperty(); 
	private StringProperty email = new SimpleStringProperty(); 
	private StringProperty phone = new SimpleStringProperty(); 
	private ListProperty<Invoice> invoices = new SimpleListProperty<Invoice>(); 
	
	
	public CompanyBean(Company c) {
			
		this.company = c; 
		id.set(company.getId());
		companyId.set(company.getCompanyId());
		name.set(company.getName());
		System.out.println(name);
		address.set(company.getAddress());
		city.set(company.getCity());
		country.set(company.getCountry());
		email.set(company.getEmail());
		phone.set(company.getPhone());		
		
		invoices.set( FXCollections.observableArrayList(company.getInvoices()));
	}
	
	
	public Company getCompany() {
		return company;
	}


	

	public final IntegerProperty idProperty() {
		return this.id;
	}
	


	public final int getId() {
		return this.idProperty().get();
	}
	


	public final void setId(final int id) {
		this.idProperty().set(id);
		this.company.setId(id);
	}
	


	public final StringProperty companyIdProperty() {
		return this.companyId;
	}
	


	public final String getCompanyId() {
		return this.companyIdProperty().get();
	}
	


	public final void setCompanyId(final String companyId) {
		this.companyIdProperty().set(companyId);
		this.company.setCompanyId(companyId);
	}
	


	public final StringProperty nameProperty() {
		return this.name;		
	}
	


	public final String getName() {
		return this.nameProperty().get();
	}
	


	public final void setName(final String name) {
		this.nameProperty().set(name);
		this.company.setName(name);
	}
	


	public final StringProperty addressProperty() {
		return this.address;
	}
	


	public final String getAddress() {
		return this.addressProperty().get();
	}
	


	public final void setAddress(final String address) {
		this.addressProperty().set(address);
		this.company.setAddress(address);
	}
	


	public final StringProperty cityProperty() {
		return this.city;
	}
	


	public final String getCity() {
		return this.cityProperty().get();
	}
	


	public final void setCity(final String city) {
		this.cityProperty().set(city);
		this.company.setCity(city);
	}
	


	public final StringProperty countryProperty() {
		return this.country;
	}
	


	public final String getCountry() {
		return this.countryProperty().get();
	}
	


	public final void setCountry(final String country) {
		this.countryProperty().set(country);
		this.company.setCountry(country);
	}
	


	public final StringProperty emailProperty() {
		return this.email;
	}
	


	public final String getEmail() {
		return this.emailProperty().get();
	}
	


	public final void setEmail(final String email) {
		this.emailProperty().set(email);
		this.company.setEmail(email);
	}
	


	public final StringProperty phoneProperty() {
		return this.phone;
	}
	


	public final String getPhone() {
		return this.phoneProperty().get();
	}
	


	public final void setPhone(final String phone) {
		this.phoneProperty().set(phone);
		this.company.setPhone(phone);
	}
	


	public final ListProperty<Invoice> invoicesProperty() {
		return this.invoices;
	}
	


	public final ObservableList<Invoice> getInvoices() {
		return this.invoicesProperty().get();
	}
	


	public final void setInvoices(final ObservableList<Invoice> invoices) {
		this.invoicesProperty().set(invoices);
		company.setInvoices(invoices);
	}
	


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
	

}

package fx.beans;

import java.util.Date;

import entities.Budget;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BudgetBean {

	
	private Budget budget;
	
	private IntegerProperty budgetId = new SimpleIntegerProperty();
	private StringProperty budgetNumber = new SimpleStringProperty();
	private StringProperty companyId = new SimpleStringProperty();
	private StringProperty customerId = new SimpleStringProperty();
	private ObjectProperty<Date> budgetDate = new SimpleObjectProperty<Date>();
	private IntegerProperty status = new SimpleIntegerProperty();
	private IntegerProperty conceptId = new SimpleIntegerProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private StringProperty taxId = new SimpleStringProperty();
	private DoubleProperty taxTotal = new SimpleDoubleProperty();
	private DoubleProperty priceTaxesIncluded = new SimpleDoubleProperty();
	
	
	public BudgetBean(Budget b) {
		this.budget = b;
		budgetId.set(budget.getBudgetId());
		budgetNumber.set(budget.getBudgetNumber());
		companyId.set(budget.getCompanyId());
		customerId.set(budget.getCustomerId());
		budgetDate.set(budget.getBudgetDate());
		status.set(budget.getStatus());
		conceptId.set(budget.getConceptId());
		price.set(budget.getPrice());
		taxId.set(budget.getTaxId());
		taxTotal.set(budget.getTaxTotal());
		priceTaxesIncluded.set(budget.getPriceTaxesIncluded());		
	}
	
	public Budget getBudget() {
		return budget;
	}

	public final IntegerProperty budgetIdProperty() {
		return this.budgetId;
	}
	

	public final int getBudgetId() {
		return this.budgetIdProperty().get();
	}
	

	public final void setBudgetId(final int budgetId) {
		this.budgetIdProperty().set(budgetId);
	}
	

	public final StringProperty budgetNumberProperty() {
		return this.budgetNumber;
	}
	

	public final String getBudgetNumber() {
		return this.budgetNumberProperty().get();
	}
	

	public final void setBudgetNumber(final String budgetNumber) {
		this.budgetNumberProperty().set(budgetNumber);
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
	

	public final StringProperty customerIdProperty() {
		return this.customerId;
	}
	

	public final String getCustomerId() {
		return this.customerIdProperty().get();
	}
	

	public final void setCustomerId(final String customerId) {
		this.customerIdProperty().set(customerId);
	}
	

	public final ObjectProperty<Date> budgetDateProperty() {
		return this.budgetDate;
	}
	

	public final Date getBudgetDate() {
		return this.budgetDateProperty().get();
	}
	

	public final void setBudgetDate(final Date budgetDate) {
		this.budgetDateProperty().set(budgetDate);
	}
	

	public final IntegerProperty statusProperty() {
		return this.status;
	}
	

	public final int getStatus() {
		return this.statusProperty().get();
	}
	

	public final void setStatus(final int status) {
		this.statusProperty().set(status);
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
	

	public final StringProperty taxIdProperty() {
		return this.taxId;
	}
	

	public final String getTaxId() {
		return this.taxIdProperty().get();
	}
	

	public final void setTaxId(final String taxId) {
		this.taxIdProperty().set(taxId);
	}
	

	public final DoubleProperty taxTotalProperty() {
		return this.taxTotal;
	}
	

	public final double getTaxTotal() {
		return this.taxTotalProperty().get();
	}
	

	public final void setTaxTotal(final double taxTotal) {
		this.taxTotalProperty().set(taxTotal);
	}
	

	public final DoubleProperty priceTaxesIncludedProperty() {
		return this.priceTaxesIncluded;
	}
	

	public final double getPriceTaxesIncluded() {
		return this.priceTaxesIncludedProperty().get();
	}
	

	public final void setPriceTaxesIncluded(final double priceTaxesIncluded) {
		this.priceTaxesIncludedProperty().set(priceTaxesIncluded);
	}
	
	
	
	
	
	

}

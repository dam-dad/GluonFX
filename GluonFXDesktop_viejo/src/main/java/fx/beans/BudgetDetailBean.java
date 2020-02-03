package fx.beans;

import entities.BudgetDetail;
import entities.BudgetDetailId;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BudgetDetailBean {
	
	
	BudgetDetail budgetDetail;
	
	private ObjectProperty<BudgetDetailId> id = new SimpleObjectProperty<BudgetDetailId>(); 
	private IntegerProperty budgetId = new SimpleIntegerProperty();
	private DoubleProperty quantity = new SimpleDoubleProperty();
	private DoubleProperty price = new SimpleDoubleProperty();
	private DoubleProperty priceUnit = new SimpleDoubleProperty();
	
	public BudgetDetailBean(BudgetDetail b) {
		this.budgetDetail = b;
		id.set(budgetDetail.getId());
		budgetId.set(budgetDetail.getBudgetId());
		quantity.set(budgetDetail.getQuantity());
		price.set(budgetDetail.getPrice());
		priceUnit.set(budgetDetail.getPriceUnit());
	}
	
	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}

	public final ObjectProperty<BudgetDetailId> idProperty() {
		return this.id;
	}
	

	public final BudgetDetailId getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final BudgetDetailId id) {
		this.idProperty().set(id);
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
	

	public final DoubleProperty quantityProperty() {
		return this.quantity;
	}
	

	public final double getQuantity() {
		return this.quantityProperty().get();
	}
	

	public final void setQuantity(final double quantity) {
		this.quantityProperty().set(quantity);
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
	

	public final DoubleProperty priceUnitProperty() {
		return this.priceUnit;
	}
	

	public final double getPriceUnit() {
		return this.priceUnitProperty().get();
	}
	

	public final void setPriceUnit(final double priceUnit) {
		this.priceUnitProperty().set(priceUnit);
	}
	
	
	


}

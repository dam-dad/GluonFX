package fx.beans;



import entities.BudgetDetailId;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BudgetDetailIdBean {
	
	private BudgetDetailId bdi;
	
	private IntegerProperty budgetDetailId = new SimpleIntegerProperty(); 
	private StringProperty productId = new SimpleStringProperty(); 
	
	
	public BudgetDetailIdBean(BudgetDetailId b) {
		this.bdi = b;
		budgetDetailId.set(bdi.getBudgetDetailId());
		productId.set(bdi.getProductId());
	}
	
	
	public BudgetDetailId getBdi() {
		return bdi;
	}
	


	public final IntegerProperty budgetDetailIdProperty() {
		return this.budgetDetailId;
	}
	


	public final int getBudgetDetailId() {
		return this.budgetDetailIdProperty().get();
	}
	


	public final void setBudgetDetailId(final int budgetDetailId) {
		this.budgetDetailIdProperty().set(budgetDetailId);
	}
	


	public final StringProperty productIdProperty() {
		return this.productId;
	}
	


	public final String getProductId() {
		return this.productIdProperty().get();
	}
	


	public final void setProductId(final String productId) {
		this.productIdProperty().set(productId);
	}
	
	
	

	
	
	
	

}

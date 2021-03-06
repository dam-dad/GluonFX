package model.entities;
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ConceptBudget generated by hbm2java
 */
@Entity
@Table(name = "concept_budget", catalog = "7057507_administration_db", uniqueConstraints = @UniqueConstraint(columnNames = "budget_id"))
public class ConceptBudget implements java.io.Serializable {

	private Integer id;
	private Budget budget;
	private String description;
	private Double price;
	
	private IntegerProperty id_prop = new SimpleIntegerProperty(); 
	private ObjectProperty<Budget> budget_prop = new SimpleObjectProperty<>();
	private StringProperty description_prop = new SimpleStringProperty();
	private DoubleProperty price_prop = new SimpleDoubleProperty(); 

	public ConceptBudget() {
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_id", unique = true)
	public Budget getBudget() {
		return this.budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
		this.budget_prop.set(budget);
	}

	@Column(name = "description", length = 600)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.description_prop.set(description);
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
		this.price_prop.set(price);
	}


	//JavaFX Properties

	public final IntegerProperty id_propProperty() {
		return this.id_prop;
	}
	


	public final ObjectProperty<Budget> budget_propProperty() {
		return this.budget_prop;
	}
	

	public final StringProperty description_propProperty() {
		return this.description_prop;
	}
	

	public final DoubleProperty price_propProperty() {
		return this.price_prop;
	}
	

	

}

package com.moimah.hibernate.spring.entities;

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

/**
 * Clase tipo Entity de concept_budget
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "concept_budget", catalog = "7057507_administration_db", uniqueConstraints = @UniqueConstraint(columnNames = "budget_id"))
public class ConceptBudget implements java.io.Serializable {

	private Integer id;
	private Budget budget;
	private String description;
	private Double price;

	public ConceptBudget() {
	}

	public ConceptBudget(Budget budget, String description, Double price) {
		this.budget = budget;
		this.description = description;
		this.price = price;
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
	}

	@Column(name = "description", length = 600)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", columnDefinition="double(16,2)")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}

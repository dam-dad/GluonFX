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
 * Clase tipo Entity de budget_detail
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "budget_detail", catalog = "7057507_administration_db", uniqueConstraints = @UniqueConstraint(columnNames = "product_id"))
public class BudgetDetail implements java.io.Serializable {

	private Integer id;
	private Budget budget;
	private Product product;
	private Double quantity;
	private Double price;
	private Double priceUnit;

	public BudgetDetail() {
	}

	public BudgetDetail(Budget budget, Product product, Double quantity, Double price, Double priceUnit) {
		this.budget = budget;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.priceUnit = priceUnit;
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
	@JoinColumn(name = "budget_id")
	public Budget getBudget() {
		return this.budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", unique = true)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "quantity", columnDefinition="double(16,2)")
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price", columnDefinition="double(16,2)")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "price_unit", columnDefinition="double(16,2)")
	public Double getPriceUnit() {
		return this.priceUnit;
	}

	public void setPriceUnit(Double priceUnit) {
		this.priceUnit = priceUnit;
	}

}

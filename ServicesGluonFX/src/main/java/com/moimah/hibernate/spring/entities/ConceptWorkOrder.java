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

/**
 * Clase tipo Entity de concept_work_order
 * 
 * 
 * @author moimah
 *
 */
@Entity
@Table(name = "concept_work_order", catalog = "7057507_administration_db")
public class ConceptWorkOrder implements java.io.Serializable {

	private Integer id;
	private WorkOrder workOrder;
	private String description;
	private Double price;

	public ConceptWorkOrder() {
	}

	public ConceptWorkOrder(WorkOrder workOrder, String description, Double price) {
		this.workOrder = workOrder;
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
	@JoinColumn(name = "work_order_id")
	public WorkOrder getWorkOrder() {
		return this.workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
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

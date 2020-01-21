package entities;
// Generated 21 ene. 2020 12:50:14 by Hibernate Tools 5.2.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * InvoiceDetail generated by hbm2java
 */
@Entity
@Table(name = "invoice_detail", catalog = "7057507_administration_db")
public class InvoiceDetail implements java.io.Serializable {

	private InvoiceDetailId id;
	private Integer invoiceId;
	private Double quantity;
	private Double price;
	private Double priceUnit;

	public InvoiceDetail() {
	}

	public InvoiceDetail(InvoiceDetailId id) {
		this.id = id;
	}

	public InvoiceDetail(InvoiceDetailId id, Integer invoiceId, Double quantity, Double price, Double priceUnit) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.quantity = quantity;
		this.price = price;
		this.priceUnit = priceUnit;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "invoiceDetailId", column = @Column(name = "invoice_detail_id", nullable = false)),
			@AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false, length = 30)) })
	public InvoiceDetailId getId() {
		return this.id;
	}

	public void setId(InvoiceDetailId id) {
		this.id = id;
	}

	@Column(name = "invoice_id")
	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Column(name = "quantity", precision = 22, scale = 0)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "price_unit", precision = 22, scale = 0)
	public Double getPriceUnit() {
		return this.priceUnit;
	}

	public void setPriceUnit(Double priceUnit) {
		this.priceUnit = priceUnit;
	}

}

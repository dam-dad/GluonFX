package entities;
<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/InvoiceDetail.java
// Generated 25 ene. 2020 22:19:24 by Hibernate Tools 5.2.12.Final
=======
<<<<<<< HEAD
// Generated 24 ene. 2020 9:53:08 by Hibernate Tools 5.2.12.Final
=======
// Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/InvoiceDetail.java

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

<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/InvoiceDetail.java
	public InvoiceDetail(Invoice invoice, Product product, Double quantity, Double price, Double priceUnit) {
		this.invoice = invoice;
		this.product = product;
=======
<<<<<<< HEAD
	public InvoiceDetail(InvoiceDetailId id) {
		this.id = id;
	}

	public InvoiceDetail(InvoiceDetailId id, Integer invoiceId, Double quantity, Double price, Double priceUnit) {
		this.id = id;
		this.invoiceId = invoiceId;
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/InvoiceDetail.java
		this.quantity = quantity;
		this.price = price;
		this.priceUnit = priceUnit;
	}

<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/InvoiceDetail.java
=======
	@EmbeddedId
=======
	
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/InvoiceDetail.java
	@Id
	@GeneratedValue(strategy = IDENTITY)
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003

	@AttributeOverrides({
			@AttributeOverride(name = "invoiceDetailId", column = @Column(name = "invoice_detail_id", nullable = false)),
			@AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false, length = 30)) })
	public InvoiceDetailId getId() {
		return this.id;
	}

	public void setId(InvoiceDetailId id) {
		this.id = id;
	}

<<<<<<< HEAD
	@Column(name = "invoice_id")
	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
=======
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id")
	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
<<<<<<< HEAD:GluonFXDesktop_viejo/src/main/java/entities/InvoiceDetail.java
=======
		this.product_prop.set(product);
>>>>>>> dae55e6b1c545256e284754ebc17ecc715251003
>>>>>>> 90b2be75c01262763035c56e8493c45fa000fd63:GluonFXDesktop/src/main/java/entities/InvoiceDetail.java
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

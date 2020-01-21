package entities;
// Generated 21 ene. 2020 12:50:14 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "7057507_administration_db")
public class Product implements java.io.Serializable {

	private String productId;
	private String name;
	private String description;
	private Double price;
	private String url;

	public Product() {
	}

	public Product(String productId) {
		this.productId = productId;
	}

	public Product(String productId, String name, String description, Double price, String url) {
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.url = url;
	}

	@Id

	@Column(name = "product_id", unique = true, nullable = false, length = 30)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

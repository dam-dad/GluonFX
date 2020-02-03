package entities;
//Generated 30 ene. 2020 8:50:01 by Hibernate Tools 5.2.12.Final

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
* Invoice generated by hbm2java
*/
@Entity
@Table(name = "invoice", catalog = "7057507_administration_db")
public class Invoice implements java.io.Serializable {

	private Integer id;
	private Company company;
	private Customer customer;
	private PayMethod payMethod;
	private Tax tax;
	private String invoiceNumber;
	private Date invoiceDate;
	private Integer status;
	private Integer conceptId;
	private Double price;
	private Double taxTotal;
	private Double priceTaxesIncluded;
	private List<ConceptInvoice> conceptInvoices = new ArrayList<ConceptInvoice>(0);
	private List<InvoiceDetail> invoiceDetails = new ArrayList<InvoiceDetail>(0);
	
	
	//Properties
	private IntegerProperty id_prop = new SimpleIntegerProperty();
	private ObjectProperty<Company> company_prop = new SimpleObjectProperty<>();
	private ObjectProperty<Customer> customer_prop = new SimpleObjectProperty<>();
	private ObjectProperty<PayMethod> payMethod_prop = new SimpleObjectProperty<>();
	private ObjectProperty<Tax> tax_prop = new SimpleObjectProperty<>();
	private StringProperty invoiceNumber_prop = new SimpleStringProperty();
	private ObjectProperty<LocalDate> invoiceDate_prop = new SimpleObjectProperty<>();
	private IntegerProperty status_prop = new SimpleIntegerProperty();
	private IntegerProperty conceptId_prop = new SimpleIntegerProperty();
	private DoubleProperty price_prop = new SimpleDoubleProperty();
	private DoubleProperty taxTotal_prop = new SimpleDoubleProperty();
	private DoubleProperty priceTaxesIncluded_prop = new SimpleDoubleProperty();
	private ListProperty<ConceptInvoice> conceptInvoices_prop = new SimpleListProperty<>();
	private ListProperty<InvoiceDetail> invoiceDetails_prop = new SimpleListProperty<>();

	public Invoice() {
	}

		
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.id_prop.set(id);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
		this.company_prop.set(company);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.customer_prop.set(customer);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_method_id")
	public PayMethod getPayMethod() {
		return this.payMethod;
	}


	
	public void setPayMethod(PayMethod payMethod) {
		this.payMethod = payMethod;
		this.payMethod_prop.set(payMethod);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tax_id")
	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
		this.tax_prop.set(tax);
	}

	@Column(name = "invoice_number")
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
		this.invoiceNumber_prop.set(invoiceNumber);
	}

	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date", length = 10)
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
		this.invoiceDate_prop.set(localDateToDateConverter(invoiceDate));
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
		this.status_prop.set(status);
	}
	
	

	@Column(name = "concept_id")
	public Integer getConceptId() {
		return this.conceptId;
	}

	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
		this.conceptId_prop.set(conceptId);
	}

	@Column(name = "price", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
		this.price_prop.set(price);
	}

	@Column(name = "tax_total", precision = 22, scale = 0)
	public Double getTaxTotal() {
		return this.taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
		this.taxTotal_prop.set(taxTotal);
	}
	
	
	

	@Column(name = "price_taxes_included", precision = 22, scale = 0)
	public Double getPriceTaxesIncluded() {
		return this.priceTaxesIncluded;
	}

	public void setPriceTaxesIncluded(Double priceTaxesIncluded) {
		this.priceTaxesIncluded = priceTaxesIncluded;
		this.priceTaxesIncluded_prop.set(priceTaxesIncluded);
	}
	


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	public List<ConceptInvoice> getConceptInvoices() {
		return this.conceptInvoices;
	}

	public void setConceptInvoices(List<ConceptInvoice> conceptInvoices) {
		this.conceptInvoices = conceptInvoices;
		this.conceptInvoices_prop.set(FXCollections.observableArrayList(conceptInvoices));
	}
	
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	public List<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}
		

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
		this.invoiceDetails_prop.set(FXCollections.observableArrayList(invoiceDetails));
	}
	
	
	
	//Converters
	public LocalDate localDateToDateConverter(Date date) {
		LocalDate dateA = new java.sql.Date(date.getTime()).toLocalDate();
		return dateA;
	}
	
	//JavaFX getters y setters

	public Date dateToLocalDateConverter(LocalDate localDate) {
		Date dateA = java.sql.Date.valueOf(localDate);
		return dateA;
	}

	public final IntegerProperty id_propProperty() {
		return this.id_prop;
	}
	
	public final ObjectProperty<Company> company_propProperty() {
		return this.company_prop;
	}
	
	public final ObjectProperty<Customer> customer_propProperty() {
		return this.customer_prop;
	}
	
	public final ObjectProperty<PayMethod> payMethod_propProperty() {
		return this.payMethod_prop;
	}
	
	public final ObjectProperty<Tax> tax_propProperty() {
		return this.tax_prop;
	}
	
	public final StringProperty invoiceNumber_propProperty() {
		return this.invoiceNumber_prop;
	}
	
	public final ObjectProperty<LocalDate> invoiceDate_propProperty() {
		return this.invoiceDate_prop;
	}
	
	public final IntegerProperty status_propProperty() {
		return this.status_prop;
	}
	
	public final IntegerProperty conceptId_propProperty() {
		return this.conceptId_prop;
	}
	
	public final DoubleProperty price_propProperty() {
		return this.price_prop;
	}
	
	public final DoubleProperty taxTotal_propProperty() {
		return this.taxTotal_prop;
	}
	
	public final DoubleProperty priceTaxesIncluded_propProperty() {
		return this.priceTaxesIncluded_prop;
	}
	
	public final ListProperty<ConceptInvoice> conceptInvoices_propProperty() {
		return this.conceptInvoices_prop;
	}
	
	public final ListProperty<InvoiceDetail> invoiceDetails_propProperty() {
		return this.invoiceDetails_prop;
	}
	
	

}
package com.utils;

import java.util.ArrayList;
import java.util.List;

import com.gluonhq.impl.charm.a.b.b.i;
import com.google.gson.Gson;

import com.mashape.unirest.http.Unirest;
import com.model.entities.Company;
import com.model.entities.ConceptInvoice;
import com.model.entities.Customer;
import com.model.entities.Invoice;
import com.model.entities.InvoiceDetail;
import com.model.entities.PayMethod;
import com.model.entities.Product;
import com.model.entities.Tax;


public class Service {

	public Service() {
		Unirest.setObjectMapper(new UnirestObjectMapper()); 
	}
	
	
	/*
	 * Añade una nueva compañia
	 */
	
	public void insertCompany(Company company) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createCompany")
				.field("company_id", company.getCompanyId())
				.field("name", company.getName())
				.field("address", company.getAddress())
				.field("city", company.getCity())
				.field("country", company.getCountry())
				.field("email", company.getEmail())
				.field("phone", company.getPhone())
				.asString();
			
			System.out.println("compañia insertada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza una compañia existente
	 */
	
	public void updateCompany(Company company) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updateCompany")
				.field("id", company.getId())
				.field("company_id", company.getCompanyId())
				.field("name", company.getName())
				.field("address", company.getAddress())
				.field("city", company.getCity())
				.field("country", company.getCountry())
				.field("email", company.getEmail())
				.field("phone", company.getPhone())
				.asString();
			
			System.out.println("compañia actualizada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Elimina una compañía existente
	 */	
	
	public void deleteCompany(Company company) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deleteCompany")
				.field("id", company.getId())				
				.asString();
			
			System.out.println("compañia eliminada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto compañia por su id
	 */
	
	public Company getCompanybyId(int id) {

		Company company = null;

		try {
			company = Unirest
					.get("http://moimahservices.ddns.net:16941/companyById?id=" + id)
					.asObject(Company.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return company;
				
	}
	
	/*
	 * Devuelve una lista de objetos compañia
	 */
	
	public List<Company> getAllCompanies() {
		
		List<Company>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allCompany")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		Company[] companies = gson.fromJson(result, Company[].class);
		
		for (int i = 0; i < companies.length; i++) {
			list.add(companies[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	
	
	/*
	 * Añade una nuevo concepto a una factura
	 */
	
	public void insertConceptInvoice(ConceptInvoice conceptInvoice, Invoice invoice) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createConceptInvoice")
				.field("invoice_id", invoice.getId())
				.field("description", conceptInvoice.getDescription())
				.field("price", conceptInvoice.getPrice())
				.asString();
			
			System.out.println("concepto insertado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza una concepto existente
	 */
	
	public void updateConceptInvoice(ConceptInvoice conceptInvoice, Invoice invoice) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updateConceptInvoice")
				.field("invoice_id", invoice.getId())
				.field("id", conceptInvoice.getId())
				.field("invoice_id", conceptInvoice.getId())
				.field("description", conceptInvoice.getDescription())
				.field("price", conceptInvoice.getPrice())
				.asString();
			
			System.out.println("concepto actualizado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Elimina un concepto
	 */	
	
	public void deleteconceptInvoice(ConceptInvoice conceptInvoice) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deleteConceptInvoice")
				.field("id", conceptInvoice.getId())				
				.asString();
			
			System.out.println("concepto eliminado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto conceptInvoice por su id
	 */
	
	public ConceptInvoice getconceptInvoicebyId(int id) {

		ConceptInvoice conceptInvoice = null;

		try {
			conceptInvoice = Unirest
					.get("http://moimahservices.ddns.net:16941/conceptInvoiceById?id=" + id)
					.asObject(ConceptInvoice.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conceptInvoice;
				
	}
	
	/*
	 * Devuelve una lista de conceptos de compañia
	 */
	
	public List<ConceptInvoice> getAllConceptInvoices() {
		
		List<ConceptInvoice>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allConceptInvoice")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		ConceptInvoice[] conceptInvoices = gson.fromJson(result, ConceptInvoice[].class);
		
		for (int i = 0; i < conceptInvoices.length; i++) {
			list.add(conceptInvoices[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	
	
	/*
	 * Añade un nuevo cliente
	 */
	
	public void insertCustomer(Customer customer) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createCustomer")
				.field("customer_id", customer.getCustomerId())
				.field("name", customer.getName())
				.field("address", customer.getAddress())
				.field("city", customer.getCity())
				.field("country", customer.getCountry())
				.field("email", customer.getEmail())
				.field("phone", customer.getPhone())
				.asString();
			
			System.out.println("cliente insertado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza un cliente existente
	 */
	
	public void updateCustomer(Customer customer) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updateCustomer")
				.field("id", customer.getId())
				.field("customer_id", customer.getCustomerId())
				.field("name", customer.getName())
				.field("address", customer.getAddress())
				.field("city", customer.getCity())
				.field("country", customer.getCountry())
				.field("email", customer.getEmail())
				.field("phone", customer.getPhone())
				.asString();
			
			System.out.println("cliente actualizado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Elimina una cliente existente
	 */	
	
	public void deleteCustomer(Customer customer) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deleteCustomer")
				.field("id", customer.getId())				
				.asString();
			
			System.out.println("cliente eliminado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto cliente por su id
	 */
	
	public Customer getCustomerbyId(int id) {

		Customer customer = null;

		try {
			customer = Unirest
					.get("http://moimahservices.ddns.net:16941/customerById?id=" + id)
					.asObject(Customer.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customer;
				
	}
	
	/*
	 * Devuelve una lista de objetos cliente
	 */
	
	public List<Customer> getAllCustomers() {
		
		List<Customer>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allCustomer")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		Customer[] customers = gson.fromJson(result, Customer[].class);
		
		for (int i = 0; i < customers.length; i++) {
			list.add(customers[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	
	/*
	 * Añade una nueva compañia
	 */
	
	public void insertInvoice(Invoice invoice) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createInvoice")
				.field("company_id", invoice.getCompany().getId())
				.field("customer_id", invoice.getCustomer().getId())
				.field("invoice_date", invoice.getInvoiceDate())
				.field("concept_id", invoice.getConceptId())
				.field("pay_method_id", invoice.getPayMethod().getId())
				.field("tax_id", invoice.getTax().getId())
				.field("status", invoice.getStatus())
				.asString();
			
			System.out.println("factura insertada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza una factura existente
	 */
	
	public void updateInvoice(Invoice invoice) {
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updateInvoice")
				.field("id", invoice.getId())
				.field("invoice_number", invoice.getInvoiceNumber())				
				.field("company_id", invoice.getCompany().getId())
				.field("customer_id", invoice.getCustomer().getId())
				.field("invoice_date", invoice.getInvoiceDate())
				.field("concept_id", invoice.getConceptId())
				.field("pay_method_id", invoice.getPayMethod().getId())
				.field("tax_id", invoice.getTax().getId())
				.field("status", invoice.getStatus())				
				.field("tax_total", invoice.getTaxTotal())
				.field("price", invoice.getPrice())
				.field("price_tax_included", invoice.getPriceTaxesIncluded())
				.asString();
			
			System.out.println("factura actualizada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Elimina una factura existente
	 */	
	
	public void deleteinvoice(Invoice invoice) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deleteInvoice")
				.field("id", invoice.getId())				
				.asString();
			
			System.out.println("factura eliminada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto invoice por su id
	 */
	
	public Invoice getinvoicebyId(int id) {

		Invoice invoice = null;

		try {
			invoice = Unirest
					.get("http://moimahservices.ddns.net:16941/invoiceById?id=" + id)
					.asObject(Invoice.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return invoice;
				
	}
	
	/*
	 * Devuelve una lista de objetos invoice
	 */
	
	public List<Invoice> getAllinvoices() {
		
		List<Invoice>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allInvoice")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		Invoice[] invoices = gson.fromJson(result, Invoice[].class);
		
		for (int i = 0; i < invoices.length; i++) {
			list.add(invoices[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}
	
	
	/*
	 * Añade un nuevo detalle
	 */
	
	public void insertinvoiceDetail(InvoiceDetail invoiceDetail, Invoice invoice) {
	
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createInvoiceDetail")
				.field("invoice_id", invoice.getId())	
				.field("product_id", invoiceDetail.getProduct().getId())
				.field("quantity", invoiceDetail.getQuantity())				
				.asString();
			
			System.out.println("detalle insertado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza un detalle existente
	 */
	
	public void updateInvoiceDetail(InvoiceDetail invoiceDetail, Invoice invoice) {
	//int id, int invoice_id, int product_id,double quantity,double price_ud
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updateInvoiceDetail")	
				.field("id", invoiceDetail.getId())
				.field("invoice_id", invoice.getId())					
				.field("product_id", invoiceDetail.getProduct().getId())
				.field("quantity", invoiceDetail.getQuantity())	
				.field("price_ud", invoiceDetail.getProduct().getPrice())
				.asString();	
	
			
			System.out.println("Detalle actualizado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Elimina un detalle existente
	 */	
	
	public void deleteInvoiceDetail(InvoiceDetail invoiceDetail) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deleteInvoiceDetail")
				.field("id", invoiceDetail.getId())				
				.asString();
			
			System.out.println("detalle eliminado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto detalle por su id
	 */
	
	public InvoiceDetail getInvoiceDetailbyId(int id) {

		InvoiceDetail invoiceDetail = null;

		try {
			invoiceDetail = Unirest
					.get("http://moimahservices.ddns.net:16941/invoiceDetailById?id=" + id)
					.asObject(InvoiceDetail.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return invoiceDetail;
				
	}
	
	/*
	 * Devuelve una lista de objetos detalle
	 */
	
	public List<InvoiceDetail> getAllInvoiceDetails() {
		
		List<InvoiceDetail>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allInvoiceDetail")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		InvoiceDetail[] invoiceDetails = gson.fromJson(result, InvoiceDetail[].class);
		
		for (int i = 0; i < invoiceDetails.length; i++) {
			list.add(invoiceDetails[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}

	
	/*
	 * Añade una nueva metodo de pago
	 */
	
	public void insertPayMethod(PayMethod payMethod) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createPayMethod")
				.field("description", payMethod.getDescription())			
				.asString();
			
			System.out.println("metodo de pago insertado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza una metodo de pago existente
	 */
	
	public void updatePayMethod(PayMethod payMethod) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updatePayMethod")
				.field("id", payMethod.getId())
				.field("description", payMethod.getDescription())			
				.asString();
			
			System.out.println("metodo de pago actualizado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Elimina un metodo de pago existente
	 */	
	
	public void deletePayMethod(PayMethod payMethod) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deletePayMethod")
				.field("id", payMethod.getId())				
				.asString();
			
			System.out.println("metodo de pago eliminado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto metodo de pago por su id
	 */
	
	public PayMethod getPayMethodbyId(int id) {

		PayMethod payMethod = null;

		try {
			payMethod = Unirest
					.get("http://moimahservices.ddns.net:16941/payMethodById?id=" + id)
					.asObject(PayMethod.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return payMethod;
				
	}
	
	/*
	 * Devuelve una lista de objetos metodo de pago
	 */
	
	public List<PayMethod> getAllPayMethods() {
		
		List<PayMethod>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allPayMethod")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		PayMethod[] payMethods = gson.fromJson(result, PayMethod[].class);
		
		for (int i = 0; i < payMethods.length; i++) {
			list.add(payMethods[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;

	}
	

	
	/*
	 * Añade un nueva Producto
	 */
	
	public void insertProduct(Product product) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createProduct")
				.field("product_id", product.getProductId())
				.field("name", product.getName())
				.field("description", product.getDescription())
				.field("price", product.getPrice())
				.field("stock", product.getStock())
				.field("url", product.getUrl())				
				.asString();
			
			System.out.println("Producto insertado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza un Producto existente
	 */
	
	public void updateProduct(Product product) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updateProduct")
				.field("id", product.getId())
				.field("product_id", product.getProductId())
				.field("name", product.getName())
				.field("description", product.getDescription())
				.field("price", product.getPrice())
				.field("stock", product.getStock())
				.field("url", product.getUrl())				
				.asString();
			
			System.out.println("Producto actualizado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Elimina una Producto existente
	 */	
	
	public void deleteProduct(Product Product) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deleteProduct")
				.field("id", Product.getId())				
				.asString();
			
			System.out.println("Producto eliminado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto Producto por su id
	 */
	
	public Product getProductbyId(int id) {

		Product Product = null;

		try {
			Product = Unirest
					.get("http://moimahservices.ddns.net:16941/productById?id=" + id)
					.asObject(Product.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Product;
				
	}
	
	/*
	 * Devuelve una lista de objetos Producto
	 */
	
	public List<Product> getAllProducts() {
		
		List<Product>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allProduct")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		Product[] products = gson.fromJson(result, Product[].class);
		
		for (int i = 0; i < products.length; i++) {
			list.add(products[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
	
	}
	
	
	/*
	 * Añade un nueva impuesto
	 */
	
	public void insertTax(Tax tax) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/createTax")
				.field("tax_id", tax.getTaxId())
				.field("percentage", tax.getPercentage())
				.field("description", tax.getDescription())
				.asString();
			
			System.out.println("impuesto insertada");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Actualiza un impuesto existente
	 */
	
	public void updateTax(Tax tax) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/updateTax")
				.field("id", tax.getId())
				.field("tax_id", tax.getTaxId())
				.field("percentage", tax.getPercentage())
				.field("description", tax.getDescription())
				.asString();
				
			
			System.out.println("impuesto actualizado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Elimina un impuesto existente
	 */	
	
	public void deleteTax(Tax tax) {
		
		try {
			Unirest.post("http://moimahservices.ddns.net:16941/deleteTax")
				.field("id", tax.getId())				
				.asString();
			
			System.out.println("impuesto eliminado");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Devuelve objeto impuesto por su id
	 */
	
	public Tax getTaxbyId(int id) {

		Tax Tax = null;

		try {
			Tax = Unirest
					.get("http://moimahservices.ddns.net:16941/taxById?id=" + id)
					.asObject(Tax.class)
					.getBody();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Tax;
				
	}
	
	/*
	 * Devuelve una lista de objetos impuesto
	 */
	
	public List<Tax> getAllTaxes() {
		
		List<Tax>  list = new ArrayList<>();
		
		try {
			
		String result =  Unirest
					.get("http://moimahservices.ddns.net:16941/allTax")
					.asString()
					.getBody();

	
		Gson gson = new Gson();
		Tax[] tax = gson.fromJson(result, Tax[].class);
		
		for (int i = 0; i < tax.length; i++) {
			list.add(tax[i]);
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
		
	}	
	
	
	
}

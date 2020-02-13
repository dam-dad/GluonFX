package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.InvoiceDetail;
import com.moimah.hibernate.spring.entities.InvoiceDetail;

/**
 * 
 * Esta es la clase que usaremos para acceder a los datos de las entidades User.
 * Al estar anotada con el estereotipo @Repository, será localizada rapidamente,
 * y usada para tal fin.
 * 
 * Al tener definido un motor de transacciones en DatabaseConfig, toda clase
 * anotada con @Transactional provocará que se invoquen los método begin()
 * y commit() de forma "mágica" en el inicio y el fin del método.
 * 
 * 
 */
@Repository
@Transactional
public class InvoiceDetailDao {

	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD	

	/**
	 * Almacena una factura en la base de datos
	 */	
	public void create(InvoiceDetail invoiceDetail) {		
		entityManager.persist(invoiceDetail);
	}
	
	/**
	 * Elimina una factura de la base de datos.
	 */	
	public void delete(InvoiceDetail invoiceDetail) {
		
		if(entityManager.contains(invoiceDetail)) {
			entityManager.remove(invoiceDetail);
		}else {
			entityManager.remove(entityManager.merge(invoiceDetail));				
		}
	}
	
	
	/**
	 * Actualiza la  factura proporcionada
	 */	
	public void update(InvoiceDetail invoiceDetail) {
		entityManager.merge(invoiceDetail);
	}
	
	/**
	 * Devuelve una factura en base a su Id
	 */	
	public InvoiceDetail getInvoiceDetailById(int id) {
		return entityManager.find(InvoiceDetail.class, id);
	}
	
	
	/**
	 * Devuelve todos las facturas de la base de datos.
	 */	
	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> getAll(){		
		return entityManager.createQuery("SELECT i FROM InvoiceDetail i").getResultList();
	}
	
	
	
	
}
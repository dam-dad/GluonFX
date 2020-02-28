package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.InvoiceDetail;

/**
 * Esta es la clase que  para acceder a los datos de las entidades InvoiceDetail.
 * Al estar anotada con el estereotipo @Repository, será localizada rapidamente,
 * y usada para tal fin.
 * 
 * Al tener definido un motor de transacciones en DatabaseConfig, toda clase
 * anotada con @Transactional provocará que se invoquen los método begin()
 * y commit() de forma "mágica" en el inicio y el fin del método.
 * 
 * 
 * @author moimah
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
	 * Almacena un invoiceDetail en la bbdd
	 * @param invoiceDetail a almacenar
	 */
	public void create(InvoiceDetail invoiceDetail) {		
		entityManager.persist(invoiceDetail);
	}
	
	/**
	 * Elimina un invoiceDetail de la bbddd
	 * @param invoiceDetail invoiceDetail a eliminar
	 */
	public void delete(InvoiceDetail invoiceDetail) {
		
		if(entityManager.contains(invoiceDetail)) {
			entityManager.remove(invoiceDetail);
		}else {
			entityManager.remove(entityManager.merge(invoiceDetail));				
		}
	}
	
	
	/**
	 * Actualiza el invoiceDetail en la bbdd
	 * @param invoiceDetail
	 */
	public void update(InvoiceDetail invoiceDetail) {
		entityManager.merge(invoiceDetail);
	}
	
	/**
	 * Busca y devuelve un invoiceDetail por su id
	 * @param id del invoiceDetail a buscar
	 * @return invoiceDetail encontrado
	 */
	public InvoiceDetail getInvoiceDetailById(int id) {
		return entityManager.find(InvoiceDetail.class, id);
	}
	
	
	/**
	 * Busca y devuelve en forma de lista todos los invoiceDetails encontrados
	 * @return listInvoiceDetails lista con todos los invoiceDetails
	 */
	@SuppressWarnings("unchecked")
	public List<InvoiceDetail> getAll(){		
		return entityManager.createQuery("SELECT i FROM InvoiceDetail i").getResultList();
	}
	
	
	
	
}
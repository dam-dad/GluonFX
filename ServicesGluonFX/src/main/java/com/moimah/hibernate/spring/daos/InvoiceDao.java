package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.Invoice;

/**
 * Esta es la clase que  para acceder a los datos de las entidades Invoice.
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
public class InvoiceDao {

	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD	

	/**
	 * Almacena un invoice en la bbdd
	 * @param invoice a almacenar
	 */
	public void create(Invoice invoice) {		
		entityManager.persist(invoice);
	}
	
	/**
	 * Elimina un invoice de la bbddd
	 * @param invoice invoice a eliminar
	 */
	public void delete(Invoice invoice) {
		
		if(entityManager.contains(invoice)) {
			entityManager.remove(invoice);
		}else {
			entityManager.remove(entityManager.merge(invoice));				
		}
	}
	
	
	/**
	 * Actualiza el invoice en la bbdd
	 * @param invoice
	 */	
	public void update(Invoice invoice) {
		entityManager.merge(invoice);			
		
	}
	
	/**
	 * Busca y devuelve un invoice por su id
	 * @param id del invoice a buscar
	 * @return invoice encontrado
	 */
	public Invoice getInvoiceById(int id) {
		return entityManager.find(Invoice.class, id);
	}
	
	
	/**
	 * Busca y devuelve en forma de lista todos los invoices encontrados
	 * @return listInvoices lista con todos los invoices
	 */	
	@SuppressWarnings("unchecked")
	public List<Invoice> getAll(){		
		return entityManager.createQuery("SELECT i FROM Invoice i").getResultList();
	}
	
	
	
	
}

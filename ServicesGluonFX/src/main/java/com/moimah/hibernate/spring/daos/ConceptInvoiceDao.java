package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.ConceptInvoice;

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
public class ConceptInvoiceDao {
	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	
	//METODOS CRUD
	
	/**
	 * Almacena un concepto en la base de datos
	 */	
	public void create(ConceptInvoice conceptInvoice) {		
		entityManager.persist(conceptInvoice);
	}
	
	
	/**
	 * Elimina un concepto de la base de datos.
	 */	
	public void delete(ConceptInvoice conceptInvoice) {
		
		if(entityManager.contains(conceptInvoice)) {
			entityManager.remove(conceptInvoice);
		}else {
			entityManager.remove(entityManager.merge(conceptInvoice));				
		}
	}
	
	/**
	 * Actualiza el concepto proporcionado
	 */	
	public void update(ConceptInvoice conceptInvoice) {
		entityManager.merge(conceptInvoice);
	}
	
	/**
	 * Devuelve un concepto en base a su Id
	 */	
	public ConceptInvoice getConceptInvoiceById(int id) {
		return entityManager.find(ConceptInvoice.class, id);
	}
	
	
	/**
	 * Devuelve todos los conceptos de la base de datos.
	 */	
	@SuppressWarnings("unchecked")
	public List<ConceptInvoice> getAll(){		
		return entityManager.createQuery("SELECT c FROM ConceptInvoice c").getResultList();
	}

}

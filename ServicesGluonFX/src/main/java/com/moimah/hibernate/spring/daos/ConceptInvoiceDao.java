package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.ConceptInvoice;

/**
 * Esta es la clase que  para acceder a los datos de las entidades ConceptInvoice.
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
public class ConceptInvoiceDao {
	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	
	//METODOS CRUD
	
	/**
	 * Almacena un conceptInvoice en la bbdd
	 * @param conceptInvoice a almacenar
	 */
	public void create(ConceptInvoice conceptInvoice) {		
		entityManager.persist(conceptInvoice);
	}
	
	
	/**
	 * Elimina un conceptInvoice de la bbddd
	 * @param conceptInvoice conceptInvoice a eliminar
	 */
	public void delete(ConceptInvoice conceptInvoice) {
		
		if(entityManager.contains(conceptInvoice)) {
			entityManager.remove(conceptInvoice);
		}else {
			entityManager.remove(entityManager.merge(conceptInvoice));				
		}
	}
	
	
	/**
	 * Actualiza el conceptInvoice en la bbdd
	 * @param conceptInvoice
	 */
	public void update(ConceptInvoice conceptInvoice) {
		entityManager.merge(conceptInvoice);
	}
	
	
	/**
	 * Busca y devuelve un conceptInvoice por su id
	 * @param id del conceptInvoice a buscar
	 * @return conceptInvoice encontrado
	 */	
	public ConceptInvoice getConceptInvoiceById(int id) {
		return entityManager.find(ConceptInvoice.class, id);
	}
	
	
	/**
	 * Busca y devuelve en forma de lista todos los conceptInvoices encontrados
	 * @return listConceptInvoices lista con todos los conceptInvoice
	 */
	@SuppressWarnings("unchecked")
	public List<ConceptInvoice> getAll(){		
		return entityManager.createQuery("SELECT c FROM ConceptInvoice c").getResultList();
	}

}

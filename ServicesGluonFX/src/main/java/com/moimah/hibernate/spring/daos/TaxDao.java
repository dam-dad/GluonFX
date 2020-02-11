package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.Tax;

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
public class TaxDao {

	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD	

	/**
	 * Almacena una factura en la base de datos
	 */	
	public void create(Tax tax) {		
		entityManager.persist(tax);
	}
	
	/**
	 * Elimina una factura de la base de datos.
	 */	
	public void delete(Tax tax) {
		
		if(entityManager.contains(tax)) {
			entityManager.remove(tax);
		}else {
			entityManager.remove(entityManager.merge(tax));				
		}
	}
	
	
	/**
	 * Actualiza la  factura proporcionada
	 */	
	public void update(Tax tax) {
		entityManager.merge(tax);
	}
	
	/**
	 * Devuelve una factura en base a su Id
	 */	
	public Tax getTaxById(int id) {
		return entityManager.find(Tax.class, id);
	}
	
	
	/**
	 * Devuelve todos las facturas de la base de datos.
	 */	
	@SuppressWarnings("unchecked")
	public List<Tax> getAll(){		
		return entityManager.createQuery("SELECT i FROM Tax i").getResultList();
	}
	
	
	
	
}

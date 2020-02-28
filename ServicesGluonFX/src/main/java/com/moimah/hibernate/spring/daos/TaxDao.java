package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.Tax;

/**
 * Esta es la clase que  para acceder a los datos de las entidades Tax.
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
public class TaxDao {

	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD	

	/**
	 * Almacena un tax en la bbdd
	 * @param tax a almacenar
	 */
	public void create(Tax tax) {		
		entityManager.persist(tax);
	}
	
	
	/**
	 * Elimina un tax de la bbddd
	 * @param tax tax a eliminar
	 */
	public void delete(Tax tax) {
		
		if(entityManager.contains(tax)) {
			entityManager.remove(tax);
		}else {
			entityManager.remove(entityManager.merge(tax));				
		}
	}
	
	
	/**
	 * Actualiza el tax en la bbdd
	 * @param tax
	 */
	public void update(Tax tax) {
		entityManager.merge(tax);
	}
	
	/**
	 * Busca y devuelve un tax por su id
	 * @param id del tax a buscar
	 * @return tax encontrado
	 */
	public Tax getTaxById(int id) {
		return entityManager.find(Tax.class, id);
	}
	
	
	/**
	 * Busca y devuelve en forma de lista todos los taxs encontrados
	 * @return listTaxs lista con todos los tax
	 */
	@SuppressWarnings("unchecked")
	public List<Tax> getAll(){		
		return entityManager.createQuery("SELECT i FROM Tax i").getResultList();
	}
	
	
	
	
}

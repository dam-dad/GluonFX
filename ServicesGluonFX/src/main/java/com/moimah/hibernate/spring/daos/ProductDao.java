package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.Product;

/**
 * Esta es la clase que  para acceder a los datos de las entidades Product.
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
public class ProductDao {

	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD	

	/**
	 * Almacena un product en la bbdd
	 * @param product a almacenar
	 */
	public void create(Product product) {			
		//entityManager.persist(product); //doesn´t work
		entityManager.merge(product);
	}
	
	
	/**
	 * Elimina un product de la bbddd
	 * @param product product a eliminar
	 */
	public void delete(Product product) {
		
		if(entityManager.contains(product)) {
			entityManager.remove(product);
		}else {
			entityManager.remove(entityManager.merge(product));				
		}
	}
	
	
	/**
	 * Actualiza el product en la bbdd
	 * @param product
	 */
	public void update(Product product) {
		entityManager.merge(product);
	}
	

	/**
	 * Busca y devuelve un product por su id
	 * @param id del product a buscar
	 * @return product encontrado
	 */	
	public Product getProductById(int id) {
		return entityManager.find(Product.class, id);
	}
	
	
	/**
	 * Busca y devuelve en forma de lista todos los products encontrados
	 * @return listProducts lista con todos los product
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAll(){		
		return entityManager.createQuery("SELECT p FROM Product p").getResultList();
	}
	
	
	
	
}
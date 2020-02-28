package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.Customer;
	
/**
 * Esta es la clase que  para acceder a los datos de las entidades Customer.
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
public class CustomerDao {

	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD

	/**
	 * Almacena un customer en la bbdd
	 * @param customer a almacenar
	 */
	public void create(Customer customer) {
		entityManager.persist(customer);
	}

	/**
	 * Elimina un customer de la bbddd
	 * @param customer customer a eliminar
	 */
	public void delete(Customer customer) {

		if (entityManager.contains(customer)) {
			entityManager.remove(customer);
		} else {
			entityManager.remove(entityManager.merge(customer));
		}
	}

	/**
	 * Actualiza el customer en la bbdd
	 * @param customer
	 */
	public void update(Customer customer) {
		entityManager.merge(customer);
	}

	/**
	 * Busca y devuelve un customer por su id
	 * @param id del customer a buscar
	 * @return customer encontrado
	 */
	public Customer getCustomerById(int id) {
		return entityManager.find(Customer.class, id);
	}

	/**
	 * Busca y devuelve en forma de lista todos los customers encontrados
	 * @return listCustomers lista con todos los customer
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> getAll() {
		return entityManager.createQuery("SELECT c FROM Customer c").getResultList();
	}

}

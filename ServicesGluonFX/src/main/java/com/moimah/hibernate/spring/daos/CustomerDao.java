package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.Customer;
	
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
	public class CustomerDao {
		
		// A través de la anotación @PersistenceContext, se inyectará automáticamente
		// un EntityManager producido desde el entityManagerFactory definido en la clase
		// DatabaseConfig.
	
		@PersistenceContext
		private EntityManager entityManager;
	
		// METODOS CRUD	
	
		/**
		 * Almacena un cliente en la base de datos
		 */	
		public void create(Customer customer) {		
			entityManager.persist(customer);
		}
		
		/**
		 * Elimina un cliente de la base de datos.
		 */	
		public void delete(Customer customer) {
			
			if(entityManager.contains(customer)) {
				entityManager.remove(customer);
			}else {
				entityManager.remove(entityManager.merge(customer));				
			}
		}
		
		
		/**
		 * Actualiza el cliente proporcionada
		 */	
		public void update(Customer customer) {
			entityManager.merge(customer);
		}
		
		/**
		 * Devuelve un compañia en base a su Id
		 */	
		public Customer getCustomerById(int id) {
			return entityManager.find(Customer.class, id);
		}
		
		
		/**
		 * Devuelve todos los clientes de la base de datos.
		 */	
		@SuppressWarnings("unchecked")
		public List<Customer> getAll(){		
			return entityManager.createQuery("SELECT c FROM Customer c").getResultList();
		}
		

}
	


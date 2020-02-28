package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.PayMethod;

/**
 * Esta es la clase que  para acceder a los datos de las entidades payMethod.
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
public class PayMethodDao {

	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD	

	/**
	 * Almacena un payMethod en la bbdd
	 * @param payMethod payMethod a almacenar
	 */
	public void create(PayMethod payMethod) {		
		entityManager.persist(payMethod);
	}
	
	/**
	 * Elimina un payMethod de la bbddd
	 * @param payMethod payMethod a eliminar
	 */
	public void delete(PayMethod payMethod) {
		
		if(entityManager.contains(payMethod)) {
			entityManager.remove(payMethod);
		}else {
			entityManager.remove(entityManager.merge(payMethod));				
		}
	}
	
	
	/**
	 * Actualiza el payMethod en la bbdd
	 * @param payMethod
	 */
	public void update(PayMethod payMethod) {
		entityManager.merge(payMethod);
	}
	
	/**
	 * Busca y devuelve un payMethod por su id
	 * @param id del payMethod a buscar
	 * @return payMethod payMethod
	 */
	public PayMethod getPayMethodById(int id) {
		return entityManager.find(PayMethod.class, id);
	}
	
	
	/**
	 * Busca y devuelve en forma de lista todos los payMethods
	 * @return listPayMethods lista con todos los payMethods
	 */
	@SuppressWarnings("unchecked")
	public List<PayMethod> getAll(){		
		return entityManager.createQuery("SELECT i FROM PayMethod i").getResultList();
	}
	
	
	
	
}

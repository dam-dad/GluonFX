package com.moimah.hibernate.spring.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.moimah.hibernate.spring.entities.Company;

/**
 * Esta es la clase que  para acceder a los datos de las entidades Company.
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
public class CompanyDao {
	
	// A través de la anotación @PersistenceContext, se inyectará automáticamente
	// un EntityManager producido desde el entityManagerFactory definido en la clase
	// DatabaseConfig.

	@PersistenceContext
	private EntityManager entityManager;

	// METODOS CRUD	

	/**
	 * Almacena un company en la bbdd
	 * @param company a almacenar
	 */
	public void create(Company company) {		
		entityManager.persist(company);
	}
	
	/**
	 * Elimina un company de la bbddd
	 * @param company company a eliminar
	 */
	public void delete(Company company) {
		
		if(entityManager.contains(company)) {
			entityManager.remove(company);
		}else {
		  entityManager.remove(entityManager.merge(company));				
		}
	}
	

	/**
	 * Actualiza la compañia proporcionada
	 */	
	public void update(Company company) {
		entityManager.merge(company);
	}
	
	/**
	 * Busca y devuelve un company por su id
	 * @param id del company a buscar
	 * @return company encontrado
	 */
	public Company getCompanyById(int id) {
		return entityManager.find(Company.class, id);
	}
	
	
	/**
	 * Busca y devuelve en forma de lista todas las companys encontrados
	 * @return listCompanys lista con todas las companys
	 */
	@SuppressWarnings("unchecked")
	public List<Company> getAll(){		
		return entityManager.createQuery("SELECT c FROM Company c").getResultList();
	}
	
	

}

package hibernate;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;


public class HibernateController {
	

		private StandardServiceRegistry sr;
		private SessionFactory sf;
		private Session session;

		/*
		 * Inicializa la conexion
		 */
		public void start() {

			//Inicialización del SessionFactory
			sr = new StandardServiceRegistryBuilder().configure().build();		
					
			sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();				
							
		    //Apertura de una sesión (e inicio de una transacción)		
		    session = sf.openSession();
			

		}
		
		
		public void close() {
			
			session.close();
		}
		
		
		/*
		 * Guarda un objeto
		 */
		public void save(Object object) {
			
					
				session.clear();
				
				session.beginTransaction();
				
				session.save(object);
						
				session.getTransaction().commit();
		
			
		}
		
				
		
		/*
		 * Actualiza un objeto
		 */
		
		public void update (Object object) {
			
			
			
			session.clear();
			
			session.beginTransaction();
			
			session.update(object);
			
			session.getTransaction().commit();
		}
		
		
		/*
		 * Elimina un objeto
		 */
		
		public void delete (Object object) {
			
			//session.clear();
			
			session.beginTransaction();
			
			session.delete(object);
			
			session.getTransaction().commit();
		}
		
		
		/*
		 * Selecciona todos los elementos de una lista concreta
		 */		
		/*
		 * Selecciona todos los elementos de una lista concreta
		 */		
		public List selectAll(String entityName) {
			
			
			//session.clear();
			
			session.beginTransaction();
			
			Query query = session.createQuery("From " + entityName);
			
			
			List lista = query.list();
			
			session.getTransaction().commit();
					
			return lista; 				
			
			
		}
		
		/*
		 * Cosulta personalizada
		 */		
		public List<?> customQuery(String customQuery) {
			
			
			session.clear();
			
			session.beginTransaction();
			
			Query<?> query = session.createQuery(customQuery);
			
			
			List<?> lista = query.list();
			
			session.getTransaction().commit();
					
			return lista; 				
						
		}
		
		public Object uniqueResultQuery(String hql) {	        
	        Query query = session.createQuery(hql);
	        query.setMaxResults(1);
	        Object object =  query.uniqueResult();
	        
	        return object;
		}
		

		

}

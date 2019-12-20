package org.hibernate_dbt.query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Lecturer;

public class InsertData_lecturer {	
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();	 
       Session session = factory.getCurrentSession();
	       try {  
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();
	 
	           Lecturer l1 = new Lecturer();
	           l1.setName("Herbert");
	           l1.setSurname("Leuterer");
	           l1.setAddress("Waldstr. 8");
	           l1.setBirthday("12.06.1973");
 
	           session.save(l1);
	           
	           // Commit data.
	           session.getTransaction().commit();
	           HibernateUtils.shutdown();
	       } catch (Exception e) {
	           e.printStackTrace();
	           // Rollback in case of an error occurred.
	           session.getTransaction().rollback();
	       }
	   }
}

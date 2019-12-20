package org.hibernate_dbt.query;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Course;
import org.hibernate_dbt.entities.Lecturer;

public class InsertData_course {
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
       Session session = factory.getCurrentSession();
	       try { 
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();
	 
	         //  Set<Lecturer> lecturers = new HashSet<Lecturer>();
	          // lecturers.add(new Lecturer("Prof", "surnam", "adresse 2 ", "19.09.1970"));
	           Lecturer l = new Lecturer("Andreas", "Müller", "19.09.1970", "Feldstraße 23");
	         
	            //session.save(l);   dies hier spare ich , hibernate generiert dies automatisch (many to one)
	           Course c1 = new Course("IT2", l);
	           
	           session.save(c1);
	           
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

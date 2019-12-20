package org.hibernate_dbt.query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Student;

public class QueryData_3b {
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
       Session session = factory.getCurrentSession();
	       try {
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();
	 
	           //Select all student objects with birthdate after 01/01/1995. 
	           String hql = "FROM Student e where e.birthday > '1.1.1995'";

	           // Create Query object.
	           Query<Student> query = session.createQuery(hql);
	 
	           // Execute query.
	           List<Student> students = query.getResultList();
	 
	           for (Student stds: students) {
	               System.out.println("Studenten: " + stds.getName() + " \t Geb: " + stds.getBirthday().subSequence(0, 10));
	           }
	  
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
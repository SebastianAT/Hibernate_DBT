package org.hibernate_dbt.query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Course;
import org.hibernate_dbt.entities.Lecturer;
import org.hibernate_dbt.entities.Student;

public class QueryData_3d {
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
       Session session = factory.getCurrentSession();
	       try {
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();
	 
	           // Update the address of lecturer Gerhard Weber to “Universitaetstrasse 1”.
	           String hql = "Update Lecturer set address = :address " + 
	                   " where name = :name and surname = :surname";
	          
	           // Create Query objec
	           Query query = session.createQuery(hql);
	           
	           query.setParameter("address", "Universitaetstrasse 11");
	           query.setParameter("name", "Gerhard");
	           query.setParameter("surname", "Weber");
	           
	           int result = query.executeUpdate();
	           if(result > 0) {
	        	   System.out.println("Rows affected: " + result);
	           } else {
	        	   System.out.println("Something went wrong.. ");
	           }
	           
	             
	           // Query lecturer to see changes.
	           String hql2 = "FROM Lecturer l where l.name = :name and l.surname = :surname";
	           
	           Query<Lecturer> query2 = session.createQuery(hql2);
	           query2.setParameter("name", "Gerhard");
	           query2.setParameter("surname", "Weber");
	 
	           // Execute query.
	           List<Lecturer> lecturers = query2.getResultList();
	 
	           for (Lecturer lcrs: lecturers) {
	               System.out.println("\nLecturer: " + lcrs.getName() + " " + lcrs.getSurname() + " \t Neue Adresse: " + lcrs.getAddress());
	           }
	           
	           
	        // possibilty 2 - get ID from DB with query above
	           /*
	           Lecturer lecturer2 = new Lecturer();
	           lecturer2 = (Lecturer) session.get(Lecturer.class, 1); // id from query here
	           lecturer2.setAddress("Universitaetsstrasse 1");
	           session.update(lecturer2);*/
	
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
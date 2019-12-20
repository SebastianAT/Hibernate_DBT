package org.hibernate_dbt.query;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Student;
import org.hibernate_dbt.entities.Course;
import org.hibernate_dbt.entities.Lecturer;

public class QueryData_3c {
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
       Session session = factory.getCurrentSession();
	       try {  
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();
	 
	           Lecturer l = new Lecturer("Christian", "Stutz", "01.01.1967", "Kernerweg 6");
	           Set<Course> courses = new HashSet<Course>();
	           courses.add(new Course("Interoperability", l));
	           
	           Student s1 = new Student("Sebastian", "Leer", "Nimmerweg 57", "13.12.1993", courses);
	           
	           /*
	           session.save(s1);
	          */
	            
 String hql = "SELECT p FROM Student p JOIN p.courses c  WHERE c.courseName = :idCategory";
 String hql2 = "From Student where studentID not in (SELECT p FROM Student p JOIN p.courses c WHERE c.courseName = :idCategory)";

	           // Create Query object.
	           Query<Student> query = session.createQuery(hql2);
	           query.setParameter("idCategory", "Interoperability");
	
	           // Execute query.
	           List<Student> students = query.getResultList();
	 
	           for (Student stds: students) {
	               System.out.println("Studenten: " + stds.getName());
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
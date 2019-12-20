package org.hibernate_dbt.query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Course;
import org.hibernate_dbt.entities.Student;

public class QueryData_3a {
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
       Session session = factory.getCurrentSession();
	       try {
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();

	           // Select all course objects.   // distinct not working (currently no solution)
	           String hql = "Select e from " + Course.class.getName() + " e "
	                   + " order by e.courseName ";
	           
	           String hql2 = "FROM Course";
	           
	           // take out one byID
	           //   Course course2 = new Course();
	           //    course2 = (Course) session.get(Course.class, 1);
	           //    System.out.println(course2.getCourseName());
	           
	           // Create Query object.
	           Query<Course> query = session.createQuery(hql);
	 
	           // Execute query.
	           List<Course> courses = query.getResultList();
	 
	           for (Course crs: courses) {
	               System.out.println("Course: " + crs.getCourseName());
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

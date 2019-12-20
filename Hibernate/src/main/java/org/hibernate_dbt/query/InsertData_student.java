package org.hibernate_dbt.query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Student;

public class InsertData_student {
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
       Session session = factory.getCurrentSession();
	       try {
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();
	 /*
	           Student s1 = new Student();
	           s1.setName("Garry");
	           s1.setSurname("Flotter");
	           s1.setAddress("Hogwarts");
	           s1.setBirthday("22.02.1994");
	           
	           Student s2 = new Student();
	           s2.setName("Stefan");
	           s2.setSurname("Gerber");
	           s2.setAddress("Völkermarkterstr. 8");
	           s2.setBirthday("12.02.1993");
	           
	           Student s3 = new Student();
	           s3.setName("Franz");
	           s3.setSurname("Fuger");
	           s3.setAddress("Hofergasse 7");
	           s3.setBirthday("02.03.1995");
	           
	           Student s4 = new Student();
	           s4.setName("Alessa");
	           s4.setSurname("Hedt");
	           s4.setAddress("Triumphgasse 10");
	           s4.setBirthday("03.12.1997");
	           */
	           Student s5 = new Student();
	           s5.setName("Manfred");
	           s5.setSurname("Rut");
	           s5.setAddress("Sirgasse 12");
	           s5.setBirthday("01.02.1990");
	           
	           session.save(s5);

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
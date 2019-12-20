package org.hibernate_dbt.query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate_dbt.Hibernate.HibernateUtils;
import org.hibernate_dbt.entities.Course;
import org.hibernate_dbt.entities.Lecturer;
import org.hibernate_dbt.entities.Student;

public class QueryData_3e {
	public static void main(String[] args) {
       SessionFactory factory = HibernateUtils.getSessionFactory();
       Session session = factory.getCurrentSession();
	       try {
	           // All the action with DB via Hibernate must be located in one transaction.
	           // Start Transaction.            
	           session.getTransaction().begin();
	 
	           //Delete all students with name Sara that attend the course “DBT”
	           Lecturer l = new Lecturer("Joel", "Ruk", "17.02.1985", "Stadlweg 2");
	           Set<Course> courses = new HashSet<Course>();
	           courses.add(new Course("DBT", l));
	           
	           Student s1 = new Student("Sara", "Leer", "Nimmerweg 57", "13.12.1993", courses);
	           Student s2 = new Student("Sara", "Vogell", "Limmgasse 7", "13.2.1998", courses);
	           // Student s3 = new Student("Heribert", "Lauter", "Ziehstraße 17", "13.2.1968", courses);
	   
	           // saving students to DB
	           session.save(s1);
	           session.save(s2);
	           // session.save(s3);
	          
	           // get Students Sara which attents course:
	           //String hql = "SELECT p FROM Student p JOIN p.courses c  WHERE p.name = :name AND c.courseName = :deleteCourse";
	           String hqlNeu = "From Student where studentID in"
	           		+ " (SELECT p FROM Student p JOIN p.courses c  WHERE p.name = :name AND c.courseName = :deleteCourse)";
	          
	           Query<Student> query = session.createQuery(hqlNeu);
	           query.setParameter("deleteCourse", "DBT");
	           query.setParameter("name", "Sara");
					
				// Execute query.
				List<Student> students = query.getResultList();
				
				for (Student stds: students) {
				    System.out.println("Studenten: " + stds.getName());
				    
				    Student a =session.find(Student.class, stds.getStudentID());
				     
			        // get all courses that this student attents
			        Query q = session.createNativeQuery("SELECT sc.courseID FROM STUDENT_COURSE sc "
										        		+ "JOIN Course c ON sc.courseID = c.courseID "
										        		+ "JOIN STUDENT_COURSE sc2 ON c.courseID = sc2.courseID "
										        		+ "WHERE sc2.studentID = ? "
										        		+ "GROUP BY sc.courseID");
			        q.setParameter(1, a.getStudentID());
			        List<Integer> courseIds = (List<Integer>)q.getResultList();
			          System.out.println(courseIds);
			        if(courseIds.size() > 0) {
			        	
			        // remove all associations for this student
			        q = session.createNativeQuery("DELETE FROM STUDENT_COURSE sc WHERE sc.studentID = ?");
			        q.setParameter(1, a.getStudentID());
			        q.executeUpdate();
			                 
			        // remove all students that 
			        q = session.createNativeQuery("DELETE FROM Student s WHERE s.studentID = ?");
			        //  q = session.createNativeQuery("DELETE FROM Student b WHERE b.studentID IN (:ids)");
			        //  q.setParameter("ids", courseIds);
			        q.setParameter(1, a.getStudentID());
			        q.executeUpdate();
			             
			     
			        //  session.remove(a);
			        }
			         
			        // actually there are problems with cascade deleting, courses and students were deleted because of cascase.type.all 
			        // i dont get it working wih type.PERSIT, type.MERGE etc..
				    /*
					// Delete object/s    
					Object obj = session.load(Student.class, stds.getStudentID());
					if(obj != null) {
						session.delete(obj);
					}	*/			
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
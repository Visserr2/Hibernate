package nl.thuis.tutorial.many2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.many2many.entity.Course;
import nl.thuis.tutorial.many2many.entity.Instructor;
import nl.thuis.tutorial.many2many.entity.InstructorDetail;
import nl.thuis.tutorial.many2many.entity.Review;
import nl.thuis.tutorial.many2many.entity.Student;

public class GetCoursesForStudentDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
				SessionFactory sessionFactory = new Configuration()
													.configure("hibernate-many2many.cfg.xml")
													.addAnnotatedClass(Instructor.class)
													.addAnnotatedClass(InstructorDetail.class)
													.addAnnotatedClass(Course.class)
													.addAnnotatedClass(Review.class)
													.addAnnotatedClass(Student.class)
													.buildSessionFactory();

				// get session
				Session session = sessionFactory.getCurrentSession();
				
				try {
					
					// Begin Transaction
					session.beginTransaction();
					
					// Get student
					System.out.println("Get student");
					int id = 1;
					Student student = session.get(Student.class, id);
					
					// Viewing student and courses
					System.out.println(student);
					System.out.println(student.getCourses());
					
					// Commit transaction
					session.getTransaction().commit();
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					// close session and session factory. Prevent memory leaks
					session.close();
					sessionFactory.close();
				}
	}
}

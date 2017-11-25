package nl.thuis.tutorial.one2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.one2many.entity.Course;
import nl.thuis.tutorial.one2many.entity.Instructor;
import nl.thuis.tutorial.one2many.entity.InstructorDetail;

public class CreateCoursesOneToManyDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
				SessionFactory sessionFactory = new Configuration()
													.configure("hibernate-one2many.cfg.xml")
													.addAnnotatedClass(Instructor.class)
													.addAnnotatedClass(InstructorDetail.class)
													.addAnnotatedClass(Course.class)
													.buildSessionFactory();

				// get session
				Session session = sessionFactory.getCurrentSession();
				
				try {
					
					// Begin Transaction
					session.beginTransaction();
					
					// Get instructor
					int id = 1;
					Instructor instructor = session.get(Instructor.class, id);
					
					// Create course
					Course course1 = new Course("The first course of the app");
					Course course2 = new Course("The second course of the app");
					
					// Adding course to instructor
					instructor.addCourse(course1);
					instructor.addCourse(course2);
					
					// Save courses to database
					session.save(course1);
					session.save(course2);
					
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

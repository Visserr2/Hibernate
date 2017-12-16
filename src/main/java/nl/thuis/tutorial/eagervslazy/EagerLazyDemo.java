package nl.thuis.tutorial.eagervslazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.eagervslazy.entity.Course;
import nl.thuis.tutorial.eagervslazy.entity.Instructor;
import nl.thuis.tutorial.eagervslazy.entity.InstructorDetail;


public class EagerLazyDemo {

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
					System.out.println("Getting data from database");
					Instructor instructor = session.get(Instructor.class, id);
					System.out.println("Fetched data from database");
					
					// Show courses. When fetch type is lazy this will also call database to retrieve courses
					System.out.println(instructor.getCourseList());
					
					// Commit transaction
					session.getTransaction().commit();
					
					// If the courses get fetched after the session is closed then an exception will be thrown
					// There are three ways for lazy fetching to retrieve all data
					// # Option 1: Retrieve all the necessary data before session is closed (The getters for the lazy attributes)
					// # Option 2: Retrieve all the necessary data with HQL within the session (@See FetchJoin Demo)
					// # Option 3: Retrieve necessary data in another session and standalone-query (get all courses from constructor x)
					
					// All ToOne relationships are eager by default
					// All ToMany relationships are lazy by default
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					// close session and session factory. Prevent memory leaks
					session.close();
					sessionFactory.close();
				}
	}
}

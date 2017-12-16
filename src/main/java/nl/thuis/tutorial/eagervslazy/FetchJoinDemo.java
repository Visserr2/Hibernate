package nl.thuis.tutorial.eagervslazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import nl.thuis.tutorial.eagervslazy.entity.Course;
import nl.thuis.tutorial.eagervslazy.entity.Instructor;
import nl.thuis.tutorial.eagervslazy.entity.InstructorDetail;

public class FetchJoinDemo {

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
					
					// start a transaction
					session.beginTransaction();
					
					// option 2: Hibernate query with HQL
					
					// get the instructor from db
					int theId = 1;

					Query<Instructor> query = 
							session.createQuery("select i from Instructor i "
											+ "JOIN FETCH i.courseList "
											+ "where i.id=:theInstructorId", 
									Instructor.class);

					// set parameter on query
					query.setParameter("theInstructorId", theId);
					
					// execute query and get instructor
					Instructor tempInstructor = query.getSingleResult();
					
					System.out.println(" Instructor: " + tempInstructor);	
					
					// commit transaction
					session.getTransaction().commit();
					
					// close the session
					session.close();
					
					System.out.println("\n The session is now closed!\n");
					
					// get courses for the instructor
					System.out.println(" Courses: " + tempInstructor.getCourseList());
					
					System.out.println(" Done!");
					
					// If the courses get fetched after the session is closed then an exception will be thrown
					// There are three ways for lazy fetching to retrieve all data
					// # Option 1: Retrieve all the necessary data before session is closed (The getters for the lazy attributes)
					// # Option 2: Retrieve all the necessary data with HQL within the session (@See FetchJoin Demo)
					// # Option 3: Retrieve necessary data in another session and standalone-query (get all courses from constructor x)
					
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					// close session and session factory. Prevent memory leaks
					session.close();
					sessionFactory.close();
				}
	}
}

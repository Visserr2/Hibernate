package nl.thuis.tutorial.one2one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.one2one.entity.Instructor;
import nl.thuis.tutorial.one2one.entity.InstructorDetail;

public class ReadOneToOneBidirectionalDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
				SessionFactory sessionFactory = new Configuration()
													.configure("hibernate-one2one.cfg.xml")
													.addAnnotatedClass(Instructor.class)
													.addAnnotatedClass(InstructorDetail.class)
													.buildSessionFactory();

				// get session
				Session session = sessionFactory.getCurrentSession();
				
				try {					
					// Begin Transaction
					session.beginTransaction();
					
					// get instructor - null if object cannot be found
					int id = 2;
					InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
					
					if(instructorDetail != null) {
						System.out.println(instructorDetail);
						System.out.println(instructorDetail.getInstructor());
					}
					
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

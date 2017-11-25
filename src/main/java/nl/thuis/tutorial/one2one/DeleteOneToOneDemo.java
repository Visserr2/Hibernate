package nl.thuis.tutorial.one2one;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.one2one.entity.Instructor;
import nl.thuis.tutorial.one2one.entity.InstructorDetail;

public class DeleteOneToOneDemo {

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
					int id = 1;
					Instructor instructor = session.get(Instructor.class, id);
					
					// Delete instructor and instructordetail because of cascade all
					if(instructor != null) {
						session.delete(instructor);
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

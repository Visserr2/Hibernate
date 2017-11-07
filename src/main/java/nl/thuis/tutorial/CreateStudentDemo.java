package nl.thuis.tutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();

		// get session
		Session session = sessionFactory.getCurrentSession();
		
		try {		
			// Create Database Object
			Student student = new Student("Henk", "De Groot", "Test@Test.nl");
			
			// Begin Transaction
			session.beginTransaction();
			
			// Save Database Object
			session.save(student);
			
			// Commit transaction
			session.getTransaction().commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// close session and session factory
			session.close();
			sessionFactory.close();
		}
	}

}

package nl.thuis.tutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.entity.Student;

public class ReadStudentDemo {

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
			Student studentTwo = new Student("Pieter", "De Klein", "Testing@Test.nl");

			// Begin Transaction
			session.beginTransaction();
			
			// Save Database Object
			session.save(student);
			session.save(studentTwo);
			
			// Commit transaction
			session.getTransaction().commit();
			
			session.close();
			
			// Get new Session
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			// read from database
			Student tempStudent = session.get(Student.class, studentTwo.getId());
			System.out.println(tempStudent);
			
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

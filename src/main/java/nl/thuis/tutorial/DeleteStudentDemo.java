package nl.thuis.tutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();

		// get session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			int studentId = 5;
			// Begin Transaction
			session.beginTransaction();
			
			// read from database
			System.out.println("Get student from database");
			Student tempStudent = session.get(Student.class, studentId);
			System.out.println("Fetched student: " + tempStudent);
			
			// Delete one record
			System.out.println("Delete Student");
			session.delete(tempStudent);
			
			session.getTransaction().commit();
			
			session.close();
			
			// Get new session
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			// delete multiple records 
			System.out.println("Deleting records");
			session.createQuery("DELETE Student s WHERE s.firstName='Scooby' ").executeUpdate();
			
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

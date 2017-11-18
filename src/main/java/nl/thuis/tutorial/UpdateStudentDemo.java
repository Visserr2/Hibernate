package nl.thuis.tutorial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();

		// get session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			int studentId = 1;
			// Begin Transaction
			session.beginTransaction();
			
			// read from database
			System.out.println("Get student from database");
			Student tempStudent = session.get(Student.class, studentId);
			System.out.println("Fetched student: " + tempStudent);
			
			// Update name
			System.out.println("Updating Student");
			tempStudent.setFirstName("Scooby");
			
			session.getTransaction().commit();
			
			session.close();
			
			// Get new session
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			// Update all emails in database
			System.out.println("Updating emails of students");
			session.createQuery("UPDATE Student s SET s.email='foo@gmail.com' ").executeUpdate();
			
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

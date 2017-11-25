package nl.thuis.tutorial;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
		SessionFactory sessionFactory = new Configuration()
											.configure()
											.addAnnotatedClass(Student.class)
											.buildSessionFactory();

		// get session
		Session session = sessionFactory.getCurrentSession();
		
		try {		
			// Begin Transaction
			session.beginTransaction();
	
			// Get list of students
			List<Student> students = session.createQuery("from Student", Student.class).getResultList();
			
			// Show list
			displayStudentList(students);
			
			// Get list of student with last name De Groot
			students = session.createQuery("FROM Student s WHERE s.lastName = 'De Groot' ", Student.class).getResultList();
			
			// Show List
			displayStudentList(students);
			
			// Get list of student with last name De Groot or first name pieter
			students = session.createQuery("FROM Student s WHERE s.lastName = 'De Groot' OR s.firstName = 'Pieter' ", Student.class).getResultList();
			
			// Show List
			displayStudentList(students);
			
			// Get list of student with email that ends on Test.nl
			students = session.createQuery("FROM Student s WHERE s.email like '%Test.nl' ", Student.class).getResultList();
			
			// Show List
			displayStudentList(students);
			
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
	
	private static void displayStudentList(List<Student> list) {
		for(Student student: list) {
			System.out.println(student);
		}
	}

}

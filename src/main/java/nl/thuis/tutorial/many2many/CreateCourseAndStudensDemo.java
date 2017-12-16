package nl.thuis.tutorial.many2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.many2many.entity.Course;
import nl.thuis.tutorial.many2many.entity.Instructor;
import nl.thuis.tutorial.many2many.entity.InstructorDetail;
import nl.thuis.tutorial.many2many.entity.Review;
import nl.thuis.tutorial.many2many.entity.Student;

public class CreateCourseAndStudensDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
				SessionFactory sessionFactory = new Configuration()
													.configure("hibernate-many2many.cfg.xml")
													.addAnnotatedClass(Instructor.class)
													.addAnnotatedClass(InstructorDetail.class)
													.addAnnotatedClass(Course.class)
													.addAnnotatedClass(Review.class)
													.addAnnotatedClass(Student.class)
													.buildSessionFactory();

				// get session
				Session session = sessionFactory.getCurrentSession();
				
				try {
					
					// Begin Transaction
					session.beginTransaction();
					
					// Create new Course
					System.out.println("Create Course");
					Course course = new Course("This is a new course with reviews");
					
					// Save course.
					System.out.println("Save course");
					session.save(course);
					
					// Create students
					System.out.println("Create students");
					Student student1 = new Student("Ronald", "Visser", "Ronald.visser@hotmail.nl");
					Student student2 = new Student("Dennis", "Kamstra", "Dennis.Kamstra@hotmail.nl");
					
					// Add students to course
					System.out.println("Add students to course");
					course.addStudent(student1);
					course.addStudent(student2);
					
					// Save students
					System.out.println("Save students");
					session.save(student1);
					session.save(student2);				
					System.out.println("Saved students in Course " + course.getStudents());
					
					// Now both students and courses are saved in database. 
					// Also are the ids of the connected students and courses saved in the join table after the commit
					
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

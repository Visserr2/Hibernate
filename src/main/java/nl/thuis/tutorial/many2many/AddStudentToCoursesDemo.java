package nl.thuis.tutorial.many2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.many2many.entity.Course;
import nl.thuis.tutorial.many2many.entity.Instructor;
import nl.thuis.tutorial.many2many.entity.InstructorDetail;
import nl.thuis.tutorial.many2many.entity.Review;
import nl.thuis.tutorial.many2many.entity.Student;

public class AddStudentToCoursesDemo {

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
					
					// Get student
					System.out.println("Get student");
					int id = 1;
					Student student = session.get(Student.class, id);
					
					// Create more course.
					System.out.println("Creating courses");
					Course course1 = new Course("Java Course!");
					Course course2 = new Course("Hibernate Course!");
					
					// Add Courses to student
					// It doesn't matter if you add the student to the course or the course to the student
					System.out.println("Adding courses to student");
					student.addCourse(course1);
					student.addCourse(course2);
					
					// Save Courses
					System.out.println("Save courses");
					session.save(course1);
					session.save(course2);				
					System.out.println("Saved Courses : " + student.getCourses() );
					
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

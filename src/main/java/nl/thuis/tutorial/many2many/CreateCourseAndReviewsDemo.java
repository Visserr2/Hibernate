package nl.thuis.tutorial.many2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.one2many.uni.entity.Course;
import nl.thuis.tutorial.one2many.uni.entity.Instructor;
import nl.thuis.tutorial.one2many.uni.entity.InstructorDetail;
import nl.thuis.tutorial.one2many.uni.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
				SessionFactory sessionFactory = new Configuration()
													.configure("hibernate-one2many-uni.cfg.xml")
													.addAnnotatedClass(Instructor.class)
													.addAnnotatedClass(InstructorDetail.class)
													.addAnnotatedClass(Course.class)
													.addAnnotatedClass(Review.class)
													.buildSessionFactory();

				// get session
				Session session = sessionFactory.getCurrentSession();
				
				try {
					
					// Begin Transaction
					session.beginTransaction();
					
					// Create new Course
					System.out.println("Create Course");
					Course course = new Course("This is a new course with reviews");
					
					// Add reviews
					System.out.println("Add reviews");
					course.addReview(new Review("This is a good course!"));
					course.addReview(new Review("This is a okay course!"));
					course.addReview(new Review("This is a bad course!"));

					// Save course. Because of "cascade all" the reviews are also saved 
					System.out.println("Save course");
					session.save(course);
					
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

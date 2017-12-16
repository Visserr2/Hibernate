package nl.thuis.tutorial.many2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.one2many.uni.entity.Course;
import nl.thuis.tutorial.one2many.uni.entity.Instructor;
import nl.thuis.tutorial.one2many.uni.entity.InstructorDetail;
import nl.thuis.tutorial.one2many.uni.entity.Review;

public class GetCourseAndReviewsDemo {

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
					
					// Get the course
					int id = 10;
					Course course = session.get(Course.class, id);
					
					// Print the course
					System.out.println(course);
					
					// Print the reviews. Gets the reviews with hibernate query because it is lazy fetching
					System.out.println(course.getReviews());
					
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

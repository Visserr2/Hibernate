package nl.thuis.tutorial.one2many.bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import nl.thuis.tutorial.one2many.bi.entity.Course;
import nl.thuis.tutorial.one2many.bi.entity.Instructor;
import nl.thuis.tutorial.one2many.bi.entity.InstructorDetail;

public class CreateInstructorOneToManyDemo {

	public static void main(String[] args) {
		// Creating sessionfactory
				SessionFactory sessionFactory = new Configuration()
													.configure("hibernate-one2many.cfg.xml")
													.addAnnotatedClass(Instructor.class)
													.addAnnotatedClass(InstructorDetail.class)
													.addAnnotatedClass(Course.class)
													.buildSessionFactory();

				// get session
				Session session = sessionFactory.getCurrentSession();
				
				try {
					// Creating objects
					Instructor instructor = new Instructor("Ronald", "Visser", "Ronald.Visser@hotmail.nl");
					InstructorDetail instructorDetail = new InstructorDetail("https://youtube.com", "Youtube");
					// Associating objects
					instructor.setInstructorDetail(instructorDetail);
					
					// Begin Transaction
					session.beginTransaction();
					
					// Save instructor - this also saves InstructorDetail because of cascade all
					session.save(instructor);					
					
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

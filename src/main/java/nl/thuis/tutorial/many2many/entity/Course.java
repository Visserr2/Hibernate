package nl.thuis.tutorial.many2many.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	// CascadeType.PERSIST : means that save() or persist() operations cascade to related entities.
    // CascadeType.MERGE : means that related entities are merged when the owning entity is merged.
    // CascadeType.REFRESH : does the same thing for the refresh() operation.
    // CascadeType.REMOVE : removes all related entities association with this setting when the owning entity is deleted.
    // CascadeType.DETACH : detaches all related entities if a “manual detach” occurs.
    // CascadeType.ALL : is shorthand for all of the above cascade operations.
	// Never use cascade type remove in a ManytoOne or OneToMany relationship
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	// refer to foreign key within the table
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	// This relationship is unidirectional because Review-object doesn't hold a reference to a Course-Object. 
	// The consequence is that all cascading can only be done one way. Also it isn't possible to find a Course through a comment-object
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Review> reviews;
	
	// This is a ManyToMany relationship
	@ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(	name="course_student", // name of join-table
				joinColumns=@JoinColumn(name="course_id"), 
				inverseJoinColumns=@JoinColumn(name="student_id")) // inverse is opposite of the relationship
	private List<Student> students;
	
	public Course() {}
	
	public Course(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	// Convenience method to add review
	public void addReview(Review review) {
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		reviews.add(review);
	}
	
	// Convenience method to add student
		public void addStudent(Student student) {
			if(students == null) {
				students = new ArrayList<>();
			}
			students.add(student);
		}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
	
}

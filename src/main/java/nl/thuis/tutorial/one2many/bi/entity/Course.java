package nl.thuis.tutorial.one2many.bi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public Course() {}
	
	public Course(String title) {
		super();
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
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}
	
}

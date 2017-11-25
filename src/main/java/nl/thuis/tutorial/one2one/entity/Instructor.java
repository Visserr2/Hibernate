package nl.thuis.tutorial.one2one.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor")
public class Instructor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String email;
	
	// create relationship with InstructorDetail
	// CascadeType.PERSIST : means that save() or persist() operations cascade to related entities.
    // CascadeType.MERGE : means that related entities are merged when the owning entity is merged.
    // CascadeType.REFRESH : does the same thing for the refresh() operation.
    // CascadeType.REMOVE : removes all related entities association with this setting when the owning entity is deleted.
    // CascadeType.DETACH : detaches all related entities if a “manual detach” occurs.
    // CascadeType.ALL : is shorthand for all of the above cascade operations.
	// Use multiple types: cascade= {CascadeType.PERSIST, CascadeType.MERGE, etc}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id") // refer to foreign key
	private InstructorDetail instructorDetail;
	
	public Instructor() {}

	public Instructor(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}
	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	};	
}

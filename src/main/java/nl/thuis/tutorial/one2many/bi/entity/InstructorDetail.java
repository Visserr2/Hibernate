package nl.thuis.tutorial.one2many.bi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="youtube_channel")
	private String youtubeChannel;
	private String hobby;	
	// Make the OneToOne relationship bidirectional. The instructor is loaded in the instructor_detail too now.
	// Refers to foreign key of the other class
	// CascadeType.PERSIST : means that save() or persist() operations cascade to related entities.
    // CascadeType.MERGE : means that related entities are merged when the owning entity is merged.
    // CascadeType.REFRESH : does the same thing for the refresh() operation.
    // CascadeType.REMOVE : removes all related entities association with this setting when the owning entity is deleted.
    // CascadeType.DETACH : detaches all related entities if a “manual detach” occurs.
    // CascadeType.ALL : is shorthand for all of the above cascade operations.
	// Use multiple types: cascade= {CascadeType.PERSIST, CascadeType.MERGE, etc}
	@OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL)
	private Instructor instructor;
	
	public InstructorDetail() {}
	
	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYoutubeChannel() {
		return youtubeChannel;
	}
	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		return "InstructorDetail [id=" + id + ", youtubeChannel=" + youtubeChannel + ", hobby=" + hobby + "]";
	}	
}

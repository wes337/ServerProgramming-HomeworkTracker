package fi.haagahelia.wesleymoses.homeworktracker.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fi.haagahelia.wesleymoses.homeworktracker.domain.Assignment;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long courseid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	private List<Assignment> assignments;
	
	public Course() {}
	
	public Course(String name) {
		super();
		this.name = name;
	}

	public Long getCourseid() {
		return courseid;
	}

	public void setCourseid(Long courseid) {
		this.courseid = courseid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
}

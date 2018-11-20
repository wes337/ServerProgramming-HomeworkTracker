package fi.haagahelia.wesleymoses.homeworktracker.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fi.haagahelia.wesleymoses.homeworktracker.domain.Course;


@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String assignmentName;
    private String dueDate;
    private String description;
    private Boolean completed;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "courseid")
    private Course course;
    
    public Assignment() {}

	public Assignment(String assignmentName, String dueDate, String description, Course course, Boolean completed) {
		super();
		this.assignmentName = assignmentName;
		this.dueDate = dueDate;
		this.description = description;
		this.course = course;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

}

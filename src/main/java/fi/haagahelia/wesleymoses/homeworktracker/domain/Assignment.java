package fi.haagahelia.wesleymoses.homeworktracker.domain;

import javax.persistence.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fi.haagahelia.wesleymoses.homeworktracker.domain.Course;


@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String assignmentName;
    private LocalDate dueDate;
    private String description;
    private Boolean completed = false;
    private LocalDate completedDate = LocalDate.now();
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "courseid")
    private Course course;

    public Assignment() {}

	public Assignment(String assignmentName, LocalDate dueDate, String description, Course course, Boolean completed, LocalDate completedDate) {
		super();
		this.assignmentName = assignmentName;
		this.dueDate = dueDate;
		this.description = description;
		this.course = course;
		this.completed = completed;
		this.completedDate = completedDate;
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

	public LocalDate getDueDate() { return dueDate;
	}

	public void setDueDate(String dueDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	this.dueDate = LocalDate.parse(dueDate, formatter);
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

	public void setCompleted(Boolean completed) { this.completed = completed; }

	public LocalDate getCompletedDate() { return completedDate; }

	public void setCompletedDate(String completedDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	this.completedDate = LocalDate.parse(completedDate, formatter);;
    }

	public void giveCompletedDate() {
    	Date compdate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
    	setCompletedDate(ft.format(compdate));
	}

}

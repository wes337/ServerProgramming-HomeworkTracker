package fi.haagahelia.wesleymoses.homeworktracker.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

    List<Assignment> findByAssignmentName(String assignmentName);
    List<Assignment> findByCompletedTrue();
    List<Assignment> findByCompletedFalse();
    List<Assignment> findByCourse(Course course);
    List<Assignment> findByCourseAndCompletedFalse(Course course);
    List<Assignment> findByCourseAndCompletedTrue(Course course);
    
}
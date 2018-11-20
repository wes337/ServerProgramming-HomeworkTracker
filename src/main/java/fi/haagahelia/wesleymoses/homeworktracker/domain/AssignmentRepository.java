package fi.haagahelia.wesleymoses.homeworktracker.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

    List<Assignment> findByAssignmentName(String assignmentName);
    
}
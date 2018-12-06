package fi.haagahelia.wesleymoses.homeworktracker.domain;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {

    List<Assignment> findByAssignmentName(String assignmentName);
    List<Assignment> findByCompletedTrue();
    List<Assignment> findByCompletedFalse();
    List<Assignment> findByCourse(Course course);
    List<Assignment> findByCourseAndCompletedFalse(Course course);
    List<Assignment> findByCourseAndCompletedTrue(Course course);
    @Query(value="SELECT * FROM ASSIGNMENT WHERE COMPLETED = FALSE AND DUE_DATE >= CURDATE() ORDER BY DUE_DATE DESC", nativeQuery = true)
    List<Assignment> findAllByOrderByDueDateDesc();

}
package fi.haagahelia.wesleymoses.homeworktracker.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByName(String name);
    
}
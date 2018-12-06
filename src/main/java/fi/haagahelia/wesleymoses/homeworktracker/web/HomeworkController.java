package fi.haagahelia.wesleymoses.homeworktracker.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fi.haagahelia.wesleymoses.homeworktracker.domain.Assignment;
import fi.haagahelia.wesleymoses.homeworktracker.domain.AssignmentRepository;
import fi.haagahelia.wesleymoses.homeworktracker.domain.CourseRepository;
import fi.haagahelia.wesleymoses.homeworktracker.domain.Course;

@Controller
public class HomeworkController {
	
	@Autowired
	private AssignmentRepository repository; 

	@Autowired
	private CourseRepository crepository; 
	
	
	@RequestMapping(value="/index")
	public String home() {
		return "index";
	}
	
    // Login
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	


    // Show all assignments
    @RequestMapping(value="/")
    public String indexList(Model model) {
        model.addAttribute("assignments", repository.findByCompletedFalse());
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("course", new Course());
        return "assignmentlist";
    }

	// Show all assignments
    @RequestMapping(value="/assignmentlist")
    public String assignmentList(Model model) {	
        model.addAttribute("assignments", repository.findByCompletedFalse());
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("course", new Course());
        return "assignmentlist";
    }

    // Sort assignments
    @RequestMapping(value="/sort")
    public String assignmentListSort(Model model) {
        model.addAttribute("assignments", repository.findAllByOrderByDueDateDesc());
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("course", new Course());
        return "assignmentlist";
    }

    // Show all completed assignments
    @RequestMapping(value="/completedassignments")
    public String completedAssignmentList(Model model) {
        model.addAttribute("assignments", repository.findByCompletedTrue());
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("course", new Course());
        return "completedassignments";
    }
  
	// RESTful service to get all assignments
    @RequestMapping(value="/assignments", method = RequestMethod.GET)
    public @ResponseBody List<Assignment> assignmentListRest() {	
        return (List<Assignment>) repository.findAll();
    }    

	// RESTful service to get assignment by id
    @RequestMapping(value="/assignment/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Assignment> findStudentRest(@PathVariable("id") Long assignmentId) {	
    	return repository.findById(assignmentId);
    }       
    
    // Add new assignment
    @RequestMapping(value = "/add")
    public String addAssignment(Model model){
    	model.addAttribute("assignment", new Assignment());
    	model.addAttribute("courses", crepository.findAll());
        return "addassignment";
    }

    // Add new Course
    @RequestMapping(value = "/addcourse")
    public  String addCourse(Model model){
	    model.addAttribute("course", new Course());
	    return "addcourse";
    }
    
    // Save new assignment
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Assignment assignment){
        repository.save(assignment);
        return "redirect:assignmentlist";
    }

    // Save new course
    @RequestMapping(value = "/savecourse", method = RequestMethod.POST)
    public String saveCourse(Course course) {
	    crepository.save(course);
	    return "redirect:assignmentlist";
    }

    // Delete assignment
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteAssignment(@PathVariable("id") Long assignmentId, Model model) {
    	repository.deleteById(assignmentId);
        return "redirect:../assignmentlist";
    }

    // Delete course
    @RequestMapping(value = "/deletecourse", method = RequestMethod.POST)
    public String deleteCourse(@RequestParam("courseid") @PathVariable("id") Long courseId, Model model) {
        crepository.deleteById(courseId);
        return "redirect:../assignmentlist";
    }

    // Filter by Course
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filterCourse(@RequestParam("courseid") @PathVariable("id") Long courseId, Model model) {
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("course", new Course());
        model.addAttribute("assignments", repository.findByCourseAndCompletedFalse(crepository.findById(courseId).get()));
        model.addAttribute("thiscourse", crepository.findById(courseId).get());
        return "filteredlist";
    }

    // Show all filtered completed assignments (POST)
    @RequestMapping(value="/filter/completedassignments", method = RequestMethod.POST)
    public String filteredcompletedAssignmentListPost(@RequestParam("courseid") @PathVariable("id") Long courseId, Model model) {
        model.addAttribute("assignments", repository.findByCourseAndCompletedTrue(crepository.findById(courseId).get()));
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("course", new Course());
        model.addAttribute("thiscourse", crepository.findById(courseId).get());
        return "filteredcompletedassignments";
    }

    // Show all filtered completed assignments
    @RequestMapping(value="/filter/completedassignments/{id}", method = RequestMethod.GET)
    public String filteredcompletedAssignmentList(@PathVariable("id") Long courseId, Model model) {
        model.addAttribute("assignments", repository.findByCourseAndCompletedTrue(crepository.findById(courseId).get()));
        model.addAttribute("courses", crepository.findAll());
        model.addAttribute("course", new Course());
        model.addAttribute("thiscourse", crepository.findById(courseId).get());
        return "filteredcompletedassignments";
    }

    // Mark assignment as complete
    @RequestMapping(value = "/markasdone/{id}", method = RequestMethod.GET)
    public String doneAssignment(@PathVariable("id") Long assignmentId, Model model) {
        Assignment a = repository.findById(assignmentId).get();
        a.setCompletedDate(LocalDate.now());
        a.setCompleted(true);
        repository.save(a);
        return "redirect:../assignmentlist";
    }

    // Mark assignment as incomplete
    @RequestMapping(value = "/incomplete/{id}", method = RequestMethod.GET)
    public String incompleteAssignment(@PathVariable("id") Long assignmentId, Model model) {
        Assignment a = repository.findById(assignmentId).get();
        a.setCompleted(false);
        repository.save(a);
        return "redirect:../completedassignments";
    }

    // Edit Assignment
    @RequestMapping(value = "/edit/{id}")
    public String addAssignment(@PathVariable("id") Long assignmentId, Model model) {
        model.addAttribute("assignment", repository.findById(assignmentId));
        model.addAttribute("courses", crepository.findAll());
        return "editassignment";
    }

}
	
package fi.haagahelia.wesleymoses.homeworktracker.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.wesleymoses.homeworktracker.domain.Assignment;
import fi.haagahelia.wesleymoses.homeworktracker.domain.AssignmentRepository;
import fi.haagahelia.wesleymoses.homeworktracker.domain.CourseRepository;

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
	
	// Show all assignments
    @RequestMapping(value="/assignmentlist")
    public String assignmentList(Model model) {	
        model.addAttribute("assignments", repository.findAll());
        return "assignmentlist";
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
    
    // Save new assignment
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Assignment assignment){
        repository.save(assignment);
        return "redirect:assignmentlist";
    }    

    // Delete assignment
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteAssignment(@PathVariable("id") Long assignmentId, Model model) {
    	repository.deleteById(assignmentId);
        return "redirect:../assignmentlist";
    }  

}
	
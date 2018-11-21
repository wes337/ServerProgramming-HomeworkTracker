package fi.haagahelia.wesleymoses.homeworktracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.wesleymoses.homeworktracker.domain.Assignment;
import fi.haagahelia.wesleymoses.homeworktracker.domain.AssignmentRepository;
import fi.haagahelia.wesleymoses.homeworktracker.domain.Course;
import fi.haagahelia.wesleymoses.homeworktracker.domain.CourseRepository;

@SpringBootApplication
public class HomeworktrackerApplication {

	private static final Logger log = LoggerFactory.getLogger(HomeworktrackerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(HomeworktrackerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(AssignmentRepository arepository, CourseRepository crepository) {
		return (args) -> {
			log.info("Create some test courses");
			crepository.save(new Course("IT"));
			crepository.save(new Course("Business"));
			crepository.save(new Course("Law"));
			crepository.save(new Course("Communications"));
			
			arepository.save(new Assignment("IT Project", "2012-12-25", "An IT course project.", crepository.findByName("IT").get(0), true));
			arepository.save(new Assignment("Business Assignment", "2018-11-25", "A business course assignment.", crepository.findByName("Business").get(0), false));
			arepository.save(new Assignment("Communications Paper", "2019-07-20", "A research paper.", crepository.findByName("Communications").get(0), false));
			arepository.save(new Assignment("Law Journal", "2018-12-11", "A law journal.", crepository.findByName("Law").get(0), false));
			arepository.save(new Assignment("Test Assignment", "2020-10-30", "A test assignment.", crepository.findByName("IT").get(0), false));
			arepository.save(new Assignment("Test Assignment", "2018-10-30", "A test assignment.", crepository.findByName("Law").get(0), false));
			arepository.save(new Assignment("Test Assignment", "2015-10-30", "A test assignment.", crepository.findByName("Business").get(0), false));

			log.info("fetch all assignments");
			for (Assignment assignment : arepository.findAll()) {
				log.info(assignment.toString());
			}

		};
	}
}

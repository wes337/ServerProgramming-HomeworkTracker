package fi.haagahelia.wesleymoses.homeworktracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.wesleymoses.homeworktracker.domain.Assignment;
import fi.haagahelia.wesleymoses.homeworktracker.domain.AssignmentRepository;
import fi.haagahelia.wesleymoses.homeworktracker.domain.Course;
import fi.haagahelia.wesleymoses.homeworktracker.domain.CourseRepository;
import fi.haagahelia.wesleymoses.homeworktracker.domain.User;
import fi.haagahelia.wesleymoses.homeworktracker.domain.UserRepository;

@SpringBootApplication
public class HomeworktrackerApplication {

	private static final Logger log = LoggerFactory.getLogger(HomeworktrackerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(HomeworktrackerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(AssignmentRepository arepository, CourseRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Create some test courses");
			crepository.save(new Course("IT"));
			crepository.save(new Course("Business"));
			crepository.save(new Course("Law"));
			crepository.save(new Course("Communications"));
			
			arepository.save(new Assignment("IT Project", LocalDate.of(2012,Month.DECEMBER,25), "An IT course project.", crepository.findByName("IT").get(0), true, LocalDate.of(2012,Month.DECEMBER,20)));
			arepository.save(new Assignment("Business Assignment", LocalDate.of(2018,Month.DECEMBER,25), "A business course assignment.", crepository.findByName("Business").get(0), false, null));
			arepository.save(new Assignment("Communications Paper", LocalDate.of(2019,Month.JULY,20), "A research paper.", crepository.findByName("Communications").get(0), false, null));
			arepository.save(new Assignment("Law Journal", LocalDate.of(2018,Month.DECEMBER,11), "A law journal.", crepository.findByName("Law").get(0), false, null));
			arepository.save(new Assignment("Test Assignment", LocalDate.of(2020,Month.OCTOBER,30), "A test assignment.", crepository.findByName("IT").get(0), false, null));
			arepository.save(new Assignment("Test Assignment", LocalDate.of(2018,Month.OCTOBER,30), "A test assignment.", crepository.findByName("Law").get(0), false, null));
			arepository.save(new Assignment("Test Assignment", LocalDate.of(2015,Month.OCTOBER,30), "A test assignment.", crepository.findByName("Business").get(0), false, null));
            		arepository.save(new Assignment("Some Project", LocalDate.of(2016,Month.AUGUST,30), "Some project that was due a long time ago.", crepository.findByName("Communications").get(0), false, null));

            // Create users: admin/admin user/user
 			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
 			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
 			urepository.save(user1);
 			urepository.save(user2);
            
            log.info("fetch all assignments");
			for (Assignment assignment : arepository.findAll()) {
				log.info(assignment.toString());
			}

		};
	}
}

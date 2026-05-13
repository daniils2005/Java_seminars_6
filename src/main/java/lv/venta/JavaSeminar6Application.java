package lv.venta;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Professor;
import lv.venta.model.Student;
import lv.venta.model.enums.Degree;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IProfessorRepo;
import lv.venta.repo.IStudentRepo;

@SpringBootApplication
public class JavaSeminar6Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaSeminar6Application.class, args);
	}

	@Bean //automatiski izpildis so funkciju startejot sistemu
	public CommandLineRunner testRepo(IStudentRepo studRepo, IProfessorRepo profRepo,
			ICourseRepo courRepo, IGradeRepo grRepo) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Student stud1 = new Student("Rendijs", "Serna");
				Student stud2 = new Student("Janis", "Berzins");
				studRepo.saveAll(Arrays.asList(stud1,stud2));
				
				Professor prof1 = new Professor("Vairis", "Caune", Degree.unknown);
				Professor prof2 = new Professor("Galina", "Hilkevica",Degree.phd);
				profRepo.saveAll(Arrays.asList(prof1, prof2));
				
				Course course1 = new Course("Algoritmu teorija", 3, prof1);
				Course course2 = new Course("Matematiska analize", 6, prof2);
				courRepo.saveAll(Arrays.asList(course1, course2));
				
				Grade gr1 = new Grade(10, stud1, course1);//Rendijs nopelnija 10 Algoritmi
				Grade gr2 = new Grade(7, stud1, course2);//Rendijs nopelnija 7 Matematika
				
				Grade gr3 = new Grade(4, stud2, course1);//Janis nopelnija 4 Algoritmi
				Grade gr4 = new Grade(5, stud2, course2);//Janis nopelnija 5 Matematika
				grRepo.saveAll(Arrays.asList(gr1,gr2,gr3,gr4));
				
			}
		};
		
	}
	
}

package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.model.enums.Degree;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "ProfessorTable")
@Entity
public class Professor extends Person{
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Pid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long pid;
	
		
	@NotNull
	@Column(name = "Degree")
	@Enumerated(EnumType.STRING)
	private Degree degree;

	@ManyToMany(mappedBy = "professors")
	@ToString.Exclude
	private Collection<Course> courses = new ArrayList<Course>();
	
	
	public Professor(String name, String surname, Degree degree) {
		super(name, surname);
		setDegree(degree);
	}
	
	//manytomany
	public void addCourse(Course course) throws Exception {
		if(course == null) {
			throw new Exception("Nav pareizi ievades dati");
		}
		if(courses.contains(course)) {
			throw new Exception(course.getTitle() + " jau esksiste profesira oasniegtaja kursus saraksta");
		}
		courses.add(course);
	}
	
	public void removeCourse(Course course) throws Exception {
		if(course == null) {
			throw new Exception("Nav pareizi ievades dati");
		}
		if(!courses.contains(course)) {
			throw new Exception("Neavar dzest, jo " + course.getTitle() + " neeksiste profesira oasniegtaja kursus saraksta");
		}
		courses.remove(course);
	}
}

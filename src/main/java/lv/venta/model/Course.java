package lv.venta.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "CourseTable")
@Entity
public class Course {
	
	
	@Setter(value = AccessLevel.NONE)
	@Column(name = "Cid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cid;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z]{1}[A-Za-z0-9 ]{1,40}")
	@Column(name = "Title")
	private String title;
	
	@Min(1)
	@Max(30)
	@Column(name = "CreditPoints")
	private int creditPoints;
	
	@ManyToMany
	@JoinTable(name = "profCourseTable", joinColumns = @JoinColumn(name = "Cid"), inverseJoinColumns = @JoinColumn(name = "Pid"))
	@ToString.Exclude
	private Collection<Professor> professors = new ArrayList<Professor>();
	
	@OneToMany(mappedBy = "course")
	@ToString.Exclude
	private Collection<Grade> grades = new ArrayList<Grade>();
	
	//TODO sasaiste ar profesoru
	public Course(String title, int creditPoints, Professor professor) {
		setTitle(title);
		setCreditPoints(creditPoints);
		try {
			addProfessor(professor);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addProfessor(Professor professor) throws Exception {
		if(professor == null) {
			throw new Exception("Nav pareizi ievades dati");
		}
		if(professors.contains(professor)) {
			throw new Exception(professor.getSurname() + " jau esksiste ka kursa pasniedzejs");
		}
		professors.add(professor);
	}
	
	public void removeCourse(Professor professor) throws Exception {
		if(professor == null) {
			throw new Exception("Nav pareizi ievades dati");
		}
		if(!professors.contains(professor)) {
			throw new Exception("Neavar dzest, jo " + professor.getSurname() + " neeksiste kursu profesoru saraksta");
		}
		professors.remove(professor);
	}

}

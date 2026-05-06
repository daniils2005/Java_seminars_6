package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Professor;
import lv.venta.model.Student;
import lv.venta.model.enums.Degree;

public interface IFilterService {
	
	//visi profesori, kuru grads ir konkretais
	public abstract ArrayList<Professor> filterProfessorByDegree(Degree degree) throws Exception;
	//iegut kursus, kurus pasniedz profesors ar konkretu id 
	public abstract ArrayList<Course> filterCoursesByProfessorId(long id) throws Exception;
	//iegut visas atzimes, ja ir zinams studenta vards un uzvards
	public abstract ArrayList<Grade> filterGradesByStudentNameAndSurname(String name, String surname) throws Exception;
	//iegut videjo atzimju vertibu, ja ir zinams kursa nosaukums
	public abstract float calculateAvgGradeByCourseTitle(String title) throws Exception;
	//iegut visus studentus, kuriem ir kada nesekmiga atzime
	public abstract ArrayList<Student> filterStudentsWithFailedGrades() throws Exception;
}

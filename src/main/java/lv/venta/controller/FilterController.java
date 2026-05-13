package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Professor;
import lv.venta.model.enums.Degree;
import lv.venta.service.IFilterService;

@Controller
public class FilterController {

	@Autowired
	private IFilterService filterService;
	
	@GetMapping("/professor/degree/{inputdegree}")
	public String getProfessorsByDegree(@PathVariable(name = "inputdegree") Degree inputDegree, Model model) {
		try {
			ArrayList<Professor> professorsFromDB = filterService.filterProfessorByDegree(inputDegree);
			model.addAttribute("package", professorsFromDB);
			return "show-multiple-professors-page";
		} catch(Exception e) {
			model.addAttribute("package", e);
			return "error-page";
		}
	}
	
	@GetMapping("get/courses/professor/id/{id}")
	public String getCoursesByProfessorId(@PathVariable(name = "id") long id, Model model) {
		try {
			ArrayList<Course> coursesFromDB = filterService.filterCoursesByProfessorId(id);
			model.addAttribute("myHeader", "Kursi, kurus pasniedz profesors id=" + id);
			model.addAttribute("package", coursesFromDB);
			return "show-multiple-courses-page";
		} catch(Exception e) {
			model.addAttribute("package", e);
			return "error-page";
		}
	}
	
	@GetMapping("/grades/student/{name}/{surname}")
	public String getGradesByStudentNameAndSurname(@PathVariable(name = "name") String name, @PathVariable(name = "surname") String surname, Model model) {
		try {
			ArrayList<Grade> gradesFromDB = filterService.filterGradesByStudentNameAndSurname(name, surname);
			model.addAttribute("package", gradesFromDB);
			return "show-multiple-grades-page";
		} catch(Exception e) {
			model.addAttribute("package", e);
			return "error-page";
		}
	}
}

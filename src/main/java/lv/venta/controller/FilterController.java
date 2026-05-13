package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}

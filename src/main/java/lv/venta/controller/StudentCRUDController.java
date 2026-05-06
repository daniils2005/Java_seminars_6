package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.service.IStudentCRUDService;

@Controller
@RequestMapping("/students/crud")
public class StudentCRUDController {
		
	//objekts no kura ir pieejamas visas ieprieks izveidotas CRUD funkcijas
	@Autowired
	private IStudentCRUDService studCrudService;
	
	@GetMapping("/all") //localhost:8080/student/crud/all
	public String getAllStudents(Model model) {
		try {
			model.addAttribute("package", studCrudService.retrieveAll());
			return "show-all-students";
		} catch(Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String getDeleteStudentById(@PathVariable(name = "id") long id, Model model) {
		try
		{
			studCrudService.deleteById(id);
			model.addAttribute("package", studCrudService.retrieveAll());
			return "show-all-students-page";  //tiks paradita show-all-students-page.html lapa
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}

package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Grade;
import lv.venta.model.Student;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.service.IStudentCRUDService;

@Service
public class StudentCRUDServiceImpl implements IStudentCRUDService {

	@Autowired
	private IStudentRepo studRepo;
	
	@Autowired
	private IGradeRepo gradeRepo;
	
	@Override
	public ArrayList<Student> retrieveAll() throws Exception {
		if(studRepo.count() == 0) {
			throw new Exception("studentu tabula DB ir tuksa");
		}
		ArrayList<Student> result = (ArrayList<Student>)studRepo.findAll();
		return result;
	}

	@Override
	public Student retrieveById(long id) throws Exception {
		if(id <= 0) {
			throw new Exception("id nevar but negativs vai 0");
		}
		if(!studRepo.existsById(id)) {
			throw new Exception("neeksiste produkts ar id " + id);
		}
		return studRepo.findById(id).get();
	}

	//students ir sasaitits ar grade
	@Override
	public void deleteById(long id) throws Exception {
		if(id <= 0) {
			throw new Exception("id nevar but negativs vai 0");
		}
		if(!studRepo.existsById(id)) {
			throw new Exception("neeksiste produkts ar id " + id);
		}
		
		Student studentForDeleting = studRepo.findById(id).get();
		
		ArrayList<Grade> gradesForThisStudent = gradeRepo.findByStudentSid(id);
		
		for(Grade tempG : gradesForThisStudent) {
			tempG.setStudent(null);
			gradeRepo.save(tempG); // lai nomainitu uz null datubazes limeni
		}
		
		studRepo.delete(studentForDeleting);

	}

	@Override
	public void create(String name, String surname) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateById(long id, String name, String surname) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

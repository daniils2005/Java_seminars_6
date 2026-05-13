package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Course;

public interface ICourseRepo extends CrudRepository<Course, Long>{

	boolean existsByTitle(String title);

	ArrayList<Course> findByProfessorsPid(long id);

}

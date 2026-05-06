package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Grade;

public interface IGradeRepo extends CrudRepository<Grade, Long>{

	ArrayList<Grade> findByStudentSid(long id);

	ArrayList<Grade> findByStudentNameAndStudentSurname(String name, String surname);

	@Query(nativeQuery = true, 
			value = "SELECT avg(t1.gr_value) "
					+ "FROM grade_table as t1 "
					+ "JOIN course_table as t2 "
					+ "ON t1.cid=t2.cid "
					+ "WHERE t2.title=:title;")
	float calculateAVGGradeForCourse(String title);

}

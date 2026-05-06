package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Professor;
import lv.venta.model.enums.Degree;

public interface IProfessorRepo extends CrudRepository<Professor, Long> {

	ArrayList<Professor> findByDegree(Degree degree);

}

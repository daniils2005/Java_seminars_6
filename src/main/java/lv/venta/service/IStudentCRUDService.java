package lv.venta.service;

import lv.venta.model.Student;

public interface IStudentCRUDService extends ICRUDServiceBase<Student> {
	//CRUD prieks Studenta - retrieve all, retrieve by id, delete by id nak no ICRUDServiceBase
	
	//C - create
	public abstract void create(String name, String surname) throws Exception;
	
	//U - update by id
	public abstract void updateById(long id, String name, String surname) throws Exception;
	
}

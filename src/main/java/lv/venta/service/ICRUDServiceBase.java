package lv.venta.service;

import java.util.ArrayList;

//Ttype - template datu tips, kas pielagosies velak jau konkretaja CRUD interfeisa
public interface ICRUDServiceBase<Ttype> {
	//CRUD - ka baze tam funkcijam, kuras var sablonizet - retrieveAll, retrieve by id, delete by id
	
	//R - retrieve all
	public abstract ArrayList<Ttype> retrieveAll() throws Exception;
	
	//R - retrieve by id
	public abstract Ttype retrieveById(long id) throws Exception;
	
	//D - delete by id
	public abstract void deleteById(long id) throws Exception;
	
}

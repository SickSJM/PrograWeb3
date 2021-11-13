package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Profesor;


public interface IProfesorService {
	public void insert(Profesor role);
	public void delete(long idProfesor);
	Optional<Profesor> listarId(long idprofesor);
	List<Profesor> list();

}

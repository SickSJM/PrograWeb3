package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Profesor;

public interface IProfesorService {
	public Integer insert(Profesor profesor);
	public void insert2(Profesor profesor);
	List<Profesor> list();
	Optional<Profesor> listarId(int idpro);
	public void delete(int idpro);
}

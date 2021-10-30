package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Profesor;

public interface IProfesorService {
	public Integer insert(Profesor profesor);

	List<Profesor> list();
}

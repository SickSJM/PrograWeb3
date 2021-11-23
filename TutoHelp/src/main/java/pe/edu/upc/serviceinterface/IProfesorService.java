package pe.edu.upc.serviceinterface;



import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Profesor;




public interface IProfesorService {
	public void insert(Profesor role);
	public void delete(long idProfesor);
	Optional<Profesor> listarId(long idprofesor);
	List<Profesor> list();

}

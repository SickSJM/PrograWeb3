package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Curso;

public interface ICursoService {
	public Integer insert(Curso curso);

	List<Curso> list();
	Optional<Curso> listarId(int idcu);
	public void delete(int idcu);
}

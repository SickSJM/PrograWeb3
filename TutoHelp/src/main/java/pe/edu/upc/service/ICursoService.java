package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Curso;

public interface ICursoService {
	public void insert(Curso cur);

	List<Curso> list();
	public void delete(long id);
	Optional<Curso> listId(long id);
}

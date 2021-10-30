package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Curso;

public interface ICursoService {
	public Integer insert(Curso curso);

	List<Curso> list();
}

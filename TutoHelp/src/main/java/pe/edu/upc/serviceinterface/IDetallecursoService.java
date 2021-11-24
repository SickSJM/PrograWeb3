package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Detallecurso;

public interface IDetallecursoService {
	public void insert(Detallecurso dcurso);
	public void delete(long iddcurso);
	Optional<Detallecurso> listarId(long iddcurso);
/*
	List<Tipodeusuario> findByName(String name);

	List<Tipodeusuario> findByNameLikeIgnoreCase(String name);*/
	List<Detallecurso> list();
	public List<String[]> Reporte1();
}

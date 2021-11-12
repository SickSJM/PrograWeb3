package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Consulta;

public interface IConsultaService {
	public void insert(Consulta consul);

	List<Consulta> list();
	public void delete(long id);
	Optional<Consulta> listId(long id);
}

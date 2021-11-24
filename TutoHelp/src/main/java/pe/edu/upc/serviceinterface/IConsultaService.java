package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Consulta;

public interface IConsultaService {
	public void insert(Consulta consulta);

	List<Consulta> list();
	public void delete(int id);
	Optional<Consulta> listId(int id);

}

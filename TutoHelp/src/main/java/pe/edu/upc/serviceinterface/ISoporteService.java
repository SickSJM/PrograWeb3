package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Soporte;


public interface ISoporteService {
	public void insert(Soporte soporte);

	List<Soporte> list();
	public void delete(int idSoporte);
	Optional<Soporte> listId(int idSoporte);

}

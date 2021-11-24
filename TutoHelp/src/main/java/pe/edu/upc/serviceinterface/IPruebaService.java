package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Prueba;

public interface IPruebaService {
	public void insert(Prueba xprueba);

	List<Prueba> list();
	Optional<Prueba> listarId(int id);
	public void delete(int id);
}

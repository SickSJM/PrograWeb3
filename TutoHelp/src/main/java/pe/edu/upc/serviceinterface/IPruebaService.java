package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.Prueba;

public interface IPruebaService {
	public Integer insert(Prueba xprueba);

	List<Prueba> list();

}

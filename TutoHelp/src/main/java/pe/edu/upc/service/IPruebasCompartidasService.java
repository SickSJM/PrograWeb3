package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.PruebasCompartidas;

public interface IPruebasCompartidasService {
	public void insert(PruebasCompartidas prueba);
	public void delete(long id);
	Optional<PruebasCompartidas> listarId(long id);
	List<PruebasCompartidas> list();

}

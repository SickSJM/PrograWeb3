package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Soporte;

public interface ISoporteService {
	public void insert(Soporte role);
	public void delete(long idSoporte);
	Optional<Soporte> listarId(long idsoporte);
	List<Soporte> list();

}

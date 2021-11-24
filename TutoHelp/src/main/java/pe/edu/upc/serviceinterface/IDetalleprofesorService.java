package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Detalleprofesor;


public interface IDetalleprofesorService {
	public void insert(Detalleprofesor role);
	public void delete(long idDetalle);
	Optional<Detalleprofesor> listarId(long iddetalle);
	List<Detalleprofesor> list();

}

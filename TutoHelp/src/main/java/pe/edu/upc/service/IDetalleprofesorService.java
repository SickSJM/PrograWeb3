package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Detalleprofesor;


public interface IDetalleprofesorService {
	public void insert(Detalleprofesor role);
	public void delete(long idDetalle);
	Optional<Detalleprofesor> listarId(long iddetalle);
	List<Detalleprofesor> list();

}

package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Tipodeusuario;

public interface ITipodeusuarioService {
	public void insert(Tipodeusuario role);
	public void delete(long idrole);
	Optional<Tipodeusuario> listarId(long idrole);
/*
	List<Tipodeusuario> findByName(String name);

	List<Tipodeusuario> findByNameLikeIgnoreCase(String name);*/
	List<Tipodeusuario> list();

}

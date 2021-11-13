package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Tipodeusuario;

public interface ITipodeusuarioService {
	public Integer insert(Tipodeusuario role);
	public void delete(long idrole);
	Optional<Tipodeusuario> listarId(long idrole);
/*
	List<Tipodeusuario> findByName(String name);

	List<Tipodeusuario> findByNameLikeIgnoreCase(String name);*/
	List<Tipodeusuario> list();

}

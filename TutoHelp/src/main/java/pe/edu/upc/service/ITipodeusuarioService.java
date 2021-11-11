package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Tipodeusuario;

public interface ITipodeusuarioService {
	public void insert(Tipodeusuario role);

	List<Tipodeusuario> list();

}

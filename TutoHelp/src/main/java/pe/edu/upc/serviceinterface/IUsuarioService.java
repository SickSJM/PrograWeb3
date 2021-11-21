package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Usuario;

public interface IUsuarioService {
	public Integer insert(Usuario usuario);
	public void insert2(Usuario usuario);
	List<Usuario> list();
	Optional<Usuario> listarId(int iduser);
	public void delete(int iduser);
}

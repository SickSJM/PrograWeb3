package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entities.TipoDeUsuario;

public interface ITipoDeUsuarioService {
	public Integer insert(TipoDeUsuario tipodeusuario);

	List<TipoDeUsuario> list();
}

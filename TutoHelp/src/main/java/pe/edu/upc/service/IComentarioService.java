package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Comentario;

public interface IComentarioService {
	public void insert(Comentario comentario);
	public void delete(long id);
	Optional<Comentario> listarId(long id);

	List<Comentario> list();

}

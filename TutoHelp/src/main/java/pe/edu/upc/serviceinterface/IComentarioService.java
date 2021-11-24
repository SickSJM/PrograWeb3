package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Comentario;

public interface IComentarioService {
	public void insert(Comentario comentario);
	public void delete(long id);
	Optional<Comentario> listarId(long id);

	List<Comentario> list();

}

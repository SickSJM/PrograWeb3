package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("select count(l.NombreUsuario) from Usuario l where l.NombreUsuario=:name")
	public int buscarUsuario(@Param("name") String NombreUsuario);
}

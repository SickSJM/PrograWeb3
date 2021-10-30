package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoDeUsuario;

@Repository
public interface ITipoDeUsuarioRepository extends JpaRepository<TipoDeUsuario, Integer> {
	@Query("select count(l.descripcionTipoDeUsuario) from TipoDeUsuario l where upper(l.descripcionTipoDeUsuario)=:name")
	public int buscarTipoDeUsuario(@Param("name") String descripcionTipoDeUsuario);
}

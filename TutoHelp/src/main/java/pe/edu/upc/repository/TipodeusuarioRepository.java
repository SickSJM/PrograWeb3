package pe.edu.upc.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Tipodeusuario;
import pe.edu.upc.entities.Usuario;

@Repository
public interface TipodeusuarioRepository extends JpaRepository<Tipodeusuario, Long> {
	@Query("select count(c.user) from Tipodeusuario c where c.user =:user")
	public int buscarNombreTipousuario(@Param("user") Usuario usuario);
}
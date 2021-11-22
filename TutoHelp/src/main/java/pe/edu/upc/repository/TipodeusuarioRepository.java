package pe.edu.upc.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Tipodeusuario;

@Repository
public interface TipodeusuarioRepository extends JpaRepository<Tipodeusuario, Long> {
	@Query("select count(c.descripcion) from Tipodeusuario c where c.descripcion =:descripcion")
	public int buscarNombreTipousuario(@Param("descripcion") String nombreCategoria);
}
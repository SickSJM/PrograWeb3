package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Prueba;

@Repository
public interface IPruebaRepository extends JpaRepository<Prueba, Integer> {
	@Query("select count(s.NombreArchivo) from Prueba s where s.NombreArchivo=:name")
	public int buscarPrueba(@Param("name") String NombreArchivo);
}

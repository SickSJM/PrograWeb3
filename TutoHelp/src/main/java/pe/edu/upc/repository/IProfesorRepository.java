package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Profesor;

@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Integer> {
	@Query("select count(s.ProfesorNombre) from Profesor s where s.ProfesorNombre=:name")
	public int buscarProfesor(@Param("name") String ProfesorNombre);
}

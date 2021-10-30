package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Integer> {
	@Query("select count(l.CursoNombre) from Curso l where upper(l.CursoNombre)=:name")
	public int buscarCurso(@Param("name") String CursoNombre);
}

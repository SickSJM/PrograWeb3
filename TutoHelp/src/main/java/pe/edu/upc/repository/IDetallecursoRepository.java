package pe.edu.upc.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Detallecurso;

@Repository
public interface IDetallecursoRepository extends JpaRepository<Detallecurso, Long> {
	
	@Query(value = "select c.curso_nombre, count(d.user_id) "
			+ "from detallecursos d "
			+ "join usuario u on u.usuarioid=d.user_id "
			+ "join curso c on c.cursoid=d.curso "
			+ "group by c.curso_nombre "
			+ "order by count(d.user_id) "
			+ "desc limit 1", nativeQuery = true)
	public List<String[]> Reporte1();

}
package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Consulta;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Integer> {
	@Query("select count(l.TextoConsulta) from Consulta l where l.TextoConsulta=:name")
	public int buscarConsulta(@Param("name") String TextoConsulta);
}

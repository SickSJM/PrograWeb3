package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Soporte;

@Repository
public interface ISoporteRepository extends JpaRepository<Soporte, Integer> {
	@Query("select count(l.ReporteMensaje) from Soporte l where l.ReporteMensaje=:name")
	public int buscarSoporte(@Param("name") String ReporteMensaje);
}

package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Invitacion;

@Repository
public interface IInvitacionRepository extends JpaRepository<Invitacion, Integer> {
	@Query("select count(l.LinkReunion) from Invitacion l where l.LinkReunion=:name")
	public int buscarInvitacion(@Param("name") String LinkReunion);
}

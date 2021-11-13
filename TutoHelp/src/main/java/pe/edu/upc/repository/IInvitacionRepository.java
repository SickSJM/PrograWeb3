package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Invitacion;

@Repository
public interface IInvitacionRepository extends JpaRepository<Invitacion, Long> {

}
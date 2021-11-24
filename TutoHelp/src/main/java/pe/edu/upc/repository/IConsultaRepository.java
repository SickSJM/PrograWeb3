package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Consulta;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Integer> {

}

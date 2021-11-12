package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Consulta;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long> {

}
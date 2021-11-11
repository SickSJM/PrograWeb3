package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Soporte;

@Repository
public interface ISoporteRepository extends JpaRepository<Soporte, Long> {

}
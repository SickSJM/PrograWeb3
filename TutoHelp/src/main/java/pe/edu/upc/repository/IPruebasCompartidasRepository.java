package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.PruebasCompartidas;

@Repository
public interface IPruebasCompartidasRepository extends JpaRepository<PruebasCompartidas, Long> {

}
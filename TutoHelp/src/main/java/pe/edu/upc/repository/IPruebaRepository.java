package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Prueba;

@Repository
public interface IPruebaRepository extends JpaRepository<Prueba, Integer> {
}

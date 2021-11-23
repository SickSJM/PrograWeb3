package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Profesor;



@Repository
public interface IProfesorRepository extends JpaRepository<Profesor, Long> {

}
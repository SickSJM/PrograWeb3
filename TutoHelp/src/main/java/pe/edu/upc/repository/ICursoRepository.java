package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Curso;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {

}
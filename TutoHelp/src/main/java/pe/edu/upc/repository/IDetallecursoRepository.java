package pe.edu.upc.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Detallecurso;

@Repository
public interface IDetallecursoRepository extends JpaRepository<Detallecurso, Long> {

}
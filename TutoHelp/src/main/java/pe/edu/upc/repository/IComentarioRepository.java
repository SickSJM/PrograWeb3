package pe.edu.upc.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Comentario;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario, Long> {

}
package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Tipodeusuario;

@Repository
public interface TipodeusuarioRepository extends JpaRepository<Tipodeusuario, Long> {

}
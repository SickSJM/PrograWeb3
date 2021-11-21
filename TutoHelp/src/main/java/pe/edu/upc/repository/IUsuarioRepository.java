package pe.edu.upc.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	//@Query("select count(l.NombreUsuario) from Usuario l where l.NombreUsuario=:name")
	//public int buscarUsuario(@Param("name") String NombreUsuario);
	
	public Usuario findByUsername(String username);
	
	@Query("select count(u.username) from Usuario u where u.username =:username")
	public int buscarUsername(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query(value= "insert into tipodeusuarios (descripcion, user_id) VALUES (:descripcion, :user_id)", nativeQuery = true)
	public void insRol(@Param("descripcion") String authority, @Param("user_id") Long user_id);
}

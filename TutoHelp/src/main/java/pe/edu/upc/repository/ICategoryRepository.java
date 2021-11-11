package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
	@Query("select count(c.name) from Category c where c.name =:name")
	public int buscarNombreCategoria(@Param("name") String nombreCategoria);

	@Query("select c from Category c where c.name like %:name%")
	List<Category> findByName(String name);

	List<Category> findByNameLikeIgnoreCase(String name);

	@Query(value = "SELECT c.name,sum(ode.quantity) "
			+ "from Orders o join order_details ode on  ode.order_id = o.id "
			+ "join products pr on ode.product_id= pr.id "
			+ "join categories c on c.id = pr.id_category "
			+ "group by c.name "
			+ "ORDER BY sum(ode.quantity) "
			+ "DESC limit 1", nativeQuery = true)
	public List<String[]> categoryTopQuantityOrder();

	
	
	
	
	
	
	
	@Query(value = "SELECT c.name,count(pr.id) from orders o join order_details ode on ode.order_id = o.id join products pr on ode.product_id = pr.id join categories c on c.id = pr.id_category group by c.name ORDER BY COUNT(c.name) DESC limit 1", nativeQuery = true)
	public List<String[]> categoryTopFrequencyOrder();
}

package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Users;

public interface IUserService {
	public Integer insert(Users user);

	List<Users> list();
	public void delete(long id);
	Optional<Users> listId(long id);
}

package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Users;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository uR;

	@Override
	public Integer insert(Users user) {
		int rpta = uR.buscarUsername(user.getUsername());
		if (rpta == 0) {
			uR.save(user);
		}
		return rpta;
	}

	@Override
	public List<Users> list() {
		// TODO Auto-generated method stub
		return uR.findAll();
	}
}

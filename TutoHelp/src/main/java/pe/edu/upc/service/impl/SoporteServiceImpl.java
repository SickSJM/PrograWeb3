package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Soporte;
import pe.edu.upc.repository.ISoporteRepository;
import pe.edu.upc.service.ISoporteService;

@Service
public class SoporteServiceImpl implements ISoporteService {
	@Autowired
	private ISoporteRepository rR;

	@Override
	public void insert(Soporte role) {
		rR.save(role);
	}

	@Override
	public List<Soporte> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}


	
}

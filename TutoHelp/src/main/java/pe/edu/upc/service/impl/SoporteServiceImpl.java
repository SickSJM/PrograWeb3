package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void delete(long idSoporte) {
		rR.deleteById(idSoporte);
	}

	@Override
	public List<Soporte> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public Optional<Soporte> listarId(long idsoporte) {
		// TODO Auto-generated method stub
		return rR.findById(idsoporte);
	}


	
}

package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Profesor;
import pe.edu.upc.repository.IProfesorRepository;
import pe.edu.upc.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService {
	@Autowired
	private IProfesorRepository rR;

	@Override
	public void insert(Profesor role) {
		rR.save(role);
	}

	@Override
	@Transactional
	public void delete(long idProfesor) {
		rR.deleteById(idProfesor);
	}
	
	@Override
	public List<Profesor> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}
	
	@Override
	public Optional<Profesor> listarId(long idprofesor) {
		// TODO Auto-generated method stub
		return rR.findById(idprofesor);
	}


	
}

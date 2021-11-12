package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Curso;
import pe.edu.upc.repository.ICursoRepository;
import pe.edu.upc.service.ICursoService;

@Service
public class CursoServiceImpl implements ICursoService {
	@Autowired
	private ICursoRepository rR;

	@Override
	public void insert(Curso cur) {
		rR.save(cur);
	}

	@Override
	public List<Curso> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		rR.deleteById(id);
	}

	@Override
	public Optional<Curso> listId(long id) {
		// TODO Auto-generated method stub
		return rR.findById(id);
	}
	
}

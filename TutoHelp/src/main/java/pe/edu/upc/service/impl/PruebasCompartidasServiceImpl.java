package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.PruebasCompartidas;
import pe.edu.upc.repository.IPruebasCompartidasRepository;
import pe.edu.upc.service.IPruebasCompartidasService;

@Service
public class PruebasCompartidasServiceImpl implements IPruebasCompartidasService {
	@Autowired
	private IPruebasCompartidasRepository rR;

	@Override
	public void insert(PruebasCompartidas prueba) {
		rR.save(prueba);
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		rR.deleteById(id);
	}

	@Override
	public List<PruebasCompartidas> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public Optional<PruebasCompartidas> listarId(long id) {
		// TODO Auto-generated method stub
		return rR.findById(id);
	}


	
}

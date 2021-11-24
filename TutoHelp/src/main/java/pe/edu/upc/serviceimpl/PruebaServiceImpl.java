package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Prueba;
import pe.edu.upc.repository.IPruebaRepository;
import pe.edu.upc.serviceinterface.IPruebaService;

@Service
public class PruebaServiceImpl implements IPruebaService {
	@Autowired
	private IPruebaRepository pR;

	@Override
	public void insert(Prueba xprueba) {
			pR.save(xprueba);
	}

	@Override
	public List<Prueba> list() {
		// TODO Auto-generated method stub
		return pR.findAll();
	}

	@Override
	@Transactional
	public void delete(int id) {
		pR.deleteById(id);
	}
	
	@Override
	public Optional<Prueba> listarId(int id) {
		// TODO Auto-generated method stub
		return pR.findById(id);
	}
}
